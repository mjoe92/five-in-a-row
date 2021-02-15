package com.codecool.fiveinarow;

import java.util.Scanner;

public class InputManager {

    public static Scanner userInput = new Scanner(System.in);

    public static boolean turnAI(String message) {

        String input = "";
        boolean trigger = true;
        while (trigger) {
            System.out.print(message + " ");

            input = inputOrQuitGame(userInput).toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
                return false;
            }

            if (input.equals("true") || input.equals("false")) {
                trigger = false;
            } else {
                System.out.println(Displays.falseInput());
            }
        }
        return Boolean.parseBoolean(input);
    }

//    public static int getNumber(String message) {
//
//        System.out.print(message + " ");
//
//        String input = inputOrQuitGame(userInput);
//        int answer;
//        try {
//            answer = Integer.parseInt(input);
//        } catch (Exception ex) {
//
//            answer = 0;
//        }
//        return answer;
//    }

    public static int getNumberWithBoundaries(String message, int min, int max) {
        while (true) {
            System.out.print(message + " ");
            try {
                int number = Integer.parseInt(inputOrQuitGame(userInput));
                if (number < min || number > max)
                    throw new ArithmeticException();
                return number;
            } catch (ArithmeticException | NumberFormatException e) {
                System.out.println("Invalid value!");
            }
        }
    }

    public static char getRow(String message, char lastRow) {

        System.out.print(message + " ");

        System.out.printf("Row (A-%s): ", lastRow);
        char userInputForRow = '#';
        while (userInputForRow < 'A' || userInputForRow > lastRow) {
            try {
                userInputForRow = inputOrQuitGame(userInput).toUpperCase().charAt(0);
                if (userInputForRow < 'A' || userInputForRow > lastRow) {
                    throw new RuntimeException();
                }
            } catch (Exception rte) {
                System.out.printf("Not a valid coordinate, please type it again (A-%s): ", lastRow);
            }
        }
        return userInputForRow;
    }

    public static int getColumn(String message, int lastColumn) {

        System.out.print(message + " ");

        System.out.printf("Column (1-%d): ", lastColumn);
        int userInputForColumn = -1;
        while (userInputForColumn < 1 || userInputForColumn > lastColumn) {
            try {
                userInputForColumn = Integer.parseInt(inputOrQuitGame(userInput));
                if (userInputForColumn < 1 || userInputForColumn > lastColumn) {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException ex) {
                System.out.printf("Not a valid coordinate, please type it again (1-%d): ", lastColumn);
            }
        }
        return userInputForColumn;
    }

    public static String inputOrQuitGame(Scanner userInput) {
        String input = userInput.nextLine().toLowerCase();
        if (input.equals("exit") || input.equals("quit")) {
            System.out.println("Thank you for the game! Come back soon!");
            System.exit(0);
        }

        return input;
    }

}
