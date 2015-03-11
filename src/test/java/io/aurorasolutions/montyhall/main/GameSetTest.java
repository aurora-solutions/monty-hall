package io.aurorasolutions.montyhall.main;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

/**
 * Created by Rasheed on 2015-03-10.
 *
 * Test the class GameSet
 */
public class GameSetTest {

    /**
     * number of rounds/games must be greater than or equal to zero
     */
    @Test(expected = IllegalArgumentException.class)
    public void game_set_can_not_be_initialized_with_negative_number_of_games() {
        new GameSet(-1L);
    }

    /**
     * test of isItBetterToSwitch method, winsOnSwitching > winsOnNotSwitching
     */
    @Test
    public void when_wins_more_on_switching_then_its_better_to_switch() {
        //Given
        Long winsOnSwitching = 2L;
        Long winsOnNotSwitching = 1L;
        String expected = SwitchDoorBetter.YES.toString();

        //When
        String actual = GameSet.isItBetterToSwitch(winsOnSwitching,
                winsOnNotSwitching);

        //Then
        assertEquals(expected, actual);
    }

    /**
     * yest of isItBetterToSwitch method, winsOnSwitching < winsOnNotSwitching
     */
    @Test
    public void when_wins_less_on_switching_then_its_better_not_to_switch() {
        //Given
        Long winsOnSwitching = 1L;
        Long winsOnNotSwitching = 2L;
        String expected = SwitchDoorBetter.NO.toString();

        //When
        String actual = GameSet.isItBetterToSwitch(winsOnSwitching,
                winsOnNotSwitching);

        //Then
        assertEquals(expected, actual);
    }

    /**
     * test of isItBetterToSwitch method, winsOnSwitching = winsOnNotSwitching
     */
    @Test
    public void when_wins_equal_on_switching_and_not_switching_then_it_does_not_matter() {
        //Given
        Long winsOnSwitching = 1L;
        Long winsOnNotSwitching = 1L;
        String expected = SwitchDoorBetter.DOES_NOT_MATTER.toString();

        //When
        String actual = GameSet.isItBetterToSwitch(winsOnSwitching,
                winsOnNotSwitching);

        //Then
        assertEquals(expected, actual);
    }

    /**
     * test countWinsOnSwitching method; numberOfGames = 0
     */
    @Test
    public void when_number_games_is_zero_then_count_of_wins_on_switching_is_zero() {
        //Given
        Long numberOfGames = 0L;
        GameSet gameSet = new GameSet(numberOfGames);
        Long expected = 0L;

        //When
        Long actual = gameSet.countWinsOnSwitching();

        //Then
        assertEquals(expected, actual);
    }

    /**
     * test countWinsWithoutSwitching method; numberOfGames = 0
     */
    @Test
    public void when_number_games_is_zero_then_count_of_wins_without_switching_is_zero() {
        //Given
        Long numberOfGames = 0L;
        GameSet gameSet = new GameSet(numberOfGames);
        Long expected = 0L;

        //When
        Long actual = gameSet.countWinsWithoutSwitching();

        //Then
        assertEquals(expected, actual);
    }

    /**
     * number of wins on switching can not be less than zero & greater than number of games
     */
    @Test
    public void number_of_wins_on_switching_can_not_be_less_than_zero_or_greater_than_number_of_games() {
        //Given
        Long numberOfGames = 1000L;
        Long expectedMinWins = 0L;
        Long expectedMaxWins = numberOfGames;
        GameSet gameSet = new GameSet(numberOfGames);

        //When
        Long actual = gameSet.countWinsOnSwitching();

        //Then
        assertThat(actual, greaterThanOrEqualTo(expectedMinWins));
        assertThat(actual, lessThanOrEqualTo(expectedMaxWins));
    }

    /**
     * number of wins without switching can not be less than zero & greater than number of games
     */
    @Test
    public void number_of_wins_without_switching_can_not_be_less_than_zero_or_greater_than_number_of_games() {
        //Given
        Long numberOfGames = 1000L;
        Long expectedMinWins = 0L;
        Long expectedMaxWins = numberOfGames;
        GameSet gameSet = new GameSet(numberOfGames);

        //When
        Long actual = gameSet.countWinsWithoutSwitching();

        //Then
        assertThat(actual, greaterThanOrEqualTo(expectedMinWins));
        assertThat(actual, lessThanOrEqualTo(expectedMaxWins));
    }

}
