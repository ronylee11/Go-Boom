public class Card {
    private int rank;
    private char suite;

    Card() {
        rank = 1;
        suite = 's';
    }

    Card(int rank, char suite) {
        this.rank = rank;
        this.suite = suite;
    }

    int getRank() {
        return this.rank;
    }

    char getSuite() {
        return this.suite;
    }

    void setRank(int rank) {
        this.rank = rank;
    }

    void setSuite(char suite) {
        this.suite = suite;
    }

}