public class Card {
    private char rank;
    private char suit;

    Card() {
        rank = 'A';
        suit = 's';
    }

    Card(char rank, char suit) {
        this.rank = rank;
        this.suit = suit;
    }

    char getRank() {
        return this.rank;
    }

    char getSuit() {
        return this.suit;
    }

    void setRank(char rank) {
        this.rank = rank;
    }

    void setSuit(char suit) {
        this.suit = suit;
    }
}
