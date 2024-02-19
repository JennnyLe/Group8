/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author destinyodia
 */
public class PlayerHand {
    private List<GoFishCard> cards;
    private List<Rank> books;


    public int countRank(Rank rank) {
        int count = 0;
            for (GoFishCard card : cards) {
        if (card.getRank() == rank) {
            count++;
        }
    }
    return count;
}

    public PlayerHand() {
        cards = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void addCard(GoFishCard card) {
        cards.add(card);
    }

    public List<GoFishCard> getCards() {
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
        for (GoFishCard card : cards) {
            if (card.getRank() == rank) {
                count++;
            }
        }
        return count == 4;
    }

    public boolean removeRank(Rank rank) {
        Iterator<GoFishCard> iterator = cards.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            GoFishCard card = iterator.next();
            if (card.getRank() == rank) {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }
    
}
