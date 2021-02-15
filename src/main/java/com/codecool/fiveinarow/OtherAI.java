package com.codecool.fiveinarow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OtherAI {
    private final Game currentGame;
    private final int player;
    private final int opponent;
    private final int nrOfRows;
    private final int nrOfCols;
    private int[] nextMove = new int[]{0, 0};
    private final static Random rnd = new Random();
   private static int steps = 0;

    OtherAI(Game game, int player) {
        this.currentGame = game;
        this.nrOfRows = getCurrentGame().getBoard().length;
        this.nrOfCols = getCurrentGame().getBoard()[0].length;
        this.player = player;
        this.opponent = 3 - player;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public int[] getNextMove() {
        return nextMove;
    }

    public int getPlayer() {
        return player;
    }

    public void setNextMove(int[] nextMove) {
        this.nextMove = nextMove;
    }

    private int getField(int row, int col) {
        return getCurrentGame().getBoard()[row][col];
    }

    int[] getAiMove() {
//        steps++;
        if (canPlayerWin(5)) {
            return getNextMove();
        } else if (canOpponentWin(5)) {
            return getNextMove();
        } else if (canPlayerWin(4)) {
            return getNextMove();
        } else if (canOpponentWin(4)) {
            return getNextMove();
        } else {
            while (true) {
                int[] aiMove = chooseNearbyPlace();
                if (getCurrentGame().isCoordinateEmpty(aiMove))
                    return aiMove;
            }
        }
    }

    private boolean canPlayerWin(int howMany) {
        for (int row = 0; row < nrOfRows; row++) {
            for (int col = 0; col < nrOfCols; col++) {
                setNextMove(new int[]{row, col});
                if (!getCurrentGame().isCoordinateEmpty(getNextMove())) continue;
                if (canWinInColumns(player, howMany) ||
                        canWinInRows(player, howMany) ||
                        canWinInDiagonal1(player, howMany) ||
                        canWinInDiagonal2(player, howMany)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canOpponentWin(int howMany) {
        for (int row = 0; row < nrOfRows; row++) {
            for (int col = 0; col < nrOfCols; col++) {
                setNextMove(new int[]{row, col});
                if (!getCurrentGame().isCoordinateEmpty(getNextMove())) continue;
                if (canWinInColumns(opponent, howMany) ||
                        canWinInRows(opponent, howMany) ||
                        canWinInDiagonal1(opponent, howMany) ||
                        canWinInDiagonal2(opponent, howMany)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[] chooseNearbyPlace() {
        for (int row = 0; row < getCurrentGame().getBoard().length; row++) {
            for (int col = 0; col < getCurrentGame().getBoard()[0].length; col++) {
                int thePlayer = getPlayer();
                if (steps == 1 || steps == 2) {
                    thePlayer = opponent;
                }
                if (getField(row, col) == thePlayer) {
                    if (getField(row, col) == player) {
                        List<int[]> nearbyEmpty = getNearbyEmpty(row, col);
                        if (nearbyEmpty.size() == 0) continue;
                        return nearbyEmpty.get(rnd.nextInt(nearbyEmpty.size()));
                    }
                }
            }

        }
        return new int[]{rnd.nextInt(nrOfRows), rnd.nextInt(nrOfCols)};
    }


    private List<int[]> getNearbyEmpty(int row, int col) {
        List<int[]> nearbyElements = new ArrayList<>();
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                try {
                    if (getField(r, c) == 0) {
                        nearbyElements.add(new int[]{r, c});
                    }
                } catch (ArrayIndexOutOfBoundsException aioobe) {
                    //ignore
                }
            }
        }

        return nearbyElements;
    }


    private boolean canWinInColumns(int player, int howMany) {
        int nextMoveX = getNextMove()[1];
        int nextMoveY = getNextMove()[0];
        int count = 1;
        for (int y = nextMoveY - 4; y <= nextMoveY + 4; y++) {
            try {
                if (getField(y, nextMoveX) != player && y != nextMoveY) {
                    count = 1;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }
        return false;
    }

    private boolean canWinInRows(int player, int howMany) {
        int nextMoveX = getNextMove()[1];
        int nextMoveY = getNextMove()[0];
        int count = 1;
        for (int x = nextMoveX - 4; x <= nextMoveX + 4; x++) {
            try {
                if (getField(nextMoveY, x) != player && x != nextMoveX) {
                    count = 1;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }
        return false;
    }

    private boolean canWinInDiagonal1(int player, int howMany) {
        int nextMoveX = getNextMove()[1];
        int nextMoveY = getNextMove()[0];

        int x = nextMoveX - 4;
        int y = nextMoveY + 4;
        int count = 1;
        for (; x <= nextMoveX + 4; x++, y--) {
            try {
                if (getField(y, x) != player && (x != nextMoveX && y != nextMoveY)) {
                    count = 1;
                } else if (++count >= howMany) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // ignore
            }
        }

        return false;
    }

    private boolean canWinInDiagonal2(int player, int howMany) {
        int nextMoveX = getNextMove()[1];
        int nextMoveY = getNextMove()[0];

        int count = 1;
        int x = nextMoveX - 4;
        int y = nextMoveY - 4;
        for (; x <= nextMoveX + 4; x++, y++) {
            try {
                if (getField(y, x) != player && (x != nextMoveX && y != nextMoveY)) {
                    count = 1;
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
