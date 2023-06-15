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
    void addScore() {
        // for each card in hand, add respective score
        for (String card : hand) {
            char rank = card.charAt(1);
            switch (rank) {
                case 'A':
                    score += 1;
                    break;
                case '2':
                    score += 2;
                    break;
                case '3':
                    score += 3;
                    break;
                case '4':
                    score += 4;
                    break;
                case '5':
                    score += 5;
                    break;
                case '6':
                    score += 6;
                    break;
                case '7':
                    score += 7;
                    break;
                case '8':
                    score += 8;
                    break;
                case '9':
                    score += 9;
                    break;
                case 'X':
                    score += 10;
                    break;
                case 'J':
                    score += 10;
                    break;
                case 'Q':
                    score += 10;
                    break;
                case 'K':
                    score += 10;
                    break;
            }
        }
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

    void removeCard(String card) {
        hand.remove(card);
    }

    public void resetHand() {
        hand.clear();
    }

    public String getLastCard() {
        int lastIndex = hand.size() - 1;
        return hand.get(lastIndex);
    }
}
