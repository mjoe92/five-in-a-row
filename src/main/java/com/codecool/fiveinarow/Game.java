package com.codecool.fiveinarow;

import static com.codecool.fiveinarow.Graphics.*;
import static com.codecool.fiveinarow.InputManager.*;
import static com.codecool.fiveinarow.WinChecker.*;

public class Game implements GameInterface {
    private int[][] board;
    private final boolean[] isPlayerAi = new boolean[2];
    private final OtherAI[] enabledAi = new OtherAI[2];
    private int[] lastMove;

    public Game(int nRows, int nCols) {
        int[][] empty = new int[nRows][nCols];
        setBoard(empty);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int[] lastMove) {
        this.lastMove = lastMove;
    }

    public int[] getMove(int player) {
        int lastColumn = getBoard()[0].length;
        char lastRow = (char) (getBoard().length - 1 + 65);
        int[] coords = new int[2];
        while (true) {
            System.out.printf("Player%d, enter your move!%n", player);

            char userInputForRow = getRow("", lastRow);
            coords[0] = (int) userInputForRow - 65;

            int userInputForColumn = getColumn("", lastColumn);
            coords[1] = userInputForColumn - 1;
            System.out.println("Your choice: " + userInputForRow + userInputForColumn);

            if (isCoordinateEmpty(coords)) {
                break;
            }

            System.out.println("This place is taken, please choose another!");
        }
        return coords;
    }

    boolean isCoordinateEmpty(int[] coords) {
        return getBoard()[coords[0]][coords[1]] == 0;
    }

    public int[] getAiMove(int player) {
        System.out.printf("\nPlayer%d (AI), enter your move: ", player);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int[] aiMove = enabledAi[player - 1].getAiMove();
        System.out.printf("%s%d%n%n", (char) (aiMove[0] + 65), aiMove[1]+1);
        return aiMove;
    }

    public void mark(int player, int row, int col) {
        board[row][col] = player;
    }

    public boolean hasWon(int player, int howMany) {
        return hasWonInColumns(this, player, howMany)
                || hasWonInRows(this, player, howMany)
                || hasWonInDiagonal1(this, player, howMany)
                || hasWonInDiagonal2(this, player, howMany);
    }

    public boolean isFull() {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 0)
                    return false;
            }
        }
        return true;
    }

    // if columns being wider than the height of rows doesn't look good,
    // we can set the row height in the editor settings to higher value at
    // File > Settings > Editor > Color Scheme > Console Font > Line Spacing
    public void printBoard() {
        printHorizontalBorder(this);
        for (int i = 0; i < board.length; i++) {
            printLeftVerticalBorder((char) (i + 65));

            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0:
                        printInForeColor(ANSI_BLACK, ".");
                        break;
                    case 1:
                        printInForeColor(ANSI_RED, "X");
                        break;
                    case 2:
                        printInForeColor(ANSI_BLUE, "O");
                        break;
                }
            }
            printRightVerticalBorder((char) (i + 65));
        }
        printHorizontalBorder(this);
    }

    public void printResult(int player) {
        if (player == 1) {
            System.out.println("X won!");
        } else if (player == 2) {
            System.out.println("O won!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public void enableAi(int player) {
        enabledAi[player - 1] = new OtherAI(this, player);
        this.isPlayerAi[player - 1] = true;
    }

    public boolean isPlayerAi(int player) {
        return this.isPlayerAi[player - 1];
    }

    public void play(int howMany) {
        int player = 1;
        printBoard();
        while (true) {
            if (isPlayerAi(player)) {
                setLastMove(getAiMove(player));
            } else {
                setLastMove(getMove(player));
            }
            mark(player, getLastMove()[0], getLastMove()[1]);
            printBoard();
            if (hasWon(player, howMany)) {
                printResult(player);
                break;
            }
            if (isFull()) {
                printResult(0);
                break;
            }
            player = (player == 1) ? 2 : 1;
        }
    }
}