package gofishgame;
/*
 *
 * @author saadkhan, jennyle, destinyodia
 *
 */
// Enum representing card ranks
enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
}

// Enum representing card suits
enum Suit {
    HEARTS, DIAMONDS, CLUBS, SPADES
}

// Class representing a playing card
class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
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

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
