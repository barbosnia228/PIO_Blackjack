import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DeckTest {

    @Test
    void freshDeckHas52Cards() {
        int expected = Suit.values().length * Rank.values().length;
        assertEquals(expected, new Deck().size());
    }

    @Test
    void freshDeckHasNoDuplicates() {
        Deck deck = new Deck();
        Set<Card> unique = new HashSet<>(deck.getCards());
        assertEquals(deck.size(), unique.size());
    }

    @Test
    void freshDeckContainsEveryRankSuitCombination() {
        Set<Card> cards = new HashSet<>(new Deck().getCards());
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                assertTrue(cards.contains(new Card(rank, suit)), "Brak karty: " + rank + " " + suit);
            }
        }
    }

    @Test
    void drawReturnsACard() {
        assertNotNull(new Deck().draw());
    }

    @Test
    void drawDecreasesSizeByOne() {
        Deck deck = new Deck();
        int before = deck.size();
        deck.draw();
        assertEquals(before - 1, deck.size());
    }

    @Test
    void drawnCardIsRemovedFromDeck() {
        Deck deck = new Deck();
        Card drawn = deck.draw();
        assertFalse(deck.getCards().contains(drawn));
    }

    @Test
    void drawingFromEmptyDeckThrows() {
        Deck deck = new Deck();
        int total = deck.size();
        for (int i = 0; i < total; i++) {
            deck.draw();
        }
        assertTrue(deck.isEmpty());
        assertThrows(IllegalStateException.class, deck::draw);
    }

    @Test
    void shuffleKeepsSameSetOf52Cards() {
        Deck deck = new Deck();
        List<Card> before = new ArrayList<>(deck.getCards());

        deck.shuffle(new Random(42));
        List<Card> after = deck.getCards();

        assertEquals(before.size(), after.size());
        assertEquals(after.size(), new HashSet<>(after).size());
        assertEquals(new HashSet<>(before), new HashSet<>(after));
    }

    @Test
    void shuffleWithSameSeedIsDeterministic() {
        Deck a = new Deck();
        Deck b = new Deck();

        a.shuffle(new Random(123));
        b.shuffle(new Random(123));

        assertEquals(a.getCards(), b.getCards());
    }

}