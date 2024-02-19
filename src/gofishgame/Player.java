
package gofishgame;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author jennyle
 *
 */
// Class representing a player
class Player {
    private String id;
    private PlayersHand hand;

    public Player(String id) {
        this.id = id;
        hand = new PlayersHand();
    }

    public String getId() {
        return id;
    }

    public PlayersHand getHand() {
        return hand;
    }
}