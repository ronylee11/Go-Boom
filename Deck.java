import java.util.*;

public class Deck {
    // Combine Suite and Character
    private ArrayList<String> deck = new ArrayList<>();
    private String[] D_Suit = { "c", "h", "d", "s" };       //Change the char to String
    private String[] D_Rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    Deck() {

        for (int i = 0; i < D_Suit.length; i++) {
            for (int j = 0; j < D_Rank.length; j++) {
                deck.add((D_Suit[i]) + (D_Rank[j]));
            }
        }
        System.out.println(deck);
    }
    
    public ArrayList<String> shuffleCards() {
        Collections.shuffle(deck);
        return deck;
    }

    public String toString() {
        return "Deck : " + deck;
    }
}
