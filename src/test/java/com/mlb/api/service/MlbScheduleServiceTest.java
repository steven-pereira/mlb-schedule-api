package com.mlb.api.service;

import com.mlb.api.dao.MlbScheduleDaoImpl;
import com.mlb.api.dao.model.*;
import com.mlb.api.test.MockTeamFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MlbScheduleServiceTest {

    @InjectMocks
    private MlbScheduleService mlbScheduleService;

    @Mock
    private MlbScheduleDaoImpl mlbScheduleDao;

    @Test
    public void getScheduleWithFavTeamsOnTopNoDoubleHeaderTest1() {

        MlbScheduleResponse mockDaoResponse = createMockMlbScheduleWithNoDoubleHeader();
        LocalDate mockDate = LocalDate.of(2021, 4, 1);
        Long favoriteTeamId = 147L; // Lets go Yankees!

        when(mlbScheduleDao.getSchedule(mockDate, 1L, "en")).thenReturn(mockDaoResponse);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, mockDate, 1l, "en");

        // Verify the first game in the list is the Yankees game
        Long expectedFirstGameId = 3L;
        assertEquals(expectedFirstGameId, mlbScheduleResponse.getDates().get(0).getGames().get(0).getGamePk());


        // Verify the total count never changed
        int expectedGameSize = 5;
        assertEquals(expectedGameSize, mlbScheduleResponse.getDates().get(0).getGames().size());
    }

    @Test
    public void getScheduleWithFavTeamsOnTopNoDoubleHeaderTest2() {
        // Variation of Test1, but with the favorite team already being the first on the list
        // Expectation is the same list/ordering is returned
        MlbScheduleResponse mockDaoResponse = createMockMlbScheduleWithNoDoubleHeader();
        LocalDate mockDate = LocalDate.of(2021, 4, 1);
        Long favoriteTeamId = 112L;

        when(mlbScheduleDao.getSchedule(mockDate, 1L, "en")).thenReturn(mockDaoResponse);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, mockDate, 1L, "en");

        // Verify the first game
        Long expectedFirstGameId = 1L;
        assertEquals(expectedFirstGameId, mlbScheduleResponse.getDates().get(0).getGames().get(0).getGamePk());

        // Verify the last game is the same
        Long expectedLastGameId = 5L;
        assertEquals(expectedLastGameId, mlbScheduleResponse.getDates().get(0).getGames().get(4).getGamePk());


        // Verify the total count never changed
        int expectedGameSize = 5;
        assertEquals(expectedGameSize, mlbScheduleResponse.getDates().get(0).getGames().size());
    }

    @Test
    public void getScheduleWithFavTeamsOnTopNoDoubleHeaderTest3() {
        // Variation of Test1, but with the favorite team being the last on the list
        // Confirms 'edge case' of team being last on the list
        MlbScheduleResponse mockDaoResponse = createMockMlbScheduleWithNoDoubleHeader();
        LocalDate mockDate = LocalDate.of(2021, 4, 1);
        Long favoriteTeamId = 146L;

        when(mlbScheduleDao.getSchedule(mockDate, 1L, "en")).thenReturn(mockDaoResponse);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, mockDate,1L, "en");

        // Verify the first game
        Long expectedFirstGameId = 5L;
        assertEquals(expectedFirstGameId, mlbScheduleResponse.getDates().get(0).getGames().get(0).getGamePk());

        // Verify the last game is the same
        Long expectedLastGameId = 4L;
        assertEquals(expectedLastGameId, mlbScheduleResponse.getDates().get(0).getGames().get(4).getGamePk());

        // Verify the total count never changed
        int expectedGameSize = 5;
        assertEquals(expectedGameSize, mlbScheduleResponse.getDates().get(0).getGames().size());
    }

    @Test
    public void getScheduleWithFavTeamsOnTopWithDoubleHeaderTest() {

        MlbScheduleResponse mockDaoResponse = createMockMlbScheduleWithDoubleHeader();
        LocalDate mockDate = LocalDate.of(2021, 9, 11);
        Long favoriteTeamId = 141L; // Toronto Blue Jays

        when(mlbScheduleDao.getSchedule(mockDate, 1L, "en")).thenReturn(mockDaoResponse);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, mockDate, 1L, "en");

        // Verify the first game has the first toronto double header game
        Long expectedFirstGameId = 632539L;
        assertEquals(expectedFirstGameId, mlbScheduleResponse.getDates().get(0).getGames().get(0).getGamePk());

        // Verify the second game has the second toronto double header game;
        Long expectedSecondGameId = 633331L;
        assertEquals(expectedSecondGameId, mlbScheduleResponse.getDates().get(0).getGames().get(1).getGamePk());

        // Verify the total count never changed
        int expectedGameSize = 5;
        assertEquals(expectedGameSize, mlbScheduleResponse.getDates().get(0).getGames().size());
    }

    private MlbScheduleResponse createMockMlbScheduleWithNoDoubleHeader() {
        MlbScheduleResponse mlbScheduleResponse = new MlbScheduleResponse();

        LocalDate officialGameDate = LocalDate.of(2021, 4, 1);

        GameDate gameDateObj = new GameDate();
        gameDateObj.setDate(officialGameDate);

        // List of games store
        LinkedList<Game> games = new LinkedList<>();

        // 1st Game
        // Create Away Team Detail
        Team awayTeam = MockTeamFactory.createCubsTeam();
        // Create Home Team Detail
        Team homeTeam = MockTeamFactory.createRaysTeam();
        // Create Game
        LocalDateTime gameDate = LocalDateTime.of(officialGameDate, LocalTime.of(18, 20, 0));
        Game game = createGameMock(gameDate, officialGameDate,1L, createGameStatusFinal(), homeTeam, awayTeam);
        // Add Game to list of games
        games.add(game);


        // 2nd Game
        // Create Away Team Detail
        Team awayTeam2 = MockTeamFactory.createRangersTeam();
        // Create Home Team Detail
        Team homeTeam2 = MockTeamFactory.createAthleticsTeam();
        // Create Game
        LocalDateTime gameDate2 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 7, 0));
        Game game2 = createGameMock(gameDate, officialGameDate, 2L, createGameStatusFinal(), homeTeam2, awayTeam2);
        // Add Game to list of games
        games.add(game2);

        // 3rd Game
        // Create Away Team Detail
        Team awayTeam3 = MockTeamFactory.createYankeesTeam();
        // Create Home Team Detail
        Team homeTeam3 = MockTeamFactory.createMetsTeam();
        // Create Game
        LocalDateTime gameDate3 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 35, 0));
        Game game3 = createGameMock(gameDate, officialGameDate, 3L, createGameStatusFinal(), homeTeam3, awayTeam3);
        // Add Game to list of games
        games.add(game3);

        // 4th Game
        // Create Status
        GameStatus gameStatus = createGameStatusFinal();
        gameStatus.setStartTimeTBD(true);
        // Create Away Team Detail
        Team awayTeam4 = MockTeamFactory.createBravesTeam();
        // Create Home Team Detail
        Team homeTeam4 = MockTeamFactory.createAstrosTeam();
        // Create Game
        LocalDateTime gameDate4 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 40, 0));
        Game game4 = createGameMock(gameDate, officialGameDate, 4L, gameStatus, homeTeam4, awayTeam4);
        // Add Game to list of games
        games.add(game4);

        // 5th Game
        // Create Away Team Detail
        Team awayTeam5 = MockTeamFactory.createDodgersTeam();
        // Create Home Team Detail
        Team homeTeam5 = MockTeamFactory.createMiamiMarlins();
        // Create Game
        LocalDateTime gameDate5 = LocalDateTime.of(officialGameDate, LocalTime.of(22, 5, 0));
        Game game5 = createGameMock(gameDate, officialGameDate, 5L, createGameStatusFinal(), homeTeam5, awayTeam5);
        // Add Game to list of games
        games.add(game5);

        // Add games to gameDate
        gameDateObj.setGames(games);

        // Set Game Date to schedule response
        mlbScheduleResponse.setDates(List.of(gameDateObj));

        return mlbScheduleResponse;
    }


    private MlbScheduleResponse createMockMlbScheduleWithDoubleHeader() {
        MlbScheduleResponse mlbScheduleResponse = new MlbScheduleResponse();

        LocalDate officialGameDate = LocalDate.of(2021, 9, 11);

        GameDate gameDateObj = new GameDate();
        gameDateObj.setDate(officialGameDate);

        // List of games store
        LinkedList<Game> games = new LinkedList<>();

        // 1st Game
        // Create Away Team Detail
        Team awayTeam = MockTeamFactory.createGiantsTeam();
        // Create Home Team Detail
        Team homeTeam = MockTeamFactory.createCubsTeam();
        // Create Game
        LocalDateTime gameDate = LocalDateTime.of(officialGameDate, LocalTime.of(18, 20, 0));
        Game game = createGameMock(gameDate, officialGameDate,632547L, createGameStatusFinal(), homeTeam, awayTeam);
        // Add Game to list of games
        games.add(game);


        // 2nd Game
        // Create Away Team Detail
        Team awayTeam2 = MockTeamFactory.createRangersTeam();
        // Create Home Team Detail
        Team homeTeam2 = MockTeamFactory.createAthleticsTeam();
        // Create Game
        LocalDateTime gameDate2 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 7, 0));
        Game game2 = createGameMock(gameDate, officialGameDate, 632535L, createGameStatusFinal(), homeTeam2, awayTeam2);
        // Add Game to list of games
        games.add(game2);

        // 3rd Game (1st of double header)
        // Create Away Team Detail
        Team awayTeam3 = MockTeamFactory.createBlueJaysTeam();
        // Create Home Team Detail
        Team homeTeam3 = MockTeamFactory.createOriolesTeam();
        // Create Game
        LocalDateTime gameDate3 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 35, 0));
        Game game3 = createGameMock(gameDate, officialGameDate, 632539L, createGameStatusFinal(), homeTeam3, awayTeam3);
        // Add Game to list of games
        games.add(game3);

        // 4th Game (2nd of double header)
        // Create Status
        GameStatus gameStatus = createGameStatusFinal();
        gameStatus.setStartTimeTBD(true);
        // Create Away Team Detail
        Team awayTeam4 = MockTeamFactory.createBlueJaysTeam();
        // Create Home Team Detail
        Team homeTeam4 = MockTeamFactory.createOriolesTeam();
        // Create Game
        LocalDateTime gameDate4 = LocalDateTime.of(officialGameDate, LocalTime.of(20, 40, 0));
        Game game4 = createGameMock(gameDate, officialGameDate, 633331L, gameStatus, homeTeam4, awayTeam4);
        // Add Game to list of games
        games.add(game4);

        // 5th Game
        // Create Away Team Detail
        Team awayTeam5 = MockTeamFactory.createRockiesTeam();
        // Create Home Team Detail
        Team homeTeam5 = MockTeamFactory.createPhilliesTeam();
        // Create Game
        LocalDateTime gameDate5 = LocalDateTime.of(officialGameDate, LocalTime.of(22, 5, 0));
        Game game5 = createGameMock(gameDate, officialGameDate, 632540L, createGameStatusFinal(), homeTeam5, awayTeam5);
        // Add Game to list of games
        games.add(game5);

        // Add games to gameDate
        gameDateObj.setGames(games);

        // Set Game Date to schedule response
        mlbScheduleResponse.setDates(List.of(gameDateObj));

        return mlbScheduleResponse;
    }

    private Game createGameMock(LocalDateTime gameDate, LocalDate officialDate, Long gamePk, GameStatus gameStatus,
                                    Team homeTeam, Team awayTeam) {
        Game game = new Game();
        game.setGameDate(gameDate);
        game.setOfficialDate(officialDate);
        game.setGamePk(gamePk);
        game.setStatus(gameStatus);

        GameTeams gameTeams = new GameTeams();

        TeamDetail homeTeamDetail = new TeamDetail();
        homeTeamDetail.setTeam(homeTeam);

        TeamDetail awayTeamDetail = new TeamDetail();
        awayTeamDetail.setTeam(awayTeam);

        gameTeams.setHome(homeTeamDetail);
        gameTeams.setAway(awayTeamDetail);

        game.setTeams(gameTeams);

        return game;
    }

    private GameStatus createGameStatusFinal() {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setStatusCode("F");
        gameStatus.setStartTimeTBD(false);
        return gameStatus;
    }
}
