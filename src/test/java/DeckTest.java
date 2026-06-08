import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
}