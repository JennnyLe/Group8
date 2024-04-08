package gofishgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * This class represents the deck of cards in the gameplay
 * and is responsible for shuffling and dealing cards.
 * @author saadkhan, jennyle, destinyodia
 */

// Class representing a deck of cards
class Deck {
    private final List<Card> cards;

    // Initialize the deck and shuffle
    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        shuffle();
    }

    // Method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Method to deal a card
    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    // Method to get the size of the deck to know how many cards are in the deck
    public int size() {
        return cards.size();
    }
}
