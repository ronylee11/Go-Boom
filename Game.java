import java.util.ArrayList;

public class Game {
    Deck deck = new Deck();
    Player player = new Player();
    ArrayList<String> shuffledDeck = deck.shuffleCards();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card

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

        // Print each player's hand
        for (int i = 0; i < 4; i++) {
            System.out.println("Player" + (i + 1) + ": " + players[i].getCards());
        }

        // Print the center ArrayList
        System.out.println("Center : " + center);
        deck.Largest_Card(center);
        // Print the remaining deck and score (initially 0 for all players)
        // System.out.println(deck.toString());
        System.out.println(
                "Score: Player1 = 0 | Player2 = 0 | Player3 = 0 | Player4 = 0");

        // Determine the first player based on the lead card
        int firstPlayer = player.determineFirstPlayer(leadCard);

        // Print the trick number and the first player
        System.out.println("Trick #1");
        System.out.println("Turn: Player" + (firstPlayer + 1));
    }
}
