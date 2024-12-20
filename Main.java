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


class Coffee {
    protected EnumMap<Resource, Integer> coffee;

    public Coffee() {
        coffee = new EnumMap<>(Resource.class);
    }

    public int getWaterAmount() {
        return coffee.get(Resource.WATER);
    }

    public int getMilkAmount() {
        return coffee.get(Resource.MILK);
    }

    public int getCoffeeBeansAmount() {
        return coffee.get(Resource.COFFEE_BEANS);
    }

    public int getMoneyAmount() {
        return coffee.get(Resource.MONEY);
    }
}


final class Espresso extends Coffee {
    public Espresso() {
        coffee.put(Resource.WATER, 250);
        coffee.put(Resource.MILK, 0);
        coffee.put(Resource.COFFEE_BEANS, 16);
        coffee.put(Resource.MONEY, 4);
    }
}


final class Latte extends Coffee {
    public Latte() {
        coffee.put(Resource.WATER, 350);
        coffee.put(Resource.MILK, 75);
        coffee.put(Resource.COFFEE_BEANS, 20);
        coffee.put(Resource.MONEY, 7);
    }
}


final class Cappuccino extends Coffee {
    public Cappuccino() {
        coffee.put(Resource.WATER, 200);
        coffee.put(Resource.MILK, 100);
        coffee.put(Resource.COFFEE_BEANS, 12);
        coffee.put(Resource.MONEY, 6);
    }
}


final class CoffeeMachine {
    private final EnumMap<Resource, Integer> resources;

    public CoffeeMachine() {
        this.resources = new EnumMap<>(Resource.class);
        this.resources.put(Resource.WATER, 400);
        this.resources.put(Resource.MILK, 540);
        this.resources.put(Resource.COFFEE_BEANS, 120);
        this.resources.put(Resource.CUPS, 9);
        this.resources.put(Resource.MONEY, 550);
    }

    public int getWaterAmount() {
        return this.resources.get(Resource.WATER);
    }

    public void setWaterAmount(int value) {
        this.resources.put(Resource.WATER, value);
    }

    public int getMilkAmount() {
        return this.resources.get(Resource.MILK);
    }

    public void setMilkAmount(int value) {
        this.resources.put(Resource.MILK, value);
    }

    public int getCoffeeBeansAmount() {
        return this.resources.get(Resource.COFFEE_BEANS);
    }

    public void setCoffeeBeansAmount(int value) {
        this.resources.put(Resource.COFFEE_BEANS, value);
    }

    public int getDisposableCupsNumber() {
        return this.resources.get(Resource.CUPS);
    }

    public void setDisposableCupsNumber(int value) {
        this.resources.put(Resource.CUPS, value);
    }

    public int getMoneyAmount() {
        return this.resources.get(Resource.MONEY);
    }

    public void setMoneyAmount(int value) {
        this.resources.put(Resource.MONEY, value);
    }

    public void updateMachineResources(Coffee coffee) {
        this.setWaterAmount(this.getWaterAmount() - coffee.getWaterAmount());
        this.setMilkAmount(this.getMilkAmount() - coffee.getMilkAmount());
        this.setCoffeeBeansAmount(this.getCoffeeBeansAmount() - coffee.getCoffeeBeansAmount());
        this.setDisposableCupsNumber(this.getDisposableCupsNumber() - 1);
        this.setMoneyAmount(this.getMoneyAmount() + coffee.getMoneyAmount());
    }

    public void checkResourceAvailability(Coffee coffee) throws NotEnoughResourceException {

        if (this.getWaterAmount() < coffee.getWaterAmount()) {
            throw new NotEnoughResourceException(warningMessage(Resource.WATER));
        }

        if (this.getMilkAmount() < coffee.getMilkAmount()) {
            throw new NotEnoughResourceException(warningMessage(Resource.MILK));
        }

        if (this.getCoffeeBeansAmount() < coffee.getCoffeeBeansAmount()) {
            throw new NotEnoughResourceException(warningMessage(Resource.COFFEE_BEANS));
        }

        if (this.getDisposableCupsNumber() == 0) {
            throw new NotEnoughResourceException(warningMessage(Resource.CUPS));
        }
    }

