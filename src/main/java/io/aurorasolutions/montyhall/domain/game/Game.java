package io.aurorasolutions.montyhall.domain.game;

import io.aurorasolutions.montyhall.domain.common.IEntity;
import io.aurorasolutions.montyhall.domain.common.ValidationUtil;
import io.aurorasolutions.montyhall.domain.door.Door;
import io.aurorasolutions.montyhall.domain.door.DoorStatus;
import io.aurorasolutions.montyhall.domain.host.Host;
import io.aurorasolutions.montyhall.domain.player.Player;
import io.aurorasolutions.montyhall.domain.prize.Prize;

import java.util.*;

/**
 * Created by Rasheed on 2015-03-09.
 *
 * Playing the game Monty Hall! Should the player switch the door or not?
 */
public class Game implements IEntity<Game> {

    // unique identifier for the game
    private Long id;
    // host of the game
    private Host host;
    // player in the game
    private Player player;
    // three doors
    private Door[] doors = new Door[3];
    // game initial status
    private GameStatus status = GameStatus.AWAITING_INITIAL_SELECTION;
    // game events history
    private List<GameEvent> history = new ArrayList<GameEvent>();
    // door revealed by host
    private Door doorRevealedByHost;
    // door selected by player
    private Door doorSelectedByPlayer;

    /**
     * Start a game with car location is random
     *
     * @param id
     * @param host
     * @param player
     */
    public Game(Long id, Host host, Player player) {
        this.setId(id);
        this.setHost(host);
        this.setPlayer(player);
        this.initializeDoors();
    }

    /**
     * A factory method which initializes doors.
     */
    private void initializeDoors() {
        int doorWithAstonMartin = getRandomDoorNumber() - 1;
        doors[0] = (doorWithAstonMartin == 0) ? new Door(1, Prize.ASTON_MARTIN) : new Door(1, Prize.GOAT);
        doors[1] = (doorWithAstonMartin == 1) ? new Door(2, Prize.ASTON_MARTIN) : new Door(2, Prize.GOAT);
        doors[2] = (doorWithAstonMartin == 2) ? new Door(3, Prize.ASTON_MARTIN) : new Door(3, Prize.GOAT);
    }

    /**
     * Get game id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Validates the input and sets the door id
     *
     * @throws IllegalArgumentException Only positive number
     *
     * @param id door id
     */
    private void setId(Long id) {
        ValidationUtil.validateLong(id, "game");
        this.id = id;
    }

    /**
     * Get game host
     *
     * @return
     */
    public Host getHost() {
        return host;
    }

    /**
     * Set game host
     *
     * @param host
     */
    private void setHost(Host host) {
        this.host = host;
    }

    /**
     * Get player in the game
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set player in the game
     *
     * @return
     */
    private void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get list of doors
     *
     * @return
     */
    public Door[] getDoors() {
        return doors;
    }

    /**
     * Get game status
     *
     * @return
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Get game events history
     *
     * @return
     */
    public List<GameEvent> getHistory() {
        return history;
    }

    /**
     * Get a number 1, 2, or 3, randomize.
     *
     * @return 1, 2 or 3
     */
    public int getRandomDoorNumber() {
        int numberOfDoors = 3;
        Random random = new Random();
        int randomDoorNumber = random.nextInt(numberOfDoors) + 1;
        return randomDoorNumber;
    }

    /**
     * Get a random door
     *
     * @return a random door
     */
    public Door getRandomDoor() {
        return this.getDoors()[this.getRandomDoorNumber() - 1];
    }

    /**
     * Did the player win the game?
     *
     * @return true or false
     */
    public boolean didPlayerWin() {
        return (status == GameStatus.WON);
    }

