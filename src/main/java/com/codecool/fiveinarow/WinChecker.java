package com.codecool.fiveinarow;

public class WinChecker {

    static boolean hasWonInColumns(Game currentGame, int player, int howMany) {
        int lastMoveX = currentGame.getLastMove()[0];
        int lastMoveY = currentGame.getLastMove()[1];
        int count = 0;
        for (int y = lastMoveY - (howMany - 1); y <= lastMoveY + (howMany - 1); y++) {
            try {
                if (currentGame.getBoard()[lastMoveX][y] != player) {
                    count = 0;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }
        return false;
    }

    static boolean hasWonInRows(Game currentGame, int player, int howMany) {
        int lastMoveX = currentGame.getLastMove()[0];
        int lastMoveY = currentGame.getLastMove()[1];
        int count = 0;
        for (int x = lastMoveX - (howMany - 1); x <= lastMoveX + (howMany - 1); x++) {
            try {
                if (currentGame.getBoard()[x][lastMoveY] != player) {
                    count = 0;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }
        return false;
    }

    static boolean hasWonInDiagonal1(Game currentGame, int player, int howMany) {
        int lastMoveX = currentGame.getLastMove()[0];
        int lastMoveY = currentGame.getLastMove()[1];

        int x = lastMoveX - (howMany - 1);
        int y = lastMoveY + (howMany - 1);
        int count = 0;
        for (; x <= lastMoveX + (howMany - 1); x++, y--) {
            try {
                if (currentGame.getBoard()[x][y] != player) {
                    count = 0;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }

        return false;
    }

    static boolean hasWonInDiagonal2(Game currentGame, int player, int howMany) {
        int lastMoveX = currentGame.getLastMove()[0];
        int lastMoveY = currentGame.getLastMove()[1];

        int count = 0;
        int x = lastMoveX - (howMany - 1);
        int y = lastMoveY - (howMany - 1);
        for (; x <= lastMoveX + (howMany - 1); x++, y++) {
            try {
                if (currentGame.getBoard()[x][y] != player) {
                    count = 0;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }
        return false;
    }
}
