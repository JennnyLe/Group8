package gofishgame;

/*
 * This class represents a player in the game.
 * @author saadkhan, jennyle, destinyodia
 *
 */

// Class representing a player (gives player an ID and a hand of cards)
class Player {
    private String id;
    private PlayersHand hand;

    // Initializes a new player and creates a hand
    public Player(String id) {
        this.id = id;
        hand = new PlayersHand();
    }

    // Gets the player's ID
    public String getId() {
        return id;
    }

    // Gets the player's hand object
    public PlayersHand getHand() {
        return hand;
    }
}

