package io.aurorasolutions.montyhall.domain.common;

/**
 * Created by rasheed on 2015-03-10.
 */
public class ValidationUtil {

    /**
     * Validates long
     *
     * @param id
     * @param msg
     */
    public static void validateLong(Long id, String msg) {
        if(id == null) {
            throw new IllegalArgumentException(String.format("The %s id can't be NULL!", msg));
        }
        if(id < 0) {
            throw new IllegalArgumentException(String.format("The %s id can't be < 0!", msg));
        }
    }
}
