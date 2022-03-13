package com.mlb.api.service;

import com.mlb.api.config.EnvironmentKey;
import com.mlb.api.dao.IMlbScheduleDao;
import com.mlb.api.dao.model.Game;
import com.mlb.api.dao.model.MlbScheduleResponse;
import com.mlb.api.dao.model.Team;
import com.mlb.api.utils.GameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;

@Service
public class MlbScheduleService implements IMlbScheduleService {

    @Autowired
    private IMlbScheduleDao mlbScheduleDao;

    @Autowired
    private Environment env;

    @Override
    public MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date) {
        Long defaultSportId = Long.valueOf(env.getProperty(EnvironmentKey.DEFAULT_SPORT_ID));
        return getSchedule(favoriteTeamId, date, defaultSportId);
    }

    @Override
    public MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date, Long sportId) {
        String defaultLanguage = env.getProperty(EnvironmentKey.DEFAULT_LANGUAGE);
        return getSchedule(favoriteTeamId, date, sportId, defaultLanguage);
    }

    @Override
    public MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date, Long sportId, String language) {
        MlbScheduleResponse mlbScheduleResponse = mlbScheduleDao.getSchedule(date, sportId, language);

        // If there are no games on the given date, then nothing to do
        if (mlbScheduleResponse == null && mlbScheduleResponse.getDates() == null) {
            return mlbScheduleResponse;
        }

        // Move Favorite Team Games to the Top
        moveFavTeamGamesToTop(mlbScheduleResponse.getDates().get(0).getGames(), favoriteTeamId);

        // Return response with updated games list
        return mlbScheduleResponse;
    }

    /**
     * Moves the games where the favorite team is playing to the top. Maintains the same
     * chronological ordering of the remaining games.
     *
     * In the case of double headers:
     *      a) If both games are in the future, the earlier game is listed first
     *      b) If both games are in the past, the earlier game is listed first
     *      c) If either game is live, the live game is listed first, following by the completed game
     *
     * @param games List of games.
     * @param favoriteTeamId The favorite team unique id.
     */
    private void moveFavTeamGamesToTop(LinkedList<Game> games, Long favoriteTeamId) {

        if (games == null || games.isEmpty() || favoriteTeamId == null) {
            return;
        }

        int favTeamGamesFound = 0;

        // Iterate Through Each Game and Check if Away or Home Team is Favorite Team
        for (int i = 0; i < games.size(); i++) {

            Game game = games.get(i);

            Team awayTeam = game.getTeams().getAway().getTeam();
            Team homeTeam = game.getTeams().getHome().getTeam();

            // If either away or home team matches favorite team, remove from list and add
            // in the front
            if (favoriteTeamId.equals(awayTeam.getId()) || favoriteTeamId.equals(homeTeam.getId())) {
                games.remove(i);
                games.add(favTeamGamesFound, game);
                favTeamGamesFound++;
            }
        }

        // If we found 2 games for the same team, we need to figure out which game should show first
        if (favTeamGamesFound == 2) {
            // Grab first game and second game to compare which should be first
            Game firstGame = games.get(0);
            Game secondGame = games.get(1);

            // Check if the first game is in progress
            if (GameUtils.isGameInProgress(firstGame)) {
                // Do nothing since we want in progress game to be shown 1st
                // Not possible to have 2 games in progress with same team playing
            } else if (GameUtils.isGameInProgress(secondGame)) {
                // Flip the first and second game since the second game is in progress
                games.remove(1);
                games.addFirst(secondGame);
            } else if (firstGame.getStatus().getStartTimeTBD()) {
                // Flip the first and seconds games since first game has startTimeTBD flag set to true
                // which means it's the game that will happen after the 'first' game is over
                games.remove(1);
                games.addFirst(secondGame);
            }
        }
    }
}