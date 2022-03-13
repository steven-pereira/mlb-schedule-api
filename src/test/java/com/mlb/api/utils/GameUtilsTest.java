package com.mlb.api.utils;

import com.mlb.api.dao.model.Game;
import com.mlb.api.dao.model.GameStatus;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameUtilsTest {

    @Test
    public void isGameInProgressForFinalCodeTest() {
        Game game = createGameInFinalState();

        Boolean actual = GameUtils.isGameInProgress(game);

        assertFalse(actual);
    }

    @Test
    public void isGameInProgressForScheduledCodeTest() {
        Game game = createGameInScheduledState();

        Boolean actual = GameUtils.isGameInProgress(game);

        assertFalse(actual);
    }

    @Test
    public void isGameInProgressTest() {
        Game game = new Game();
        game.setStatus(new GameStatus());
        // Making up status code for 'in progress' - see javadoc for utility method
        game.getStatus().setStatusCode("P");

        Boolean actual = GameUtils.isGameInProgress(game);

        assertTrue(actual);
    }

    private Game createGameInFinalState() {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setStatusCode("F");

        Game game =  new Game();
        game.setStatus(gameStatus);

        return game;
    }

    private Game createGameInScheduledState() {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setStatusCode("S");

        Game game =  new Game();
        game.setStatus(gameStatus);

        return game;
    }
}
