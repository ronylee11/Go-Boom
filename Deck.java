import java.util.*;

public class Deck {
    // Combine Suite and Character
    private static ArrayList<String> deck = new ArrayList<>();
    private static String[] D_Suit = { "c", "h", "d", "s" };       //Change the char to String
    private static String[] D_Rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K" };
    private static ArrayList<Integer> listy = new ArrayList<>(); // this two arraylist is to store the randomize number and the card from the deck
    private static ArrayList<String> cunny = new ArrayList<>();     //which is mainly for testing only
    static Random rand = new Random();
    //Create A deck of card with no duplicates
    public static void Create_deck(){
        for (int i = 0; i < D_Suit.length; i++) {
            for (int j = 0; j < D_Rank.length; j++) {
                deck.add((D_Suit[i]) + (D_Rank[j]));
            }
        }
        System.out.println(deck);
    }
    /**
     * Creates and returns a HashMap with card values mapped to their corresponding integer values.
     * The keys are card ranks (2-10, J, Q, K, A) and the values are integers from 1 to 13.
     * @return A HashMap<String, Integer> representing the mapping of card ranks to their values.
     */
    HashMap<String , Integer> Hash_value = new HashMap<>(); 
    public HashMap<String , Integer> create_card_Values_HashMap(){ 
        Hash_value.put("2", 1);
        Hash_value.put("3", 2);
        Hash_value.put("4", 3);
        Hash_value.put("5", 4);
        Hash_value.put("6", 5);
        Hash_value.put("7", 6);
        Hash_value.put("8", 7);
        Hash_value.put("9", 8);
        Hash_value.put("T", 9);
        Hash_value.put("J", 10);
        Hash_value.put("Q", 11);
        Hash_value.put("K", 12);
        Hash_value.put("A", 13);
        System.out.println(Hash_value);
        return Hash_value;
    }    

    public void test1(){ //to test if the hashmap is working and to get the largest card
        for (int i = 0; i <= 5; i++) { // to randomize the number
            listy.add(rand.nextInt(51));
        }
        for (int j = 0; j < listy.size(); j++) { // to use the randomize number and get the card from the deck 
            cunny.add(getdeck(listy.get(j)));
        }
        System.out.println(cunny);
        //---------------------The compare part-----------------//
        String largest_card = cunny.get(0); //to the get the first card from the randomize list
        int largest_value = Hash_value.get(largest_card.substring(1)); // get the first card's rank in the arraylist
    
        for (String cunnies : cunny) {
            int cardValue = Hash_value.get(cunnies.substring(1)); // this get each each card's rank in the arraylist
    
            if (cardValue > largest_value) { // this check which card ranking is higher -- if there only cards with rank of same value the first card placed is get
                largest_card = cunnies;     
                largest_value = cardValue;
            }
        }
        System.out.println("Largest Card : " + largest_card);
    }
    

    //Shuffles the deck
    public ArrayList<String> shuffleCards() {
        Collections.shuffle(deck);
        return deck;
    }
    
    public String toString() {
        return "Deck   : " + deck;
    }

    public static String getdeck(int a){
        return deck.get(a);
    }

    public Deck() {
        Create_deck();
        create_card_Values_HashMap();
        test1();
    }
    
    public static void main(String[] args) {
        Deck deck = new Deck();
    }
    
}
