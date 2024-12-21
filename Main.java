package org.example;


import java.util.*;


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

    public int getMilkAmount() {
        return this.resources.get(Resource.MILK);
    }

    public int getCoffeeBeansAmount() {
        return this.resources.get(Resource.COFFEE_BEANS);
    }

    public int getDisposableCupsNumber() {
        return this.resources.get(Resource.CUPS);
    }

    public int getMoneyAmount() {
        return this.resources.get(Resource.MONEY);
    }

    public void setMoneyAmount(int value) {
        this.resources.put(Resource.MONEY, value);
    }

    public void updateResourceAmount(Resource resource, int value) {
        this.resources.put(resource, this.resources.get(resource) + value);
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

        machine.updateResourceAmount(Resource.WATER, -coffee.getWaterAmount());
        machine.updateResourceAmount(Resource.MILK, -coffee.getMilkAmount());
        machine.updateResourceAmount(Resource.COFFEE_BEANS, -coffee.getCoffeeBeansAmount());
        machine.updateResourceAmount(Resource.CUPS, -1);
        machine.updateResourceAmount(Resource.MONEY, coffee.getMoneyAmount());

        return isPurchaseSuccess;
    }

    public static void fillOption(CoffeeMachine machine) {
        Map<Resource, String> filledResources = new LinkedHashMap<>();
        filledResources.put(Resource.WATER, "Write how many ml of water you want to add:");
        filledResources.put(Resource.MILK, "Write how many ml of milk you want to add:");
        filledResources.put(Resource.COFFEE_BEANS, "Write how many grams of coffee beans you want to add:");
        filledResources.put(Resource.CUPS, "Write how many disposable cups you want to add:");

        for (Map.Entry<Resource, String> value : filledResources.entrySet()) {
            System.out.println(value.getValue());
            machine.updateResourceAmount(value.getKey(), Integer.parseInt(DataProcessor.readValue()));
        }
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
    public static void processMainMenu(CoffeeMachine machine, String value) {
        switch (value) {
            case "buy" -> {
                if (MainMenuOperations.buyOption(machine)) {
                    System.out.println("I have enough resources, making you a coffee!");
                }
            }
            case "fill" -> MainMenuOperations.fillOption(machine);
            case "take" -> MainMenuOperations.takeOption(machine);
            case "remaining" -> MainMenuOperations.remainingOption(machine);
            case "exit" -> MainMenuOperations.exitOption();
            default -> throw new UnsupportedOperationException("Unsupported operation: " + value);
        }

        System.out.println();
    }
}


public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            String readStringValue = DataProcessor.readValue("Write action (buy, fill, take, remaining, exit):");

            try {
                MainMenuProcessor.processMainMenu(coffeeMachine, readStringValue);
            } catch (UnsupportedOperationException exception) {
                System.out.println(exception.getLocalizedMessage());
                System.out.println();
            }
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
}


final class NotEnoughResourceException extends Exception {
    public NotEnoughResourceException(String message) {
        super(message);
    }
}