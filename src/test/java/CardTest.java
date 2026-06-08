import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @ParameterizedTest
    @CsvSource({
            "TWO,2", "THREE,3", "FOUR,4", "FIVE,5", "SIX,6",
            "SEVEN,7", "EIGHT,8", "NINE,9", "TEN,10"
    })
    void numericRanksHaveFaceValue(Rank rank, int expected) {
        assertEquals(expected, new Card(rank, Suit.HEARTS).getValue());
    }

    @ParameterizedTest
    @EnumSource(value = Rank.class, names = {"JACK", "QUEEN", "KING"})
    void faceCardsAreWorthTen(Rank rank) {
        assertEquals(10, new Card(rank, Suit.HEARTS).getValue());
    }

    @Test
    void aceIsWorthEleven() {
        assertEquals(11, new Card(Rank.ACE, Suit.HEARTS).getValue());
    }

    @Test
    void cardsWithSameRankAndSuitAreEqual() {
        Card a = new Card(Rank.TEN, Suit.SPADES);
        Card b = new Card(Rank.TEN, Suit.SPADES);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void cardsWithDifferentRankOrSuitAreNotEqual() {
        Card base = new Card(Rank.TEN, Suit.SPADES);
        assertNotEquals(base, new Card(Rank.NINE, Suit.SPADES));
        assertNotEquals(base, new Card(Rank.TEN, Suit.HEARTS));
    }
}