package gofishgame;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


// Class representing the Go Fish game
public class GoFishGame {
    private Deck deck;
    private Player playerA;
    private Player playerB;

    //initialize a new deck and creates 2 players
    public GoFishGame(String playerAId) {
        deck = new Deck();
        playerA = new Player(playerAId); //playerA enters unique ID
        playerB = new Player("Computer");//playerB is computer
        dealHands();
    }
    
    //deal 7 cards to each player
    private void dealHands() {
        for (int i = 0; i < 7; i++) {
            playerA.getHand().addCard(deck.dealCard());
            playerB.getHand().addCard(deck.dealCard());
        }
    }

    //asks player to input a card rank
    public Rank promptForRank(Scanner scanner, String playerId) {
        System.out.print("Please ask Computer for a rank (e.g. ACE, TWO, THREE etc.): ");
        String input = scanner.nextLine().toUpperCase();
        return Rank.valueOf(input);
    }

    //checks if the players card has made a book (4 of a kind)
    public void checkBooks(Player player) {
        PlayersHand hand = player.getHand();
        for (Rank rank : Rank.values()) {
            if (hand.countRank(rank) == 4) { // If there are 4 cards of the same rank, remove them and add a book
                hand.removeRank(rank);
                hand.addBook(rank);
                System.out.println(player.getId() + " has a book of " + rank);
            }
        }
    }

    //method to have the computer choose a random card to ask player for
    public Rank getRandomRank(Player player) {
        List<Card> cards = player.getHand().getCards();
        Random random = new Random();
        return cards.get(random.nextInt(cards.size())).getRank();
    }

    //the main gameplay loop
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(playerA.getId() + "'s Hand: " + playerA.getHand().getCards());
            System.out.println(playerA.getId() + "'s Books: " + playerA.getHand().getBooks());
            Rank rank = promptForRank(scanner, playerA.getId());

            // Check if Player B has the requested rank
            boolean playerBHasRank = playerB.getHand().hasRank(rank);

            if (playerBHasRank) {
                System.out.println("Congratulations! " + playerB.getId() + " has " + rank);
                
                // Transfer all cards with the requested rank from Player B to Player A
                for (Iterator<Card> iterator = playerB.getHand().getCards().iterator(); iterator.hasNext(); ) {
                    Card card = iterator.next();
                    if (card.getRank() == rank) {
                        playerA.getHand().addCard(card);
                        iterator.remove();
                    }
                }
            } else {
                System.out.println("Sorry, " + playerB.getId() + " does not have that card, go fish");
                Card card = deck.dealCard();
                if (card != null) {
                    playerA.getHand().addCard(card);
                    System.out.println(playerA.getId() + " drew: " + card);
                }
            }
            checkBooks(playerA);
            if (playerA.getHand().getCards().isEmpty() || deck.size() == 0) {
                break;
            }

            rank = getRandomRank(playerB);
            System.out.println(playerB.getId() + " asks for: " + rank);

            // Check if Player A has the requested rank
            boolean playerAHasRank = playerA.getHand().hasRank(rank);

            if (playerAHasRank) {
                System.out.println(playerA.getId() + " has " + rank);
                
                // Transfer all cards with the requested rank from Player A to Player B
                for (Iterator<Card> iterator = playerA.getHand().getCards().iterator(); iterator.hasNext(); ) {
                    Card card = iterator.next();
                    if (card.getRank() == rank) {
                        playerB.getHand().addCard(card);
                        iterator.remove();
                    }
                }
            } else {
                System.out.println(playerA.getId() + " does not have that card, go fish");
                Card card = deck.dealCard();
                if (card != null) {
                    playerB.getHand().addCard(card);
                }
            }
            checkBooks(playerB);
            if (playerB.getHand().getCards().isEmpty() || deck.size() == 0) {
                break;
            }
        }
        
        //used to declare winner
        System.out.println("Game over");
        System.out.println(playerA.getId() + "'s Books: " + playerA.getHand().getBooks());
        System.out.println(playerB.getId() + "'s Books: " + playerB.getHand().getBooks());
        int playerABooks = playerA.getHand().getBooks().size();
        int playerBBooks = playerB.getHand().getBooks().size();
        if (playerABooks > playerBBooks) {
            System.out.println(playerA.getId() + " wins!");
        } else if (playerBBooks > playerABooks) {
            System.out.println(playerB.getId() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    
    //method to prompt PlayerA for ID
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player A's ID: ");
        String playerAId = scanner.nextLine();
        GoFishGame game = new GoFishGame(playerAId);
        game.play();
    }
}