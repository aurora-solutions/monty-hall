package io.aurorasolutions.montyhall.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rasheed on 2015-03-11.
 */
public class ParsingUtilTest {

    /**
     * test toLong by passing text string value
     */
    @Test(expected = NumberFormatException.class)
    public void when_sent_string_paring_fails() {
        ParsingUtil.toLong("fail");
    }

    /**
     * test toLong by passing numeric value
     */
    @Test(expected = NumberFormatException.class)
    public void when_sent_numeric_paring_works() {
        //Given
        String numberStr = "5L";
        Long expected = 5L;

        //When
        Long actual = ParsingUtil.toLong(numberStr);

        //Then
        assertEquals(expected, actual);
    }

}
