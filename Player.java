import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Card {
    private ArrayList<String> hand = new ArrayList<>();
    private static char[] possibleSuite = { 'c', 'h', 'd', 's' };
    private static char[] possibleRank = { 'A', '2', '3', '4', '5', '6', '7',
            '8', '9', 'X', 'J', 'Q', 'K' };
    private int score = 0; // cannot be static as each player has different scores
    private static Deck deck = new Deck();
    Scanner input = new Scanner(System.in);

    Player() {
    }

    static String generateCard() {
        // generate a card
        int randomSuiteNum = (int) (Math.random() * possibleSuite.length) + 0;
        char randomSuite = possibleSuite[randomSuiteNum];
        int randomRankNum = (int) (Math.random() * possibleRank.length) + 0;
        char randomRank = possibleRank[randomRankNum];
        String tempCard = String.valueOf(randomSuite) + String.valueOf(randomRank);
        return tempCard;
    }

    String checkCard(String cardToCheck) {
        // check if card is in deck
        boolean cardIsValid = false;
        // if card is not in deck, that means a player took it
        while (!cardIsValid) {
            for (String cardInDeck : deck.getDeck()) {
                if (cardInDeck.equals(cardToCheck)) {
                    cardIsValid = true;
                    break;
                }
            }
            if (!cardIsValid) {
                cardToCheck = generateCard();
            }
        }
        return cardToCheck;
    }

    // view player current score
    int getScore() {
        return score;
    }

    // update player score
    void addScore(int score) {
        this.score += score;
    }

    // Add card to one player at a time
    public void addCard() {
        String cardToAdd = checkCard(generateCard());
        deck.addCardToHand(cardToAdd);
        hand.add(cardToAdd);
    }

    // get cards on hand for each player
    public ArrayList<String> getCards() {
        return hand;
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
