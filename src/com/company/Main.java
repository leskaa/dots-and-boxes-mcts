package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
        int player = Board.P1;
        int totalMoves = 40;
        for (int i = 0; i < totalMoves; i++) {
            board.printBoard();
            System.out.println("-----");
            board = mcts.findNextMove(board, player);
            if (board.checkStatus() > -1) {
                break;
            }
            player = 3 - player;
        }
        board.printBoard();
        board.printStatus();
    }
}