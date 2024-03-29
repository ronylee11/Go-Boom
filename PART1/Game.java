import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
    Scanner input = new Scanner(System.in);
    static boolean gameStarted = false;
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

    public void restart() {
        for (int j = 0; j < 4; j++) {
            players[j].resetHand();
            ;
        }
        deck.resetDeck();
        Deck.Create_deck();
        resetCenter();
        start();
    }

    public void resetCenter() {
        center.clear();
    }

    public void start() {
        gameStarted = true;
        initializeGame();
        ArrayList<String> shuffledDeck = deck.shuffleCards();
        String leadCard = shuffledDeck.remove(
                0); // Remove the first card from the shuffled deck as the lead card
        center.add(leadCard); // Add the lead card to the center

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
        if(trickNumber == 2){
            center.remove(0);
        }
        String large = deck.Largest_Card(center);
        player_num = Player_played.get(large.toLowerCase());
        if (player_num == 0) {
            player_num = currentPlayer;
        }
        System.out.println("Player" + (player_num + 1) + " wins the trick!\n");
        currentPlayer = player_num;
        updateScore();
    }

    public void updateScore() {
        int winnerOfTrick = player_num;
        players[winnerOfTrick].addScore();
    }

    HashMap<String, Integer> Player_played = new HashMap<>();

    public void handlePlayerTurn() {
        currentPlayer = determineFirstPlayer(center.get(0));
        System.out.println("Turn: Player" + (currentPlayer + 1));

        while (gameStarted) {
            System.out.print("> ");
            String command = input.nextLine();

            boolean isValidCard = false;

            switch (command.toLowerCase()) {
                case "s": // restart game
                    restart();
                    return; // Exit the method to avoid moving to the next player
                case "d": // draw a card
                    players[currentPlayer].addCard();
                    break;
                case "x": // quit game
                    gameStarted = false;
                    return; // Exit the method to avoid moving to the next player
                default: // play card from hand
                    isValidCard = playCard(currentPlayer, command);
                    Player_played.put(command.toLowerCase(), currentPlayer);
                    // System.out.println(Player_played);
                    break;
            }

            if (isValidCard) {
                currentPlayer = (currentPlayer + 1) % 4; // Move to the next player
            }

            if (trickCounter == 5) {
                trickNumber++;
                trickCounter = 1;
                winner_of_trick();
                resetCenter();
            }
            if (gameStarted) {
                printGameState();
                System.out.println("Turn: Player" + (currentPlayer + 1));
            }
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
