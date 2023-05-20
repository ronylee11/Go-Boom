import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    Deck deck = new Deck();
    Player[] players = new Player[4];
    ArrayList<String> center = new ArrayList<>(); // Center ArrayList to store the lead card
    Scanner input = new Scanner(System.in);
    static boolean gameStarted = false;
    static int trickNumber = 1;
    private static int trickCounter = 1;

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
    
    public void restart(){
            for (int j = 0; j < 4; j++) {
                players[j].resetHand();;
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
        String leadCard = shuffledDeck.remove(0); // Remove the first card from the shuffled deck as the lead card
        center.add(leadCard); // Add the lead card to the center

        while (gameStarted) { // Game loop
            printGameState();
            handlePlayerTurn();
          
            System.out.println();
        }
    }

    public void printGameState() {   // Print the game interface
        System.out.println("Trick #" + trickNumber);
        for (int i = 0; i < 4; i++) {
            System.out.println("Player" + (i + 1) + ": " + players[i].getCards());
        }

        System.out.println("Center : " + center);
        deck.Largest_Card(center); // get the largest card

        // Print remaining deck
        System.out.println(deck.toString());

        System.out.print("Score  : ");
        for (int i = 0; i < 4; i++) {
            System.out.print(String.format("Player%d = %d | ", i + 1, players[i].getScore()));
        }
        System.out.println();
    }

    public boolean playCard(int currentPlayer, String command) {
        String leadCard = center.get(0);
        char leadSuit = leadCard.charAt(0);
        char leadRank = leadCard.charAt(1);
    
        String suit = command.charAt(0) + "";
        char rank = command.charAt(1);
    
        boolean isSameSuit = suit.equalsIgnoreCase(Character.toString(leadSuit));
        boolean isSameRank = rank == leadRank;
    
        if (!isSameSuit || !isSameRank) {
            for (String card : players[currentPlayer].getCards()) {
                if (command.equals(card)) {
                    players[currentPlayer].removeCard(card);
                    center.add(card);
                    return true; // Card played successfully
                }
            }
            System.out.println("Invalid card! Card not found in your hand.");
            return false; // Card is invalid
        }
    
        System.out.println("Invalid card! You must change either the suit or rank.");
        return false;
    }
    

    
    
    
    
    public void handlePlayerTurn() {
        int currentPlayer = determineFirstPlayer(center.get(0));
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
                    break;
            }
    
            if (isValidCard) {
                currentPlayer = (currentPlayer + 1) % 4; // Move to the next player
            }
    
            if (gameStarted) {
                printGameState();
                System.out.println();
                System.out.println("Turn: Player" + (currentPlayer + 1));
            }

            if (trickCounter == 4) {
                trickNumber++;
                trickCounter = 1;
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
