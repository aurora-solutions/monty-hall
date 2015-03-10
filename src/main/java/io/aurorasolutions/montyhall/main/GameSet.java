package io.aurorasolutions.montyhall.main;

import io.aurorasolutions.montyhall.domain.door.Door;
import io.aurorasolutions.montyhall.domain.game.Game;
import io.aurorasolutions.montyhall.domain.host.Host;
import io.aurorasolutions.montyhall.domain.player.Player;

/**
 * Created by Rasheed on 2015-03-10.
 *
 * Run a set of Monty Hall game
 */
public class GameSet {

    // player
    private Player player;
    // host
    private Host host;
    // number of games
    private Long numberOfGames;

    /**
     * Public constructor
     */
    public GameSet(Long numberOfGames) {
        this.setNumberOfGames(numberOfGames);
        player = new Player("Rasheed", "Amir");
        host = new Host("Nasdaq", "OMX");
    }

    /**
     * Get player of the game
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get host of the game
     *
     * @return host
     */
    public Host getHost() {
        return host;
    }

    /**
     * Validates and sets number of games
     *
     * @throws IllegalArgumentException Only positive number
     *
     * @param numberOfGames
     */
    private void setNumberOfGames(Long numberOfGames) {
        if(numberOfGames < 0) {
            throw new IllegalArgumentException("The number of games you want to play must be > 0!");
        }
        this.numberOfGames = numberOfGames;
    }

    /**
     * Run $numberOfGames games where the player switch the door after the host reveals a door; count the wins!
     *
     * @return number of wins
     */
    private Long countWinsOnSwitching() {

        Long countWins = 0L;

        for (Long i = 0L; i < numberOfGames; i++) {
            Long gameId = i + 1L;
            Game game = new Game(gameId, host, player);
            Door playerDoor = getPlayerDoor(game);
            game.select(playerDoor);
            playerDoor = game.swap();
            game.open(playerDoor);
            countWins += game.didPlayerWin() ? 1 : 0;
        }

        return countWins;
    }

    /**
     * Run $numberOfGames games where the player DOESN'T switch the door after the host reveals a door; count the wins!
     *
     * @return number of wins
     */
    private Long countWinsWithoutSwitching() {

        Long countWins = 0L;

        for (Long i = 0L; i < numberOfGames; i++) {
            Long gameId = i + 1L;
            Game game = new Game(gameId, host, player);
            Door playerDoor = getPlayerDoor(game);
            game.select(playerDoor);
            game.open(playerDoor);
            countWins += game.didPlayerWin() ? 1 : 0;
        }

        return countWins;
    }

    /**
     * Randomly selects a door for player
     *
     * @param game game
     * @return door selected by player
     */
    private Door getPlayerDoor(Game game) {
        return game.getDoors()[game.getRandomDoorNumber() - 1];
    }

    /**
     * Is it better to switch? winsOnSwitching > winsOnNotSwitching : yes
     * winsOnSwitching < winsOnNotSwitching : no winsOnSwitching = winsOnNotSwitching
     * : no matter
     *
     * @param winsOnSwitching Number of wins when switching door
     * @param winsWithoutSwitching Number of wins when not switching door
     *
     * @return "no matter", "yes" or "no"
     */
    static String isItBetterToSwitch(Long winsOnSwitching, Long winsWithoutSwitching) {
        if (winsOnSwitching == winsWithoutSwitching) {
            return "no matter";
        }
        return winsOnSwitching > winsWithoutSwitching ? "yes" : "no";
    }

    /**
     * Main entry point!
     *
     * @param args number of games!
     */
    public static void main(String[] args) {
        try {
            System.out.println("\nStarting Monty Hall Game! Get Ready...");

            Long numberOfGames = 1000L;

            GameSet gameSet = new GameSet(numberOfGames);
            System.out.println(String.format("\nWarm welcome to %s by %s", gameSet.getPlayer().getFullName(),
                    gameSet.getHost().getFullName()));

            Long winsWithoutSwitching = gameSet.countWinsWithoutSwitching();
            Long winsOnSwitching = gameSet.countWinsOnSwitching();

            System.out.println("\nNumber of games: " + numberOfGames);
            System.out.println("-----------------------");
            System.out.println(
                    "Number of wins when the player do not switch door: "
                            + winsWithoutSwitching);
            System.out.println(
                    "Number of wins when the player do switch door:     "
                            + winsOnSwitching);
            System.out.println(
                    "Is it better to switch the door?                   "
                            + isItBetterToSwitch(winsOnSwitching, winsWithoutSwitching) + "\n");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
