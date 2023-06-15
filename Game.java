import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
    Scanner input = new Scanner(System.in);
    static boolean gameStarted = false;
    static boolean gameEnded = false;
    static int trickNumber = 1;
    private static int trickCounter = 1;
    private static int player_num = 0;
    private static int currentPlayer = 0;

    public void initializeGame() {
        for (int i = 0; i < 4; i++) {
            players[i] = new Player();
        }

        // Deal 1 card to each player at a time
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                players[j].addCard();
            }
        }
    }

    public ArrayList<ArrayList<String>> getPlayerCards() {
        ArrayList<ArrayList<String>> playerCards = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerCards.add(players[i].getCards());
        }
        return playerCards;
    }

    public void restart() {
        for (int j = 0; j < 4; j++) {
            players[j].resetHand();
        }
        trickNumber = 1;
        deck.resetDeck();
        Deck.Create_deck();
        resetCenter();
        start();
    }

    public void resetCenter() {
        center.clear();
    }

    public void generateCenter() {
        ArrayList<String> shuffledDeck = deck.shuffleCards();
        String leadCard = shuffledDeck.remove(
                0); // Remove the first card from the shuffled deck as the lead card
        center.add(leadCard); // Add the lead card to the center
    }

    public void start() {
        gameStarted = true;
        initializeGame();
        generateCenter();

        while (gameStarted) { // Game loop
            printGameState();
            handlePlayerTurn();

            System.out.println();
        }
    }

    public void printGameState() { // Print the game interface
        System.out.println("Trick #" + trickNumber);
        for (int i = 0; i < 4; i++) {
            System.out.println("Player" + (i + 1) + ": " + players[i].getCards());
        }
        if (trickNumber == 1) {
            System.out.println("Center : " + center);
            deck.Largest_Card(center); // get the largest card
        } else {
            System.out.println("Center : " + center);
        }

        // Print remaining deck
        System.out.println(deck.toString());

        System.out.print("Score  : ");
        for (int i = 0; i < 4; i++) {
            System.out.print(
                    String.format("Player%d = %d | ", i + 1, players[i].getScore()));
        }
        System.out.println();
    }

    public boolean playCard(int currentPlayer, String command) {
        String leadCard = "";
        if (center.isEmpty()) {
            leadCard = command;
        } else {
            leadCard = center.get(0);
        }
        char leadSuit = leadCard.toLowerCase().charAt(0);
        char leadRank = leadCard.toLowerCase().charAt(1);

        String suit = command.toLowerCase().charAt(0) + "";
        char rank = command.toLowerCase().charAt(1);

        boolean isSameSuit = suit.equalsIgnoreCase(Character.toString(leadSuit));
        boolean isSameRank = rank == leadRank;

        if (isSameSuit || isSameRank) {
            for (String card : players[currentPlayer].getCards()) {
                if (command.toLowerCase().equals(card.toLowerCase())) {
                    players[currentPlayer].removeCard(card);
                    center.add(card);
                    trickCounter++;
                    if (trickCounter != 5) {
                        System.out.println();
                    }
                    Player_played.put(card.toLowerCase(), currentPlayer); // Update the Player_played hashmap
                    return true; // Card played successfully
                }
            }

            System.out.println("Invalid card! Card not found in your hand.\n");
            return false; // Card is invalid
        } else {
            System.out.println(
                    "Invalid card! You must change either the suit or rank.\n");
            return false;
        }
    }

    public void winner_of_trick() {
        if (trickNumber == 2) {
            center.remove(0);
        }

        String largestCard = deck.Largest_Card(center);
        int winnerIndex = Player_played.get(largestCard.toLowerCase());

        if (winnerIndex == -1) {
            winnerIndex = currentPlayer;
        }

        System.out.println("Player" + (winnerIndex + 1) + " wins the trick!\n");
        currentPlayer = winnerIndex;
        updateScore();
    }

    public void updateScore() {
        int winnerOfTrick = player_num;
        // update score for losers of the trick
        for (int i = 0; i < 4; i++) {
            if (i != winnerOfTrick) {
                players[i].addScore();
            }
        }
    }

    HashMap<String, Integer> Player_played = new HashMap<>();

    public boolean canPlayOnCenter(String card) {
        if (center.isEmpty()) {
            return true; // Any card can be played on an empty center
        }

        String leadCard = center.get(0);
        char leadSuit = leadCard.toLowerCase().charAt(0);
        char leadRank = leadCard.toLowerCase().charAt(1);

        char suit = card.toLowerCase().charAt(0);
        char rank = card.toLowerCase().charAt(1);

        boolean isSameSuit = suit == leadSuit;
        boolean isSameRank = rank == leadRank;

        return isSameSuit || isSameRank;
    }

    public void handlePlayerTurn() {
        currentPlayer = determineFirstPlayer(center.get(0));
        System.out.println("Turn: Player" + (currentPlayer + 1));

        int skippedCount = 0; // Count the number of skipped turns

        while (gameStarted) {
            System.out.print("> ");
            String command = input.nextLine();

            boolean isValidCard = false;

            switch (command.toLowerCase()) {
                case "s": // restart game
                    restart();
                    return; // Exit the method to avoid moving to the next player
                case "d": // draw a card
                    drawCards();
                    break;
                case "x": // quit game
                    gameStarted = false;
                    return; // Exit the method to avoid moving to the next player
                default: // play card from hand
                    isValidCard = playCard(currentPlayer, command);
                    break;
            }

            if (isValidCard) {
                currentPlayer = (currentPlayer + 1) % 4; // Move to the next player
            }

            if (deck.isEmpty()) {
                boolean canPlay = false;
                for (String card : players[currentPlayer].getCards()) {
                    if (canPlayOnCenter(card)) {
                        canPlay = true;
                        break;
                    }
                }

                if (!canPlay) {
                    System.out.println("Player" + (currentPlayer + 1) + " cannot play. Skipping turn.\n");
                    currentPlayer = (currentPlayer + 1) % 4;
                    skippedCount++;
                    if (skippedCount >= 2) {
                        trickNumber++;
                        trickCounter = 1;
                        winner_of_trick();
                        resetCenter();
                        if (gameEnded) {
                            System.out.println("Game has ended!");
                            return; // End the program
                        }
                        currentPlayer = player_num;
                        skippedCount = 0; // Reset the skipped count
                    }
                }
            }

            if (trickCounter == 5) {
                trickNumber++;
                trickCounter = 1;
                winner_of_trick();
                resetCenter();
                if (gameEnded) {
                    System.out.println("Game has ended!");
                    return; // End the program
                }
            }

            if (gameStarted) {
                printGameState();
                System.out.println("Turn: Player" + (currentPlayer + 1));
            }
        }
    }

    public void drawCards() {
        boolean foundValidCard = false;
        boolean validCardDrawn = false; // Flag to track if a valid card was drawn
        while (!foundValidCard) {
            if (deck.isEmpty()) {
                System.out.println("Deck is empty! And Player" + (currentPlayer + 1) + " cannot play. Skipping turn.");
                break; // Exit the loop and move to the next player
            }

            players[currentPlayer].addCard();
            String lastCard = players[currentPlayer].getLastCard();
            if (canPlayOnCenter(lastCard)) {
                validCardDrawn = true;
                break; // Exit the loop and move to the next player
            }
        }
        if (!validCardDrawn) {
            currentPlayer = (currentPlayer + 1) % 4; // Move to the next player
        }
    }

    // Determine the first player based on the lead card
    public int determineFirstPlayer(String leadCard) {
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
