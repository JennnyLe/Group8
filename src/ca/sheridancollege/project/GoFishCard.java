/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author JennyLe
 * @author Sad
 * @author destinyodia
 * @author Christeena
 * Date: 19 Feb 2024
 */

// Enum representing card ranks
enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

// Enum representing card suits
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}
public class GoFishCard extends Card {
    
    private final Rank rank;
    private final Suit suit;

    public GoFishCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //get rank
    public Rank getRank() {
        return rank;
    }

   

    //get suit
    public Suit getSuit() {
        return suit;
    }
    
    /**
     *
     * @return rank and suit for toString method
     */
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
    
}
