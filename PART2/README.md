# Part 2

## A. Member Contributions

| No  | ID         | Name                  | Task descriptions | Contribution % |
| --- | ---------- | --------------------- | ----------------- | -------------- |
| 1   | 1221302735 | Ho Jing Lu            | Done No 5         | 100%           |
| 2   | 1211311027 | Lee Rong Yi           | Done No 1, 8      | 100%           |
| 3   | 1211102270 | Yap Choo Kath Moon    | Done No 7, 8      | 100%           |
| 4   | 1211201553 | Nicholas Chik Zi Jian | Done No 3,4,6     | 100%           |

## B. Part 1 Feature Completion

Mark Y for Complete, P for Partial done, N for Not implemented.

| No  | Feature                                                                        | Completed (Y/P/N) |
| --- | ------------------------------------------------------------------------------ | ----------------- |
| 1   | All cards should be faced up to facilitate checking.                           | Y                 |
| 2   | Start a new game with randomized 52 cards.                                     | Y                 |
| 3   | The first card in the deck is the first lead card and is placed at the center. | Y                 |
| 4   | The first lead card determines the first player.                               | Y                 |
| 5   | Deal 7 cards to each of the 4 players.                                         | Y                 |
| 6   | All players must follow the suit or rank of the lead card.                     | Y                 |
| 7   | The highest-rank card with the same suit as the lead card wins the trick.      | Y                 |
| 8   | The winner of a trick leads the next card.                                     | Y                 |

## C. Part 2 Feature Completion

Mark Y for Complete, P for Partial done, N for Not implemented.

| No  | Feature                                                                                                                               | Completed (Y/P/N) |
| --- | ------------------------------------------------------------------------------------------------------------------------------------- | ----------------- |
| 1   | If a player cannot follow suit or rank, the player must draw from the deck until a card can be played.                                | Y                 |
| 2   | When the remaining deck is exhausted and the player cannot play, the player does not play in the trick.                               | Y                 |
| 3   | Finish a round of game correctly. Display the score of each player.                                                                   | Y                 |
| 4   | Can exit and save the game (use file or database).                                                                                    | Y                 |
| 5   | Can resume the game. The state of the game is restored when resuming a game (use file or database).                                   | Y                 |
| 6   | Reset the game. All scores become zero. Round and trick number restart from 1.                                                        | Y                 |
| 7   | Support GUI playing mode (cards should be faced up or down as in the real game). The GUI can be in JavaFX, Swing, Spring, or Android. | Y                 |
| 8   | Keep the console output to facilitate checking. The data in console output and the GUI must tally.                                    | Y                 |

## D. Link to Video Presentation

[PART 1 Presentation Video](https://drive.google.com/file/d/1rO1p65mG7wWz_-mbA34g-EFYixsFK9dK/view?usp=share_link) <br />
[PART 2 Presentation Video](https://www.youtube.com/watch?v=dQw4w9WgXcQ) <br />

## E. Compilation Instructions

How to run the code?

For cli version

```
javac *.java && java Main
```

For GUI version

```
javac *.java && java GUI
```