    public void displayStatus() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.getWaterAmount());
        System.out.printf("%d ml of milk\n", this.getMilkAmount());
        System.out.printf("%d g of coffee beans\n", this.getCoffeeBeansAmount());
        System.out.printf("%d disposable cups\n", this.getDisposableCupsNumber());
        System.out.printf("$%d of money\n", this.getMoneyAmount());
    }

    private String warningMessage(Resource resource) {
        return "Sorry, not enough " + resource.toString().toLowerCase() + "!";
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
    public static boolean buyOption(CoffeeMachine machine) {
        boolean isPurchaseSuccess = true;

        String buyOptionMessage = "What do you want to buy? " +
                                  "1 - espresso, " +
                                  "2 - latte, " +
                                  "3 - cappuccino, " +
                                  "back - to main menu:";

        System.out.println(buyOptionMessage);

        String buyOptionValue = DataProcessor.readValue();
        Coffee coffee;

        switch (buyOptionValue) {
            case "1" -> coffee = new Espresso();
            case "2" -> coffee = new Latte();
            case "3" -> coffee = new Cappuccino();
            default -> {
                return false;
            }
        }

        try {
            machine.checkResourceAvailability(coffee);
        } catch (NotEnoughResourceException notEnoughResourceException) {
            System.out.println(notEnoughResourceException.getLocalizedMessage());
            return false;
        }

        machine.updateMachineResources(coffee);

        return isPurchaseSuccess;
    }

    public static void fillOption(CoffeeMachine machine) {
        int resourceIntValue;

        System.out.println("Write how many ml of water you want to add:");
        resourceIntValue = Integer.parseInt(DataProcessor.readValue());

        machine.setWaterAmount(machine.getWaterAmount() + resourceIntValue);

        System.out.println("Write how many ml of milk you want to add:");
        resourceIntValue = Integer.parseInt(DataProcessor.readValue());

        machine.setMilkAmount(machine.getMilkAmount() + resourceIntValue);

        System.out.println("Write how many grams of coffee beans you want to add:");
        resourceIntValue = Integer.parseInt(DataProcessor.readValue());

        machine.setCoffeeBeansAmount(machine.getCoffeeBeansAmount() + resourceIntValue);

        System.out.println("Write how many disposable cups you want to add:");
        resourceIntValue = Integer.parseInt(DataProcessor.readValue());

        machine.setDisposableCupsNumber(machine.getDisposableCupsNumber() + resourceIntValue);
    }

    public static void takeOption(CoffeeMachine machine) {
        System.out.printf("I gave you $%d\n", machine.getMoneyAmount());
        machine.setMoneyAmount(0);
    }

    public static void remainingOption(CoffeeMachine machine) {
        machine.displayStatus();
    }

    public static void exitOption() {
        System.exit(0);
    }
}


final class MainMenuProcessor {
    public static void processMainMenu(CoffeeMachine machine, MainMenuOption option) {
        switch (option) {
            case BUY -> {
                if (MainMenuOperations.buyOption(machine)) {
                    System.out.println("I have enough resources, making you a coffee!");
                }
            }
            case FILL -> MainMenuOperations.fillOption(machine);
            case TAKE -> MainMenuOperations.takeOption(machine);
            case REMAINING -> MainMenuOperations.remainingOption(machine);
            case EXIT -> MainMenuOperations.exitOption();
        }

        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            String enteredOption = DataProcessor.readValue("Write action (buy, fill, take, remaining, exit):");

            ArrayList<MainMenuOption> mainMenuOption;

            try {
                mainMenuOption = DataProcessor.validateOption(enteredOption, MainMenuOption.class);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getLocalizedMessage());
                continue;
            }

            MainMenuProcessor.processMainMenu(coffeeMachine, mainMenuOption.get(0));
        }
    }
}


final class DataProcessor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readValue() {
        return scanner.nextLine();
    }

    public static String readValue(String string) {
        System.out.println(string);

        String readValue = scanner.nextLine();

        System.out.println();

        return readValue;
    }

    public static <E extends Enum<E>> ArrayList<E> validateOption(String strValue, Class<E> enumClass) {
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
}


final class NotEnoughResourceException extends Exception {
    public NotEnoughResourceException(String message) {
        super(message);
    }
}