package gofishgame;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


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

    private void dealHands() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(deck.dealCard());
            computerHand.addCard(deck.dealCard());
        }
    }

    public Rank promptForRank(Scanner scanner) {
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
        while (true) {
            System.out.println("Player A's Hand: " + playerHand.getCards());
            System.out.println("Player A's Books: " + playerHand.getBooks());
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
        System.out.println("Congratulations! Player B has " + rank);
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
            System.out.println("Sorry, Player B does not have that card, go fish");
            Card card = deck.dealCard();
            if (card != null) {
                playerHand.addCard(card);
                System.out.println("Player A drew: " + card);
            }
        }
        checkBooks(playerHand);
        if (playerHand.getCards().isEmpty() || deck.size() == 0) {
            break;
        }

        rank = getRandomRank(computerHand);
        System.out.println("Player B asks for: " + rank);
        
        // Check if Player A has the requested rank
    boolean playerAHasRank = false;
    for (Card card : playerHand.getCards()) {
    if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
        playerAHasRank = true;
        break;
        }
    }

    if (playerAHasRank) {
    System.out.println("Player A has " + rank);
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
            System.out.println("Player A does not have that card, go fish");
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
    System.out.println("Game over");
    System.out.println("Player A's Books: " + playerHand.getBooks());
    System.out.println("Player B's Books: " + computerHand.getBooks());
    int playerBooks = playerHand.getBooks().size();
    int computerBooks = computerHand.getBooks().size();
    if (playerBooks > computerBooks) {
        System.out.println("Player A wins!");
    } else if (computerBooks > playerBooks) {
        System.out.println("Player B wins!");
    } else {
        System.out.println("It's a tie!");
    }
}

    public static void main(String[] args) {
        GoFishGame game = new GoFishGame();
        game.play();
    }
}
