import java.util.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<String> shuffledDeck = deck.shuffleCards();
        

        Player[] players = new Player[4];
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }

        ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
        String leadCard = shuffledDeck.remove(0); // Remove the first card from the shuffled deck as the lead card
        center.add(leadCard); // Add the lead card to the center

        // Deal 1 card to each player at a time
        for (int i = 0; i < 7; i++) {
            // System.out.println(players[0].getHand());
            for (int j = 0; j < 4; j++) {
                players[j].addCard();

            }
        }
        // Print each player's hand
        for (int i = 0; i < 4; i++) {
            System.out.println("Player" + (i + 1) + ": " + players[i].getCards());
        }

        // Print the center ArrayList
        System.out.println("Center : " + center);

        // Print the remaining deck and score (initially 0 for all players)
        System.out.println(deck.toString());
        System.out.println("Score: Player1 = 0 | Player2 = 0 | Player3 = 0 | Player4 = 0");

        // Determine the first player based on the lead card
        int firstPlayer = determineFirstPlayer(leadCard);

        // Print the trick number and the first player
        System.out.println("Trick #1");
        System.out.println("Turn: Player" + (firstPlayer + 1));
    }

    // Determine the first player based on the lead card
    private static int determineFirstPlayer(String leadCard) {
        char rank = leadCard.charAt(1);

        if (rank == 'A' || rank == '5' || rank == '9' || rank == 'K') {
            return 0; // Player1
        } else if (rank == '2' || rank == '6' || rank == 'X') {
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