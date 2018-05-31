package com.company;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int[][] boardValues;
    int[][] scoredBoxes;
    int totalMoves;

    public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    public Board() {
        boardValues = new int[4][10];
        scoredBoxes = new int[4][4];
    }

    public Board(Board board) {
        int boardLength = board.getBoardValues().length;
        int boardWidth = board.getBoardValues()[0].length;
        this.boardValues = new int[boardLength][boardWidth];
        int[][] boardValues = board.getBoardValues();
        int n = boardValues.length;
        for (int i = 0; i < n; i++) {
            int m = boardValues[i].length;
            for (int j = 0; j < m; j++) {
                this.boardValues[i][j] = boardValues[i][j];
            }
        }
        this.scoredBoxes = new int[4][4];
        int[][] scoredBoxes = board.getScoredBoxes();
        n = scoredBoxes.length;
        for (int i = 0; i < n; i++) {
            int m = scoredBoxes[i].length;
            for (int j = 0; j < m; j++) {
                this.scoredBoxes[i][j] = scoredBoxes[i][j];
            }
        }

    }

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

    public int[][] getBoardValues() {
        return boardValues;
    }

    public int[][] getScoredBoxes() {
        return scoredBoxes;
    }

    public int checkStatus() {
        int checkGameForWin = checkForWin();
        if (checkGameForWin != 0) {
            return checkGameForWin;
        }
        if (getEmptyPositions().size() > 0) {
            return IN_PROGRESS;
        } else {
            return DRAW;
        }
    }

    private int checkForWin() {
        boolean isGameOver = true;
        int playerOneScore = 0;
        int playerTwoScore = 0;
        for (int[] outside : scoredBoxes) {
            for (int element : outside) {
                if (element == 0) {
                    isGameOver = false;
                } else if (element == 1) {
                    playerOneScore++;
                } else if (element == 2) {
                    playerTwoScore++;
                }
            }
        }
        if (!isGameOver) {
            return 0;
        } else if (playerOneScore > playerTwoScore) {
            return 1;
        } else if (playerTwoScore > playerOneScore) {
            return 2;
        } else {
            return 0;
        }
    }

    public void printBoard() {
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 4; l++) {
                System.out.print(".");
                if (boardValues[l][k] != 0) {
                    System.out.print("_");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(".");
            if (k != 4) {
                for (int m = 0; m < 5; m++) {
                    if (boardValues[k][m + 5] != 0) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                    if (m == 4) {
                        System.out.println();
                    } else {
                        System.out.print(scoredBoxes[m][k]);
                    }
                }
            }
        }
    }

    public List<Position> getEmptyPositions() {
        int length = this.boardValues.length;
        int width = this.boardValues[0].length;
        List<Position> emptyPositions = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (boardValues[i][j] == 0)
                    emptyPositions.add(new Position(i, j));
            }
        }
        return emptyPositions;
    }

    public void printStatus() {
        switch (this.checkStatus()) {
            case P1:
                System.out.println("Player 1 wins");
                break;
            case P2:
                System.out.println("Player 2 wins");
                break;
            case DRAW:
                System.out.println("Game Draw");
                break;
            case IN_PROGRESS:
                System.out.println("Game In Progress");
                break;
        }
    }
}
