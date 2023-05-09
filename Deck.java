import java.util.ArrayList;

public class Deck {
    // Combine Suite and Character
    private ArrayList<String> deck = new ArrayList<>();
    private char[] D_Suit = { 'c', 'h', 'd', 's' };
    private char[] D_Rank = { 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };

    Deck() {

        for (int i = 0; i < D_Suit.length; i++) {
            for (int j = 0; j < D_Rank.length; j++) {
                deck.add(Character.toString(D_Suit[i]) + Character.toString(D_Rank[j]));
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
