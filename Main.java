package org.example;


import java.util.Scanner;


enum MainMenuOption {
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT
}


public class Main {
    private static <E extends Enum<E>> E validateOption(String strValue, Class<E> enumClass) {
        try {
            return Enum.valueOf(enumClass, strValue.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Unsupported option: " + strValue);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean is_exit = false;

        while (!is_exit) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String mainMenuOption = scanner.nextLine();

            MainMenuOption validatedValue;

            try {
                validatedValue = validateOption(mainMenuOption, MainMenuOption.class);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getLocalizedMessage());
                continue;
            }

            System.out.println(validatedValue);
            System.out.println();
        }
    }
}
