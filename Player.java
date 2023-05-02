import java.util.ArrayList;
import java.lang.Math;

public class Player extends Card {
    private static ArrayList<String> hand = new ArrayList<>();
    private static char[] possibleSuite = { 'c', 'h', 'd', 's' };
    private static char[] possibleRank = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };

    // Player1: [c9, h5, dQ, d6, c7, s8, hQ]
    // Player2: [h6, sJ, d2, hX, d5, cA, hA]
    // Player3: [d9, dA, h7, d8, s3, sX, h2]
    // Player4: [h8, sA, sK, hK, c6, s4, h9]

    static String generateCard() {
        // generate a card
        int randomSuiteNum = (int) (Math.random() * possibleSuite.length) + 0;
        char randomSuite = possibleSuite[randomSuiteNum];
        int randomRankNum = (int) (Math.random() * possibleRank.length) + 0;
        char randomRank = possibleRank[randomRankNum];
        String tempCard = String.valueOf(randomSuite) + String.valueOf(randomRank);
        return tempCard;
    }

    static String checkCard(String cardToCheck) {
        boolean cardIsValid = false;
        for (String card : hand) {
            while (!cardIsValid) {
                cardToCheck = generateCard();
                if (cardToCheck != card) {
                    cardIsValid = true;
                }
            }
        }
        return cardToCheck;
    }

    Player() {
        // repeat 7 times
        // generate a card that is not in hand // hA
        // check if card exists in hand, if not, create card
        for (int i = 0; i < 7; i++) {
            hand.add(checkCard(generateCard()));
        }
        System.out.println(hand);
        // super();

        // add all 7 cards to hand
    }

}