import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    ArrayList<String> shuffledDeck = deck.shuffleCards();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
    Scanner input = new Scanner(System.in);
    static boolean gameStarted = false;
    static int trickNumber = 1;

    public void start() {
        // Start game
        gameStarted = true;

        // Create 4 players
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }

        // Deal 1 card to each player at a time
        for (int i = 0; i < 7; i++) {
            // System.out.println(players[0].getHand());
            for (int j = 0; j < 4; j++) {
                players[j].addCard();
            }
        }

        String leadCard = shuffledDeck.remove(
                0); // Remove the first card from the shuffled deck as the lead card
        center.add(leadCard); // Add the lead card to the center

        while (gameStarted) { // Game loop
            // Print the trick number
            System.out.println("Trick #" + trickNumber);
            // Print each player's hand
            for (int i = 0; i < 4; i++) {
                System.out.println("Player" + (i + 1) + ": " + players[i].getCards());
            }

            // Print the center ArrayList
            System.out.println("Center : " + center);
            deck.Largest_Card(center); // get the largest card

            // Print the remaining deck
            System.out.println(deck.toString());

            // Print the current scores for all players
            System.out.print("Score  : ");
            for (int i = 0; i < 4; i++) {
                System.out.print(
                        String.format("Player%d = %d | ", i + 1, players[i].getScore()));
            }
            System.out.println();

            // Determine the first player based on the lead card
            int currentPlayer = determineFirstPlayer(leadCard);

            // Print the current player
            System.out.println("Turn: Player" + currentPlayer);

            System.out.print("> ");
            String command = input.nextLine();

            switch (command.toLowerCase()) {
                case "s": // restart game
                    start(); // BROKEN!!!
                    break;
                case "d": // draw a card
                    players[currentPlayer].addCard();
                    break;
                case "x": // quit game
                    gameStarted = false;
                    break;
            }

            for (String cardsInHand : players[currentPlayer]
                    .getCards()) { // play card from hand // BROKEN!!!
                if (command.toLowerCase().equals(cardsInHand)) {
                    players[currentPlayer].removeCard(cardsInHand);
                    center.add(cardsInHand);
                    break;
                }
            }
        }
    }

    // Determine the first player based on the lead card
    public int determineFirstPlayer(String leadCard) {
        char rank = leadCard.charAt(1);

        if (rank == 'A' || rank == '5' || rank == '9' || rank == 'K') {
            return 1; // Player1
        } else if (rank == '2' || rank == '6' || rank == 'X') {
            return 2; // Player2
        } else if (rank == '3' || rank == '7' || rank == 'J') {
            return 3; // Player3
        } else if (rank == '4' || rank == '8' || rank == 'Q') {
            return 4; // Player4
        } else {
            return -1; // Invalid lead card
        }
    }
}
