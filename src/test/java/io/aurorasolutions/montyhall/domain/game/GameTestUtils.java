package io.aurorasolutions.montyhall.domain.game;

import io.aurorasolutions.montyhall.domain.door.Door;
import io.aurorasolutions.montyhall.domain.host.Host;
import io.aurorasolutions.montyhall.domain.player.Player;

/**
 * Created by Rasheed on 2015-03-11.
 */
public class GameTestUtils {

    public static final Long GAME_ID = 1L;
    public static final Player PLAYER = new Player("Rasheed", "Amir");
    public static final Host HOST = new Host("Nasdaq", "OMX");

    /**
     * Create a new game
     *
     * @return a new game
     */
    public static Game createNewGame() {
        return new Game(GAME_ID, HOST, PLAYER);
    }

    /**
     * play game
     *
     * @return game
     */
    public static Game playGame() {
        Game game = GameTestUtils.createNewGame();
        Door initialDoorSelectedByPlayer = game.getRandomDoor();
        game.select(initialDoorSelectedByPlayer);
        Door switchedDoorOfPlayer = game.swap();
        game.open(switchedDoorOfPlayer);
        return game;
    }
}
