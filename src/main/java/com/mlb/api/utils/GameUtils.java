package com.mlb.api.utils;

import com.mlb.api.dao.model.Game;

public class GameUtils {

    /**
     * Checks whether a given Game is in progress. It does this currently by checking that it's not
     * in the Final or the Scheduled state.
     *
     * TODO:
     * Note: Since we don't know the code for 'In Progress', since there's no current date we can
     * query to see what it looks like, current assumption is if it's not one of those 2 states, the
     * 3rd state would be improgress.
     *
     * @param game The game to check if it's progress.
     * @return True if the game is not in the Scheduled or Final state.
     */
    public static boolean isGameInProgress(Game game) {
        if (game == null || game.getStatus() == null || game.getStatus().getStatusCode() == null) {
            return false;
        }

        String gameStatusCode = game.getStatus().getStatusCode();

        return !"S".equalsIgnoreCase(gameStatusCode) && !"F".equalsIgnoreCase(gameStatusCode);
    }
}
