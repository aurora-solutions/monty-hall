package io.aurorasolutions.montyhall.domain.common;

/**
 * Created by Rasheed on 2015-03-09.
 */
public abstract class Person implements IEntity<Person> {

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

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameIdentityAs(final Person other) {
        return other != null
                && this.getFirstName().equals(other.getFirstName())
                && this.getLastName().equals(other.getLastName());
    }

    /**
     * @param object
     *            to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Person)
     */
    @Override
    public boolean equals(final Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        final Person other = (Person) object;
        return sameIdentityAs(other);
    }

    //TODO: Implement toString()
    //TODO: Implement hashCode()
}
