package gofishgame;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


// Class representing the Go Fish game
public class GoFishGame {
    private Deck deck;
    private Hand playerHand;
    private Hand computerHand;

    public GoFishGame() {
        deck = new Deck();
        playerHand = new Hand();
        computerHand = new Hand();
        dealHands();
    }
    
   private void printGameRules() {
        try {
            // Print out game rules
            Thread.sleep(2000);
            System.out.println("\n=== Go Fish Game Rules ===");
            Thread.sleep(1000);
            System.out.println("The Pack: The standard 52-card pack is used.");
            Thread.sleep(1000);
            System.out.println("Object of the Game: Win the most \"books\" of cards.");
            Thread.sleep(1000);
            System.out.println("Rank of Cards: Only the card numbers matter, not the suits.");
            Thread.sleep(1000);
            System.out.println("The Deal: Each player receives seven cards for 2-3 players, or five cards for 4-5 players. The remaining cards form the stock pile.");
            Thread.sleep(1000);
            System.out.println("The Play: Players take turns asking each other for specific card ranks.");
            Thread.sleep(1000);
            System.out.println("If the asked player has the requested cards, they must give them all to the asking player.");
            Thread.sleep(1000);
            System.out.println("If not, the asking player must \"go fish\" by drawing a card from the stock pile.");
            Thread.sleep(1000);
            System.out.println("If the asking player receives one or more cards of the requested rank, they can continue their turn by asking another player for a different card.");
            Thread.sleep(1000);
            System.out.println("When a player collects all four cards of a rank, they form a book and show it to all players.");
            Thread.sleep(1000);
            System.out.println("The game continues until all thirteen books have been formed.");
            Thread.sleep(1000);
            System.out.println("The winner is the player with the most books at the end.");
            Thread.sleep(1000);
            System.out.println("If a player runs out of cards during the game, they can draw from the stock pile and continue playing.");
            Thread.sleep(1000);
            System.out.println("If the stock pile is empty, they are out of the game.\n");
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
        }
}


    private void dealHands() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(deck.dealCard());
            computerHand.addCard(deck.dealCard());
        }
    }

    public Rank promptForRank(Scanner scanner) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Please ask Player B for a rank (e.g. ACE, TWO, THREE etc.): ");
        String input = scanner.nextLine().toUpperCase();
            return Rank.valueOf(input);
    }

    public void checkBooks(Hand hand) {
        for (Rank rank : Rank.values()) {
        if (hand.countRank(rank) == 4) { // If there are 4 cards of the same rank, remove them and add a book
            hand.removeRank(rank);
            hand.addBook(rank);
            System.out.println("Player B has a book of " + rank);
        }
    }
}

    public Rank getRandomRank(Hand hand) {
        List<Card> cards = hand.getCards();
        Random random = new Random();
            return cards.get(random.nextInt(cards.size())).getRank();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        
        // Ask for player's name
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        
        // Print game rules
        printGameRules();
        
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.printf("%s's Hand: %s%n", playerName, playerHand.getCards());
            System.out.printf("%s's Books: %s%n", playerName, playerHand.getBooks());
            Rank rank = promptForRank(scanner);
        
      
    // Check if Player B has the requested rank
    boolean playerBHasRank = false;
    for (Card card : computerHand.getCards()) {
        if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
        playerBHasRank = true;
        break;
        }
    }

    if (playerBHasRank) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
                }
        System.out.println("Congratulations! Computer has " + rank);
        // Transfer all cards with the requested rank from Player B to Player A
        for (Iterator<Card> iterator = computerHand.getCards().iterator(); iterator.hasNext();) {
            Card card = iterator.next();
        if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
            playerHand.addCard(card);
            iterator.remove();
            }
        }
    }
    else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.println("Sorry, Computer does not have that card, go fish");
            Card card = deck.dealCard();
            if (card != null) {
                playerHand.addCard(card);
                System.out.printf("%s drew: %s%n", playerName, card);
            }
        }
        checkBooks(playerHand);
        if (playerHand.getCards().isEmpty() || deck.size() == 0) {
            break;
        }

        rank = getRandomRank(computerHand);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("Computer asks for: " + rank);
        
        // Check if Player A has the requested rank
    boolean playerAHasRank = false;
    for (Card card : playerHand.getCards()) {
    if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
        playerAHasRank = true;
        break;
        }
    }

    if (playerAHasRank) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
                }
    System.out.printf("%s has %s%n", playerName, rank);
    // Transfer all cards with the requested rank from Player A to Player B
    for (Iterator<Card> iterator = playerHand.getCards().iterator(); iterator.hasNext();) {
        Card card = iterator.next();
        if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
            computerHand.addCard(card);
            iterator.remove();
        }
    }
}
 else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.printf("%s does not have that card, go fish%n", playerName);
            Card card = deck.dealCard();
            if (card != null) {
                computerHand.addCard(card);
            }
        }
        checkBooks(computerHand);
        if (computerHand.getCards().isEmpty() || deck.size() == 0) {
            break;
        }
    }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GoFishGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    System.out.println("Game over");
    System.out.printf("%s's Books: %s%n", playerName, playerHand.getBooks());
    System.out.println("Computer's Books: " + computerHand.getBooks());
    int playerBooks = playerHand.getBooks().size();
    int computerBooks = computerHand.getBooks().size();
    if (playerBooks > computerBooks) {
        System.out.printf("%s wins!%n", playerName);
    } else if (computerBooks > playerBooks) {
        System.out.println("Computer wins!");
    } else {
        System.out.println("It's a tie!");
    }
}

    public static void main(String[] args) {
        GoFishGame game = new GoFishGame();
        game.play();
    }
}
