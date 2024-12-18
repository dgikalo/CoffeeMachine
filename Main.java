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
    private static EnumMap<Resource, Integer> resources;

    public CoffeeMachine() {
        resources = new EnumMap<>(Resource.class);
        resources.put(Resource.WATER, 400);
        resources.put(Resource.MILK, 540);
        resources.put(Resource.COFFEE_BEANS, 120);
        resources.put(Resource.CUPS, 9);
        resources.put(Resource.MONEY, 550);
    }

    public Integer getWaterAmount() {
        return resources.get(Resource.WATER);
    }

    public void updateWaterAmount(int value) {
        resources.put(Resource.WATER, value);
    }

    public Integer getMilkAmount() {
        return resources.get(Resource.MILK);
    }

    public void updateMilkAmount(int value) {
        resources.put(Resource.MILK, value);
    }

    public Integer getCoffeeBeansAmount() {
        return resources.get(Resource.COFFEE_BEANS);
    }

    public void updateCoffeeBeansAmount(int value) {
        resources.put(Resource.COFFEE_BEANS, value);
    }

    public Integer getDisposableCupsNumber() {
        return resources.get(Resource.CUPS);
    }

    public void updateDisposableCupsNumber(int value) {
        resources.put(Resource.CUPS, value);
    }

    public Integer getMoneyAmount() {
        return resources.get(Resource.MONEY);
    }

    public Integer updateMoneyAmount(int value) {
        resources.put(Resource.MONEY, value);
    }

    public void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water", getWaterAmount());
        System.out.printf("%d ml of milk", getMilkAmount());
        System.out.printf("%d g of coffee beans", getCoffeeBeansAmount());
        System.out.printf("% disposable cups", getDisposableCupsNumber());
        System.out.printf("$%d of money", getMoneyAmount());
    }
}


final class MainMenuProcessor {

    public static void processMainMenu(MainMenuOption option) {
        switch (option) {
            case BUY -> System.out.println("Buy");
            case FILL -> System.out.println("Fill");
            case TAKE -> System.out.println("Take");
            case REMAINING -> System.out.println("Remaining");
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

            if (validatedValue.equals(MainMenuOption.EXIT)) {
                return;
            }
        }
    }
}
