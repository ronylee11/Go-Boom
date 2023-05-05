public class Card {
  private char rank;
  private char suite;

  Card() {
    rank = 'A';
    suite = 's';
  }

  Card(char rank, char suite) {
    this.rank = rank;
    this.suite = suite;
  }

  char getRank() { return this.rank; }

  char getSuite() { return this.suite; }

  void setRank(char rank) { this.rank = rank; }

  void setSuite(char suite) { this.suite = suite; }
}