    /**
     * Mark the door chosen by player as SELECTED and return the door revealed by host
     *
     * @param door selected by the player
     * @return door revealed by the host
     */
    public Door select(Door door) {
        if (this.status != GameStatus.AWAITING_INITIAL_SELECTION) {
            throw new IllegalStateException("Can't select a door from state " + this.status);
        }

        Door toSelect = this.doors[door.getDoorId() - 1];
        toSelect.updateDoorStatus(DoorStatus.SELECTED);
        doorSelectedByPlayer = toSelect;

        GameEvent selectEvent =  door.getDoorId() == 1
                ? GameEvent.SELECTED_DOOR_ONE
                : (door.getDoorId() == 2 ? GameEvent.SELECTED_DOOR_TWO : GameEvent.SELECTED_DOOR_THREE);
        this.history.add(selectEvent);

        Door toReveal = reveal(door);
        int doorToReveal = toReveal.getDoorId() - 1;

        GameEvent revealEvent = doorToReveal == 0
                ? GameEvent.REVEALED_DOOR_ONE
                : (doorToReveal == 1 ? GameEvent.REVEALED_DOOR_TWO : GameEvent.REVEALED_DOOR_THREE);
        this.history.add(revealEvent);

        this.status = GameStatus.AWAITING_FINAL_SELECTION;

        return toReveal;
    }

    /**
     * Randomly reveals a door
     *
     * @return door revealed by the host
     */
    private Door reveal(Door selectedByPlayer) {

        int doorToReveal = getRandomDoorNumber() - 1;
        while ( (this.doors[doorToReveal] == selectedByPlayer) || (this.doors[doorToReveal].getPrize() == Prize.ASTON_MARTIN) ) {
            doorToReveal = getRandomDoorNumber() - 1;
        }

        Door toReveal = this.doors[doorToReveal];
        toReveal.reveal();
        doorRevealedByHost = toReveal;

        return toReveal;
    }

    /**
     * The player can switch the door after the host opens one of the doors.
     *
     * @return switched door
     */
    public Door swap() {
        if (this.status != GameStatus.AWAITING_FINAL_SELECTION) {
            throw new IllegalStateException("Can't swap a door from state " + this.status);
        }

        TreeSet<Door> doorsSet = new TreeSet<Door>(Arrays.asList(doors));
        doorsSet.remove(doorSelectedByPlayer);
        doorsSet.remove(doorRevealedByHost);
        Door switchedDoor = doorsSet.first();
        switchedDoor.updateDoorStatus(DoorStatus.SELECTED);
        // update the door selected by player
        this.doorSelectedByPlayer = switchedDoor;

        GameEvent switchedEvent =  switchedDoor.getDoorId() == 1
                ? GameEvent.SELECTED_DOOR_ONE
                : (switchedDoor.getDoorId() == 2 ? GameEvent.SELECTED_DOOR_TWO : GameEvent.SELECTED_DOOR_THREE);
        this.history.add(switchedEvent);

        return switchedDoor;
    }

    /**
     * Opens a door and returns the game status.
     *
     * @param door to open
     * @return game won or lost
     */
    public GameStatus open(Door door) {
        if (this.status != GameStatus.AWAITING_FINAL_SELECTION) {
            throw new IllegalStateException("Can't open a door from state " + this.status);
        }

        Door toOpen = this.doors[door.getDoorId() -1];
        toOpen.updateDoorStatus(DoorStatus.OPENED);

        Prize prize = toOpen.getPrize();
        if (prize == Prize.ASTON_MARTIN) {
            this.status = GameStatus.WON;
        }
        else {
            this.status = GameStatus.LOST;
        }

        GameEvent openEvent = (door.getDoorId() == 1
                ? GameEvent.OPENED_DOOR_ONE
                : (door.getDoorId() == 2 ? GameEvent.OPENED_DOOR_TWO : GameEvent.OPENED_DOOR_THREE));
        this.history.add(openEvent);

        if (this.status == GameStatus.WON) {
            this.history.add(GameEvent.WON);
        } else {
            this.history.add(GameEvent.LOST);
        }

        return this.status;
    }

    /**
     *
     * @param other game
     * @return true of false
     */
    @Override
    public boolean sameIdentityAs(final Game other) {
        return other != null
                && this.getId().equals(other.getId());
    }

    /**
     * @param object
     *            to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Game)
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        final Game other = (Game) object;
        return sameIdentityAs(other);
    }

    //TODO: Implement toString()
    //TODO: Implement hashCode()
}
