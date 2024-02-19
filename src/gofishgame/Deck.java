package gofishgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 *
 * @author saadkhan, jennyle, destinyodia
 *
 */
// Class representing a deck of cards
class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public int size() {
        return cards.size();
    }
}
