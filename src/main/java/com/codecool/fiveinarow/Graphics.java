package com.codecool.fiveinarow;

public class Graphics {
    static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_RESET = "\u001B[0m";

    static void printInForeColor(String color, String text){
        System.out.printf(color + "%3s ", text);
    }

    static void printHorizontalBorder(Game currentGame) {
        printWhiteBlock();
        for (int col = 0; col < currentGame.getBoard()[0].length; col++) {
            System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "%3d " + ANSI_RESET, col + 1);
        }
        printWhiteBlock();
        System.out.println();
    }

    static void printLeftVerticalBorder(char letter) {
        System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "%3s " + ANSI_RESET, letter);
    }

    static void printRightVerticalBorder(char letter) {
        System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "%2s  " + ANSI_RESET, letter);
        System.out.println();
    }

    private static void printWhiteBlock() {
        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "    " + ANSI_RESET);
    }
}
