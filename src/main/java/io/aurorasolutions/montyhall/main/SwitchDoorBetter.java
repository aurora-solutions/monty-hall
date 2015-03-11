package io.aurorasolutions.montyhall.main;

/**
 * Created by Rasheed on 2015-03-11.
 */
public enum SwitchDoorBetter {
    YES("yes"),
    NO("no"),
    DOES_NOT_MATTER("no matter");

    private final String value;

    private SwitchDoorBetter(String value) {
        this.value = value;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:value.equals(otherName);
    }

    public String toString(){
        return value;
    }
}
