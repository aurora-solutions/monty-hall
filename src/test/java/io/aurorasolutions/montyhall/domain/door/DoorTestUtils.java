package io.aurorasolutions.montyhall.domain.door;

import io.aurorasolutions.montyhall.domain.prize.Prize;

/**
 * Created by Rasheed on 2015-03-11.
 */
public class DoorTestUtils {

    public static final int DOOR_ID = 1;
    public static final Prize PRIZE = Prize.ASTON_MARTIN;

    /**
     * Creates a new door
     *
     * @return a new door
     */
    public static Door createNewDoor() {
        return new Door(DOOR_ID, PRIZE);
    }

}
