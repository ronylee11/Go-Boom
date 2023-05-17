import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    Player player = new Player();
    ArrayList<String> shuffledDeck = deck.shuffleCards();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
    Scanner input = new Scanner(System.in);

    public void start() {
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

        while (true) { // Game loop
            // Print the trick number
            System.out.println("Trick #1");
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
            int firstPlayer = player.determineFirstPlayer(leadCard);

            // Print the current player
            System.out.println("Turn: Player" + firstPlayer);

            System.out.print("> ");
            String text = input.nextLine();
        }
    }
}
