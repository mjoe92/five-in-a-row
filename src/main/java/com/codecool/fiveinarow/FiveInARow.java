package com.codecool.fiveinarow;

import static com.codecool.fiveinarow.InputManager.*;

public class FiveInARow {

    public static void main(String[] args) {

        final int numberOfPlayers = 2;
        final int howMany = 5;
        final int[] boardSize = getBoardSize(howMany);

        Game game = new Game(boardSize[0], boardSize[1]);

        for (int player = 1; player <= numberOfPlayers; player++) {

            boolean isPlayerAi = turnAI(
                    "Do you want to enable AI for player " + player + "?\n"); //by Joe

            if (isPlayerAi)
                game.enableAi(player);
        }

        game.play(howMany);
    }

    public static int[] getBoardSize(int howMany) {
        int[] boardSize = new int[2];
        boardSize[0] = getNumberWithBoundaries("Set the number of rows:", howMany, 26);
        boardSize[1] = getNumberWithBoundaries("Set the number of columns:", howMany, 50);
        return boardSize;
    }

}