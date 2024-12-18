package org.example;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Scanner;






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
}


enum MainMenuOption {
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT
}


final class MainMenuOperations {
    public static void buyOption(CoffeeMachine machine) {

    }

    public static void fillOption() {

    }

    public static void takeOption(CoffeeMachine machine) {
        machine.updateMoneyAmount(0);
    }

    public static void remainingOption(CoffeeMachine machine) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machine.getWaterAmount());
        System.out.printf("%d ml of milk\n", machine.getMilkAmount());
        System.out.printf("%d g of coffee beans\n", machine.getCoffeeBeansAmount());
        System.out.printf("%d disposable cups\n", machine.getDisposableCupsNumber());
        System.out.printf("$%d of money\n", machine.getMoneyAmount());
    }

    public static void exitOption() {
        System.exit(0);
    }
}


final class MainMenuProcessor {
    public static void processMainMenu(CoffeeMachine machine, MainMenuOption option) {
        switch (option) {
            case BUY -> System.out.println("> Buy");
            case FILL -> System.out.println(">> Fill");
            case TAKE -> MainMenuOperations.takeOption(machine);
            case REMAINING -> MainMenuOperations.remainingOption(machine);
            case EXIT -> MainMenuOperations.exitOption();
        }
    }
}


public class Main {
    private static <E extends Enum<E>> ArrayList<E> validateOption(String strValue, Class<E> enumClass) {
        ArrayList<E> returnedValue = new ArrayList<>();
        E validatedValue;

        try {
            validatedValue = Enum.valueOf(enumClass, strValue.toUpperCase());
            returnedValue.add(validatedValue);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Unsupported option: " + strValue);
        }

        return returnedValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String enteredOption = scanner.nextLine();

            ArrayList<MainMenuOption> mainMenuOption = validateOption(enteredOption, MainMenuOption.class);

            if (mainMenuOption.isEmpty()) {
                continue;
            }

            CoffeeMachine coffeeMachine = new CoffeeMachine();

            MainMenuProcessor.processMainMenu(coffeeMachine, mainMenuOption.getFirst());
        }
    }
}
