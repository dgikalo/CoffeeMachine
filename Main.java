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

    public boolean checkResourceAvailability(Coffee coffee) {
        boolean isResourceEnough = true;

        if (this.getWaterAmount() < coffee.getWaterAmount()) {
            System.out.println(warningMessage(Resource.WATER));
            isResourceEnough = false;
        }

        if (this.getMilkAmount() < coffee.getMilkAmount() && isResourceEnough) {
            System.out.println(warningMessage(Resource.MILK));
            isResourceEnough = false;
        }

        if (this.getCoffeeBeansAmount() < coffee.getCoffeeBeansAmount() && isResourceEnough) {
            System.out.println(warningMessage(Resource.COFFEE_BEANS));
            isResourceEnough = false;
        }

        if (this.getDisposableCupsNumber() == 0 && isResourceEnough) {
            System.out.println(warningMessage(Resource.CUPS));
            isResourceEnough = false;
        }

        return isResourceEnough;
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

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String readValue = DataProcessor.readValue();
        int recipeOption = Integer.parseInt(readValue);

        switch (recipeOption) {
            case 1 -> {
                Coffee espresso = new Espresso();

                if (!machine.checkResourceAvailability(espresso)) {
                    isPurchaseSuccess = false;
                    break;
                }

                machine.updateMachineResources(espresso);
            }

            case 2 -> {
                Coffee latte = new Latte();

                if (!machine.checkResourceAvailability(latte)) {
                    isPurchaseSuccess = false;
                    break;
                }

                machine.updateMachineResources(latte);
            }

            case 3 -> {
                Coffee cappuccino = new Cappuccino();

                if (!machine.checkResourceAvailability(cappuccino)) {
                    isPurchaseSuccess = false;
                    break;
                }

                machine.updateMachineResources(cappuccino);
            }
        }

        return isPurchaseSuccess;
    }

    public static void fillOption(CoffeeMachine machine) {
        int resourceValue;

        System.out.println("Write how many ml of water you want to add:");
        resourceValue = Integer.parseInt(DataProcessor.readValue());

        machine.setWaterAmount(machine.getWaterAmount() + resourceValue);

        System.out.println("Write how many ml of milk you want to add:");
        resourceValue = Integer.parseInt(DataProcessor.readValue());

        machine.setMilkAmount(machine.getMilkAmount() + resourceValue);

        System.out.println("Write how many grams of coffee beans you want to add:");
        resourceValue = Integer.parseInt(DataProcessor.readValue());

        machine.setCoffeeBeansAmount(machine.getCoffeeBeansAmount() + resourceValue);

        System.out.println("Write how many disposable cups you want to add:");
        resourceValue = Integer.parseInt(DataProcessor.readValue());

        machine.setDisposableCupsNumber(machine.getDisposableCupsNumber() + resourceValue);
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
    }
}


public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String enteredOption = DataProcessor.readValue();

            ArrayList<MainMenuOption> mainMenuOption;

            try {
                mainMenuOption = DataProcessor.validateOption(enteredOption, MainMenuOption.class);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getLocalizedMessage());
                continue;
            }

            MainMenuProcessor.processMainMenu(coffeeMachine, mainMenuOption.getFirst());
        }
    }
}


final class DataProcessor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readValue() {
        return scanner.nextLine();
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