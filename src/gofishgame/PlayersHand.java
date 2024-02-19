package gofishgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 *
 * @author saadkhan, jennyle, destinyodia
 *
 */
// Class representing a player's hand and books
public class PlayersHand {
    private List<Card> cards;
    private List<Rank> books;

    // Constructor
    public PlayersHand() {
        cards = new ArrayList<>();
        books = new ArrayList<>();
    }

    // Method to count the number of cards with a given rank in the hand
    public int countRank(Rank rank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank() == rank) {
                count++;
            }
        }
        return count;
    }

    // Method to add a card to the hand
    public void addCard(Card card) {
        cards.add(card);
    }

    // Method to get the cards in the hand
    public List<Card> getCards() {
        return cards;
    }

    // Method to add a book (a set of four cards with the same rank)
    public void addBook(Rank rank) {
        books.add(rank);
    }

    // Method to get the books (sets of four cards) in the hand
    public List<Rank> getBooks() {
        return books;
    }

    // Method to check if the hand has a book of a given rank
    public boolean hasRank(Rank rank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank() == rank) {
                count++;
            }
        }
        return count == 4;
    }

    // Method to remove all cards of a given rank from the hand
    public boolean removeRank(Rank rank) {
        Iterator<Card> iterator = cards.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getRank() == rank) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }
}
