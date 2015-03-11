package io.aurorasolutions.montyhall.main;

/**
 * Created by Rasheed on 2015-03-11.
 */
public class GameSetTestUtils {

    public static final Long NUMBER_OF_GAMES = 1000L;

    /**
     * Creates a new gameset
     *
     * @return a new gameset
     */
    public static GameSet createNewGameSet() {
        return  new GameSet(NUMBER_OF_GAMES);
    }
}
