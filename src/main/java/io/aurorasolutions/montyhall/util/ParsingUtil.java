package io.aurorasolutions.montyhall.util;

/**
 * Created by Rasheed on 2015-03-11.
 */
public class ParsingUtil {

    /**
     * Parse the argument and try convert to a Long
     *
     * @throws IllegalArgumentException No arguments
     * @throws NumberFormatException If the string does not contain a parsable long.
     *
     * @param numberStr a number as string.
     *
     * @return argument as a number
     */
    public static Long toLong(String numberStr)
    {
        try {
            return Long.parseLong(numberStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The argument format is not valid");
        }
    }
}
