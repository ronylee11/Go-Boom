import java.util.*;
public class Main {
    public static void main(String[] args) {
        // Create and shuffle the deck
        Deck deck = new Deck();
        ArrayList<String> shuffledDeck = deck.shuffleCards();
        System.out.println(deck.toString());
    
        // Create players
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
    
        // Deal 7 cards to each player
        for (int i = 0; i < 7; i++) {
            player1.addCard(shuffledDeck.remove(0));
            player2.addCard(shuffledDeck.remove(0));
            player3.addCard(shuffledDeck.remove(0));
            player4.addCard(shuffledDeck.remove(0));
        }
    
        // Print the cards of each player
        System.out.println("Player1: " + player1.getCards());
        System.out.println("Player2: " + player2.getCards());
        System.out.println("Player3: " + player3.getCards());
        System.out.println("Player4: " + player4.getCards());
    
        // Print the remaining deck and score (initially 0 for all players)
        System.out.println(deck.toString());
        System.out.println("Score: Player1 = 0 | Player2 = 0 | Player3 = 0 | Player4 = 0");
    
        // Determine the first player based on the lead card
        Card leadCard = new Card(shuffledDeck.get(0).charAt(1), shuffledDeck.get(0).charAt(0));
        int firstPlayer = determineFirstPlayer(leadCard);
    
        // Print the trick number and the first player
        System.out.println("Trick #1");
        System.out.println("Turn: Player" + (firstPlayer + 1));
    }
    
    // Determine the first player based on the lead card
    private static int determineFirstPlayer(Card leadCard) {
        char rank = leadCard.getRank();
    
        if (rank == 'A' || rank == '5' || rank == '9' || rank == 'K') {
            return 0; // Player1
        } else if (rank == '2' || rank == '6' || rank == '10') {
            return 1; // Player2
        } else if (rank == '3' || rank == '7' || rank == 'J') {
            return 2; // Player3
        } else if (rank == '4' || rank == '8' || rank == 'Q') {
            return 3; // Player4
        } else {
            return -1; // Invalid lead card
        }
    }
}