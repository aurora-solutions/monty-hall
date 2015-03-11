package io.aurorasolutions.montyhall.domain.door;

import io.aurorasolutions.montyhall.domain.prize.Prize;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Rasheed on 2015-03-10.
 *
 * Tests of the domain Door
 */
public class DoorTest {

    /**
     * the door id can not be less than zero
     */
    @Test(expected = IllegalArgumentException.class)
    public void the_door_id_can_not_be_less_than_zero() {
        new Door(-1, Prize.ASTON_MARTIN);
    }

    /**
     * the door id can't be equal to zero
     */
    @Test(expected = IllegalArgumentException.class)
    public void the_door_id_can_not_be_zero() {
        new Door(-1, Prize.ASTON_MARTIN);
    }

    /**
     * the door is created when valid parameters
     */
    @Test
    public void the_door_is_created_when_passed_valid_parameters() {
        Door door = DoorTestUtils.createNewDoor();

        assertEquals(door.getDoorId(), DoorTestUtils.DOOR_ID);
        assertEquals(door.getPrize(), DoorTestUtils.PRIZE);
        assertEquals(door.getDoorStatus(), DoorStatus.CLOSED);
    }

    /**
     * verifies that door status is open when revealed
     */
    @Test
    public void the_door_status_is_opened_when_revealed() {
        Door door = DoorTestUtils.createNewDoor();
        door.reveal();

        assertEquals(door.getDoorStatus(), DoorStatus.OPENED);
    }

    /**
     * verifies that player can not switch to opened door
     */
    @Test(expected = IllegalStateException.class)
    public void the_opened_door_can_not_be_selected() {
        Door door = DoorTestUtils.createNewDoor();
        door.reveal();
        door.updateDoorStatus(DoorStatus.SELECTED);
    }

    /**
     * verifies that a revealed (open) door can not be closed
     */
    @Test(expected = IllegalStateException.class)
    public void the_opened_door_can_not_be_closed() {
        Door door = DoorTestUtils.createNewDoor();
        door.reveal();
        door.updateDoorStatus(DoorStatus.CLOSED);
    }

    /**
     * verifies that a closed door can be selected by the player
     */
    @Test
    public void the_closed_door_can_be_selected_by_player() {
        Door door = DoorTestUtils.createNewDoor();
        door.updateDoorStatus(DoorStatus.SELECTED);

        assertEquals(door.getDoorStatus(), DoorStatus.SELECTED);
    }

    /**
     * verifies that a selected door can not be closed because it's already closed
     */
    @Test(expected = IllegalStateException.class)
    public void the_selected_door_can_not_be_closed_because_its_already_closed() {
        Door door = DoorTestUtils.createNewDoor();
        door.updateDoorStatus(DoorStatus.SELECTED);
        door.updateDoorStatus(DoorStatus.CLOSED);
    }

    /**
     * verifies that two doors with same id's are equal
     */
    @Test
    public void compare_that_two_doors_with_same_door_id_are_equal() {
        Door door1 = DoorTestUtils.createNewDoor();
        Door door2 = DoorTestUtils.createNewDoor();

        assertEquals(0, door1.compareTo(door2));
    }

    /**
     * verifies that two doors with different id's are not equal
     */
    @Test
    public void compare_that_two_doors_with_different_door_id_are_not_equal() {
        Door door1 = DoorTestUtils.createNewDoor();
        Door door2 = new Door(2, Prize.GOAT);

        assertEquals(-1, door1.compareTo(door2));
    }
}
