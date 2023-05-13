public class Main {
    public static void main(String[] args) {
        // System.out.println("Hello World");
        // System.out.println("Hello");

        Card card1 = new Card('A', 's');
        // System.out.println(card1.getSuit());
        
        Deck deck1 = new Deck();
        deck1.shuffleCards();
        System.out.println(deck1.toString());
        
        Player[] player = new Player[4];
        for (int i = 0; i < 4; i++){
            player[i]= new Player();
        }
        
        //Deal 1 card to each player at a time
        for (int i = 0; i < 7; i++) {
            //System.out.println(players[0].getHand());
            for (int j = 0; j < 4; j++) {
                player[j].addCard();
                
            }
        }
        for (int i = 0; i < 4; i++) {
            System.out.println("Player" + (i+1) + ": " + player[i].getCards());
        }

        Deck deck1 = new Deck();
    }
}
