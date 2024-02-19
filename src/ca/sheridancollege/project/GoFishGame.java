/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author destinyodia
 */
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


// Class representing the Go Fish game
public class GoFishGame {
    private GoFishDeck deck;
    private PlayerHand playerHand;
    private PlayerHand computerHand;

    public GoFishGame() {
        deck = new GoFishDeck(52);
        playerHand = new PlayerHand();
        computerHand = new PlayerHand();
        dealHands();
        
    }
    

    private void dealHands() {
        for (int i = 0; i < 7; i++) {
            playerHand.addCard(deck.dealCard());
            computerHand.addCard(deck.dealCard());
        }
    }

    public Rank promptForRank(Scanner scanner) {
        
        System.out.print("\nYour Turn\nAsk opponent for a rank (e.g. ACE, TWO, THREE etc.): ");
        String input = scanner.nextLine().toUpperCase();
        String end="QUIT";
        if ( input.equals(end)){
            endGame();
            
        }
            return Rank.valueOf(input);
    }

    public void checkBooks(PlayerHand hand) {
        for (Rank rank : Rank.values()) {
        if (hand.countRank(rank) == 4) { // If there are 4 cards of the same rank, remove them and add a book
            hand.removeRank(rank);
            hand.addBook(rank);
            System.out.println("Player B has a book of " + rank);
        }
    }
}

    public Rank getRandomRank(PlayerHand hand) {
        List<GoFishCard> cards = hand.getCards();
        Random random = new Random();
            return cards.get(random.nextInt(cards.size())).getRank();
    }
    private void endGame() {
        System.out.println("Game over");
        if (playerHand.getBooks()==null){
            System.out.println("Your Books is empty");
        }
        else{
        System.out.println("Your Books: " + playerHand.getBooks());
        }
        if (computerHand.getBooks()==null){
            System.out.println("Player B's Books is empty");
        }
        else{
        System.out.println("Player B's Books: " + computerHand.getBooks());
        }
        int playerBooks = playerHand.getBooks().size();
        int computerBooks = computerHand.getBooks().size();
        if (playerBooks > computerBooks) {
            System.out.println("You wins!");
        } else if (computerBooks > playerBooks) {
            System.out.println("Player B wins!");
        } else {
            System.out.println("It's a tie!");
        }
        System.exit(0);
        
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Your Hand: " + playerHand.getCards());
            System.out.println("Your Books: " + playerHand.getBooks());
            Rank rank = promptForRank(scanner);
            
        
      
        // Check if Player B has the requested rank
        boolean playerBHasRank = false;
        for (GoFishCard card : computerHand.getCards()) {
            if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
            playerBHasRank = true;
            break;
            }
        }

        if (playerBHasRank) {
            System.out.println("Congratulations! Player B has " + rank);
            // Transfer all cards with the requested rank from Player B to Player A
            for (Iterator<GoFishCard> iterator = computerHand.getCards().iterator(); iterator.hasNext();) {
                GoFishCard card = iterator.next();
            if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
                playerHand.addCard(card);
                iterator.remove();
                }
            }
        }
        else {
                System.out.println("Sorry, Player B does not have that card\nGo fish!!!");
                GoFishCard card = deck.dealCard();
                if (card != null) {
                    playerHand.addCard(card);
                    System.out.println("Player B is drawing a card...\n\n");
                    System.out.println("Player B drew: " + card);
                }
            }
        checkBooks(playerHand);
        if (playerHand.getCards().isEmpty() || deck.size() == 0) {
            break;
        }

        rank = getRandomRank(computerHand);
        System.out.println("\nPlayer B's turn...");
        System.out.println("Player B asks for: " + rank);

            // Check if Player A has the requested rank
        boolean playerAHasRank = false;
        for (GoFishCard card : playerHand.getCards()) {
            if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
                playerAHasRank = true;
                break;
            }
        }

        if (playerAHasRank) {
            System.out.println("");
            System.out.println("You have " + rank);
        // Transfer all cards with the requested rank from Player A to Player B
            for (Iterator<GoFishCard> iterator = playerHand.getCards().iterator(); iterator.hasNext();) {
                GoFishCard card = iterator.next();
                if (card.getRank().toString().equalsIgnoreCase(rank.toString())) {
                    computerHand.addCard(card);
                    iterator.remove();
                }
            }
        }
        else {
            System.out.println("\nYou do not have that card\nGo fish!!!");
            GoFishCard card = deck.dealCard();
            if (card != null) {
                computerHand.addCard(card);
            }
        }
            checkBooks(computerHand);
            if (computerHand.getCards().isEmpty() || deck.size() == 0) {
                break;
            }
        }
        endGame();
}

    public static void main(String[] args) {
        System.out.println("GO FISH GAME!!!\n\nRules Of The Game\nPlayer A asks Player B for a specific card,"
                + " for example, \"Do you have any Fours?\"\n" +
"Player A, who asked for the card is considered the player who is \"fishing”. The player “fishing” must have at\n" +
"least one card of the number that was asked in their hand.\n" +
"If Player B has Fours, they must hand over all the Fours in their hand.\n" +
"If Player B has none, they \"Go fish!\" \n" +
"If Player A gets one or more Fours, they are entitled to ask Player B or another player for another card.\n" +
"Player A can ask for the same card or a different one.\n\n" +
"When a player makes a catch, they must reveal the card so that the catch is verified. If a player gets the\n" +
"fourth card of a book, the player shows all four cards, places them on the table face up in front of everyone\n" +
"and plays again.\n" +
"If the player goes fishing without \"making a catch\" (does not receive a card they asked for), the turn passes\n" +
"to the next player.\n" +
"The game ends when all thirteen books have been won. The winner is the player with the most books.\n" +
"\nYou can decide to QUIT anytime by Typing 'QUIT' in the response box and see your SCORE.");
        
        System.out.println("\n\n\n");
        GoFishGame game = new GoFishGame();
        game.play();
    }

    
}
