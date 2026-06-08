import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Standardowa talia 52 kart — 4 kolory × 13 figur (spec p. 2.1).
 * Konstruktor buduje pełną, uporządkowaną talię bez duplikatów.
 */
public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public int size() {
        return cards.size();
    }

    /** Zwraca niemodyfikowalny widok kart w talii. */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}