package io.aurorasolutions.montyhall.domain.door;

import io.aurorasolutions.montyhall.domain.prize.Prize;

/**
 * Created by Rasheed on 2015-03-09.
 */
public class Door implements Comparable<Door> {

    // a unique identifier of the door
    private int doorId;
    // prize hidden behind the door
    private final Prize prize;
    // door status
    private DoorStatus doorStatus = DoorStatus.CLOSED;

    /**
     * Constructs a door
     *
     * @param doorId : unique identifier of the door
     * @param prize : prize which the door hides
     */
    public Door(int doorId, Prize prize) {
        this.setDoorId(doorId);
        this.prize = prize;
    }

    /**
     * Returns the prize which is hidden behind the door
     *
     * @return
     */
    public Prize getPrize() {
        return this.prize;
    }

    /**
     * Validates the arguments and sets the value
     *
     * @throws IllegalArgumentException Only positive number
     *
     * @param doorId
     */
    private void setDoorId(int doorId) {
        if(doorId < 0) {
            throw new IllegalArgumentException("The door id must be > 0!");
        }
        this.doorId = doorId;
    }

    /**
     * Return the unique identifier of the door
     *
     * @return
     */
    public int getDoorId() {
        return  this.doorId;
    }

    /**
     * Return the door status
     *
     * @return
     */
    public DoorStatus getDoorStatus(){
        return this.getDoorStatus();
    }

    /**
     *
     */
    public void reveal() {
        this.doorStatus = DoorStatus.OPENED;
    }

    /**
     *
     * @param newDoorStatus
     */
    public void changeDoorStatus(DoorStatus newDoorStatus) {
        boolean illegalTransitionAttempted = false;
        switch (newDoorStatus) {
            case CLOSED :
                if (this.doorStatus != DoorStatus.CLOSED) {
                    illegalTransitionAttempted = true;
                }
                break;
            case SELECTED:
                if (this.doorStatus == DoorStatus.OPENED) {
                    illegalTransitionAttempted = true;
                }
                break;
            case OPENED :
                break;
        }
        if (illegalTransitionAttempted) {
            throw new IllegalStateException("Cannot transition to " + newDoorStatus + " from " + this.doorStatus);
        }
        this.doorStatus = newDoorStatus;
    }

    /**
     * Compares two doors
     *
     * @param otherDoor the door to compare with
     * @return
     */
    @Override
    public int compareTo(final Door otherDoor) {
        return Integer.compare(this.doorId, otherDoor.doorId);
    }
}