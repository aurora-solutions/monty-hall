package io.aurorasolutions.montyhall.domain.player;

import io.aurorasolutions.montyhall.domain.common.Person;

/**
 * Created by Rasheed on 2015-03-09.
 */
public class Player extends Person {

    /**
     * Public constructor which calls it's parent constructor
     *
     * @param firstName: Player first name
     * @param lastName: Player last name
     */
    public Player(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
