package io.aurorasolutions.montyhall.domain.common;

/**
 * Created by Rasheed on 2015-03-09.
 */
public class Person {

    // first name of the person
    private String firstName;
    // last name of the person
    private String lastName;

    /**
     * Public constructor
     *
     * @param firstName: First name
     * @param lastName: Last name
     */
    public Person(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    /**
     * Get first name of the person
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name of the person
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name of the person
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name of the person
     *
     * @return
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get full name of the person
     *
     * @return full name
     */
    public String getFullName() {
        return firstName.concat(" ").concat(lastName);
    }
}
