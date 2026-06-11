import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void newHandIsEmpty() {
        assertEquals(0, new Hand().size());
    }

    @Test
    void addCardIncreasesSize() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.ACE, Suit.SPADES));
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        assertEquals(2, hand.size());
    }

    @Test
    void addedCardIsStored() {
        Hand hand = new Hand();
        Card card = new Card(Rank.KING, Suit.CLUBS);
        hand.addCard(card);
        assertTrue(hand.getCards().contains(card));
    }

    @Test
    void addingNullCardThrows() {
        assertThrows(NullPointerException.class, () -> new Hand().addCard(null));
    }
}
