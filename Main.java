package org.example;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;


enum MainMenuOption {
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT
}


enum Resource {
    WATER,
    MILK,
    COFFEE_BEANS,
    CUPS,
    MONEY
}


final class CoffeeMachine {
    private final EnumMap<Resource, Integer> resources;

    public CoffeeMachine() {
        resources = new EnumMap<>(Resource.class);
        resources.put(Resource.WATER, 400);
        resources.put(Resource.MILK, 540);
        resources.put(Resource.COFFEE_BEANS, 120);
        resources.put(Resource.CUPS, 9);
        resources.put(Resource.MONEY, 550);
    }

    public int getWaterAmount() {
        return this.resources.get(Resource.WATER);
    }

    public void updateWaterAmount(int value) {
        this.resources.put(Resource.WATER, value);
    }

    public int getMilkAmount() {
        return this.resources.get(Resource.MILK);
    }

    public void updateMilkAmount(int value) {
        this.resources.put(Resource.MILK, value);
    }

    public int getCoffeeBeansAmount() {
        return this.resources.get(Resource.COFFEE_BEANS);
    }

    public void updateCoffeeBeansAmount(int value) {
        this.resources.put(Resource.COFFEE_BEANS, value);
    }

    public int getDisposableCupsNumber() {
        return this.resources.get(Resource.CUPS);
    }

    public void updateDisposableCupsNumber(int value) {
        this.resources.put(Resource.CUPS, value);
    }

    public int getMoneyAmount() {
        return this.resources.get(Resource.MONEY);
    }

    public void updateMoneyAmount(int value) {
        this.resources.put(Resource.MONEY, value);
    }

    public void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.getWaterAmount());
        System.out.printf("%d ml of milk\n", this.getMilkAmount());
        System.out.printf("%d g of coffee beans\n", this.getCoffeeBeansAmount());
        System.out.printf("%d disposable cups\n", this.getDisposableCupsNumber());
        System.out.printf("$%d of money\n", this.getMoneyAmount());
    }
}


final class MainMenuProcessor {
    public static void processMainMenu(CoffeeMachine machine, MainMenuOption option) {
        switch (option) {
            case BUY -> System.out.println("> Buy");
            case FILL -> System.out.println(">> Fill");
            case TAKE -> System.out.println(">>> Take");
            case REMAINING -> machine.displayStatus();
            case EXIT -> System.exit(0);
        }
    }
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

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String mainMenuOption = scanner.nextLine();

            MainMenuOption validatedValue;

            try {
                validatedValue = validateOption(mainMenuOption, MainMenuOption.class);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getLocalizedMessage());
                System.out.println();
                continue;
            }

            CoffeeMachine coffeeMachine = new CoffeeMachine();

            MainMenuProcessor.processMainMenu(coffeeMachine, validatedValue);
        }
    }
}
