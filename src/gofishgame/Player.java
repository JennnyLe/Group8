package gofishgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Class representing a player's hand and books
class Hand {
    private List<Card> cards;
    private List<Rank> books;


    public int countRank(Rank rank) {
        int count = 0;
            for (Card card : cards) {
        if (card.getRank() == rank) {
            count++;
        }
    }
    return count;
}

    public Hand() {
        cards = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addBook(Rank rank) {
        books.add(rank);
    }

    public List<Rank> getBooks() {
        return books;
    }

    public boolean hasRank(Rank rank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank() == rank) {
                count++;
            }
        }
        return count == 4;
    }

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
