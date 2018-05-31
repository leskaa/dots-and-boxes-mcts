# Dots and Boxes Monte Carlo Tree Search Implementation

This project implements the Monte Carlo Tree Search Algorithm for the classic game of Dots and Boxes.
The version of dots and boxes featured in this program does not play by the repeat turn after completed box rule.
The Monte Carlo Tree Search is a tree search using machine learning techniques to provide advantages other game playing tree searches.
This tree search does not use a function to determine the value of specific positions.
It can be used for games without needing to already have found the way to determine the "best" move.
The algorithm can be stopped at any time and return the best option found so far.

### Difficulties

Working with many states for the board was very confusing.
Implementing the repeating turns would have required a significant change from the way the base algorithm works.
This is due to the algorithm needing to see the repeating turns during it's simulation to determine the best move.

### Most interesting piece of your code and explanation for what it does.

```Java
public void performMove(int player, Position p) {
        this.totalMoves++;
        boardValues[p.getX()][p.getY()] = player;
        for (int i = 0; i < boardValues.length; i++) {
            for (int j = 0; j < boardValues[0].length; j++) {
                if (j != 0 && j < 5) {
                    if (boardValues[i][j] != 0 && boardValues[i][j - 1] != 0
                            && boardValues[j - 1][i + 5] != 0 && boardValues[j - 1][i + 6] != 0
                            && scoredBoxes[i][j - 1] == 0) {
                        scoredBoxes[i][j - 1] = player;
                    }
                }
            }
        }
    }
```
This code converts the 2d array, storing data on the placement of lines, into a 2d array of the boxes that are filled.
It is used 1000s of times each move by the algorithm to make simulation moves.
## Built With

* [Intellij IDEA](https://www.jetbrains.com/idea/) - The IDE used

## Authors

* **Alex Leska**


## Acknowledgments

* Baeldung's tutorial to implement a Tic Tac Toe version of the Algorithm was very useful to understand how the algorithm works.
* Jeff Bradberry's tutorial was how I first learned the algorithm.
