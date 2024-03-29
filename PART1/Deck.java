import java.util.*;

public class Deck {
    // Combine Suite and Character
    private static ArrayList<String> deck = new ArrayList<>();
    private static String[] D_Suit = { "c", "h", "d",
            "s" }; // Change the char to String
    private static String[] D_Rank = { "A", "2", "3", "4", "5", "6", "7",
            "8", "9", "X", "J", "Q", "K" };
    static Random rand = new Random();
    private static boolean deckCreated = false;

    // Create A deck of card with no duplicates
    public static void Create_deck() {
        for (int i = 0; i < D_Suit.length; i++) {
            for (int j = 0; j < D_Rank.length; j++) {
                deck.add((D_Suit[i]) + (D_Rank[j]));
            }
        }
        // System.out.println(deck);
    }

    /**
     * Creates and returns a HashMap with card values mapped to their
     * corresponding integer values. The keys are card ranks (2-10, J, Q, K, A)
     * and the values are integers from 1 to 13.
     *
     * @return A HashMap<String, Integer> representing the mapping of card ranks
     *         to their values.
     */
    HashMap<String, Integer> Hash_value = new HashMap<>();
    HashMap<String, Integer> Score_value = new HashMap<>();

    public HashMap<String, Integer> create_card_Values_HashMap() {
        Hash_value.put("2", 1);
        Hash_value.put("3", 2);
        Hash_value.put("4", 3);
        Hash_value.put("5", 4);
        Hash_value.put("6", 5);
        Hash_value.put("7", 6);
        Hash_value.put("8", 7);
        Hash_value.put("9", 8);
        Hash_value.put("X", 9);
        Hash_value.put("J", 10);
        Hash_value.put("Q", 11);
        Hash_value.put("K", 12);
        Hash_value.put("A", 13);
        // System.out.println(Hash_value);
        return Hash_value;
    }
    public HashMap<String, Integer> create_score_Values_HashMap() {
        Score_value.put("A", 1);
        Score_value.put("2", 2);
        Score_value.put("3", 3);
        Score_value.put("4", 4);
        Score_value.put("5", 5);
        Score_value.put("6", 6);
        Score_value.put("7", 7);
        Score_value.put("8", 8);
        Score_value.put("9", 9);
        Score_value.put("X", 10);
        Score_value.put("J", 10);
        Score_value.put("Q", 10);
        Score_value.put("K", 10);
        // System.out.println(Score_value);
        return Score_value;
    }

    public String Largest_Card(
            ArrayList<String> holding) { // to get the largest card from the arraylist
        String largest_card = holding.get(0); // to the get the first card from the randomize list
        int largest_value = Hash_value.get(largest_card.substring(
                1)); // get the first card's rank in the arraylist

        for (String cunnies : holding) {
            int cardValue = Hash_value.get(cunnies.substring(
                    1)); // this get each each card's rank in the arraylist

            if (cardValue > largest_value) { // this check which card ranking is higher -- if
                                             // there only cards with rank of same value the first
                                             // card placed is get
                largest_card = cunnies;
                largest_value = cardValue;
            }
        }
        return largest_card;
        // System.out.println("Largest Card : " + largest_card);
    }

    //Spliting Card's rank and suite
    public ArrayList<String> Spliting(ArrayList<String>cunnysplit){
        for (String C_card : cunnysplit){
            String [] ArraySplit = C_card.split("");
            cunnysplit.addAll(Arrays.asList(ArraySplit));
        }
        return cunnysplit;
    }

    // Shuffles the deck
    public ArrayList<String> shuffleCards() {
        Collections.shuffle(deck);
        return deck;
    }

    public void addCardToHand(String card) {
        deck.remove(card);
    }

    public String toString() {
        return "Deck   : " + deck;
    }

    public ArrayList<String> getDeck() {
        return deck;
    }

    public Deck() {
        if (!deckCreated) { // make sure there is only one deck
            Create_deck();
            create_card_Values_HashMap();
            deckCreated = true;
        }
    }
    public void resetDeck() {
        deck.clear();
    }
}
