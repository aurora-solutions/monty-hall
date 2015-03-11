package io.aurorasolutions.montyhall.domain.game;

import io.aurorasolutions.montyhall.domain.door.Door;
import io.aurorasolutions.montyhall.domain.prize.Prize;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Rasheed on 2015-03-10.
 *
 * Tests of the domain Game
 */
public class GameTest {

    /**
     * verifies that game with game id null can not be created
     */
    @Test(expected = IllegalArgumentException.class)
    public void the_game_with_null_id_can_not_be_created() {
        new Game(null, GameTestUtils.HOST, GameTestUtils.PLAYER);
    }

    /**
     * verifies that game with game id less than zero can not be created
     */
    @Test(expected = IllegalArgumentException.class)
    public void the_game_with_id_less_than_zero_can_not_be_created() {
        new Game(-1L, GameTestUtils.HOST, GameTestUtils.PLAYER);
    }

    /**
     * verifies that game with game id equal to zero can not be created
     */
    @Test(expected = IllegalArgumentException.class)
    public void the_game_with_id_zero_can_not_be_created() {
        new Game(-1L, GameTestUtils.HOST, GameTestUtils.PLAYER);
    }

    /**
     * verifies that when game created successfully it's initialized properly
     */
    @Test
    public void the_game_when_created_is_initialized_properly() {
        Game game = GameTestUtils.createNewGame();

        assertEquals(game.getId(), GameTestUtils.GAME_ID);
        assertEquals(game.getHost(), GameTestUtils.HOST);
        assertEquals(game.getPlayer(), GameTestUtils.PLAYER);
        assertEquals(game.getStatus(), GameStatus.AWAITING_INITIAL_SELECTION);

        short doorsWithWinningPrize = 0;
        short doorsWithLoosingPrize = 0;
        for(Door door : game.getDoors()) {
            if(door.getPrize().equals(Prize.ASTON_MARTIN)) {
                doorsWithWinningPrize++;
            } else if(door.getPrize().equals(Prize.GOAT)) {
                doorsWithLoosingPrize++;
            }
        }

        assertEquals(doorsWithWinningPrize, 1);
        assertEquals(doorsWithLoosingPrize, 2);
    }

    /**
     * verifies that the door revealed/open by host is not the one with winning prize
     */
    @Test
    public void the_door_opened_by_host_should_not_be_with_winning_prize() {
        Game game = GameTestUtils.createNewGame();
        Door hostDoor = game.select(game.getRandomDoor());

        assertEquals(hostDoor.getPrize(),Prize.GOAT);
    }

    /**
     * verifies that the door opened by host is not the one selected initially by the player
     */
    @Test
    public void the_door_opened_by_host_should_not_be_the_one_initially_selected_by_player() {
        Game game = GameTestUtils.createNewGame();
        Door playerDoor = game.getRandomDoor();
        Door hostDoor = game.select(playerDoor);

        assertNotEquals(playerDoor, hostDoor);
    }

    /**
     * when choosing the door with winning prize the player wins the game
     */
    @Test
    public void when_choosing_the_winning_prize_door_player_wins() {
        Game game = GameTestUtils.createNewGame();
        Door doorWithWinningPrize = null;
        for(Door door : game.getDoors()) {
            if(door.getPrize().equals(Prize.ASTON_MARTIN)) {
                doorWithWinningPrize = door;
                break;
            }
        }
        game.select(doorWithWinningPrize);
        game.open(doorWithWinningPrize);

        assertEquals(game.getStatus(), GameStatus.WON);
    }

    /**
     * when choosing the door with goat the player looses the game
     */
    @Test
    public void when_choosing_the_goat_door_player_looses() {
        Game game = GameTestUtils.createNewGame();
        Door doorWithLoosingPrize = null;
        for(Door door : game.getDoors()) {
            if(door.getPrize().equals(Prize.GOAT)) {
                doorWithLoosingPrize = door;
                break;
            }
        }
        game.select(doorWithLoosingPrize);
        game.open(doorWithLoosingPrize);

        assertEquals(game.getStatus(), GameStatus.LOST);
    }

    /**
     * when player switches door then it shouldn't be same as the one opened by the host
     */
    @Test
    public void when_switched_door_it_should_not_be_same_as_door_revealed_by_host() {
        Game game = GameTestUtils.createNewGame();
        Door initialDoorSelectedByPlayer = game.getRandomDoor();
        Door doorRevealedByTheHost = game.select(initialDoorSelectedByPlayer);
        Door switchedDoorOfPlayer = game.swap();

        assertNotEquals(doorRevealedByTheHost, switchedDoorOfPlayer);
    }

    /**
     * when player switches door then it shouldn't be same as the initial door selected by the host
     */
    @Test
    public void when_switched_door_it_should_not_be_same_as_initial_door_selected_by_host() {
        Game game = GameTestUtils.createNewGame();
        Door initialDoorSelectedByPlayer = game.getRandomDoor();
        game.select(initialDoorSelectedByPlayer);
        Door switchedDoorOfPlayer = game.swap();

        assertNotEquals(initialDoorSelectedByPlayer, switchedDoorOfPlayer);
    }

    /**
     * player can't switch door until initial door has been selected by the player
     */
    @Test(expected = IllegalStateException.class)
    public void door_can_not_be_switched_until_initial_door_has_been_selected() {
        Game game = GameTestUtils.createNewGame();
        game.swap();
    }

    /**
     * final door can not be opened until host and player have chosen their doors
     */
    @Test(expected = IllegalStateException.class)
    public void final_door_can_not_be_opened_until_host_and_player_has_chosen_doors() {
        Game game = GameTestUtils.createNewGame();
        game.open(game.getRandomDoor());
    }

    /**
     * once game is finished then game status is WON or LOST
     */
    @Test
    public void when_game_finished_then_game_status_is_won_or_lost() {
        Game game = GameTestUtils.playGame();
        GameStatus gameStatus = game.getStatus();

        assertNotEquals(gameStatus, GameStatus.AWAITING_INITIAL_SELECTION);
        assertNotEquals(gameStatus, GameStatus.AWAITING_FINAL_SELECTION);
    }

    /**
     * once game is finished then player can not select door again
     */
    @Test(expected = IllegalStateException.class)
    public void when_game_finished_then_player_can_not_select_door_again() {
        Game game = GameTestUtils.playGame();
        game.select(game.getRandomDoor());
    }

    /**
     * once game is finished then player can not swap door again
     */
    @Test(expected = IllegalStateException.class)
    public void when_game_finished_then_player_can_not_swap_door_again() {
        Game game = GameTestUtils.playGame();
        game.swap();
    }

    /**
     * once game is finished then player can not request to open a door
     */
    @Test(expected = IllegalStateException.class)
    public void when_game_finished_door_can_not_be_opened_again() {
        Game game = GameTestUtils.playGame();
        game.open(game.getRandomDoor());
    }

}