import java.util.ArrayList;
import java.lang.Math;

public class Player extends Card {
    private ArrayList<String> hand = new ArrayList<>();
    private static char[] possibleSuite = { 'c', 'h', 'd', 's' };
    private static char[] possibleRank = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };

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

    /* Player() {
        // repeat 7 times
        // generate a card that is not in hand // hA
        // check if card exists in hand, if not, create card
        for (int i = 0; i < 7; i++) {
            hand.add(checkCard(generateCard()));
        }
        System.out.println(hand);
        // super();

        // add all 7 cards to hand
    }*/
    Player(){}
    
    //Add card to one player at a time
    public void addCard(){
        hand.add(checkCard(generateCard()));
    }
    
    //get cards on hand for each player
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
