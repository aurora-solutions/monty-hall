package io.aurorasolutions.montyhall.domain.host;

import io.aurorasolutions.montyhall.domain.common.Person;

/**
 * Created by Rasheed on 2015-03-09.
 */
public class Host extends Person {

    /**
     * Public constructor which calls it's parent constructor
     *
     * @param firstName: Host first name
     * @param lastName: Host last name
     */
    public Host(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
