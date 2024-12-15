package org.example;

import java.util.Scanner;


enum Option {
    BUY,
    FILL,
    TAKE
}

class CoffeeDrink {
    int waterAmount;
    int milkAmount;
    int coffeeBeans;
    int cost;
}

final class Espresso extends CoffeeDrink {
    public Espresso() {
        this.waterAmount = 250;
        this.milkAmount = 0;
        this.coffeeBeans = 16;
        this.cost = 4;
    }
}

final class Latte extends CoffeeDrink {
    public Latte() {
        this.waterAmount = 350;
        this.milkAmount = 75;
        this.coffeeBeans = 20;
        this.cost = 7;
    }
}

final class Cappuccino extends CoffeeDrink {
    public Cappuccino() {
        this.waterAmount = 200;
        this.milkAmount = 100;
        this.coffeeBeans = 12;
        this.cost = 6;
    }
}

public class CoffeeMachine {
    private int waterAmount;
    private int milkAmount;
    private int coffeeBeansAmount;
    private int moneyAmount;
    private int disposableCupsNumber;

    public CoffeeMachine() {
        this.waterAmount = 400;
        this.milkAmount = 540;
        this.coffeeBeansAmount = 120;
        this.moneyAmount = 550;
        this.disposableCupsNumber = 9;
    }

    private void PrintCoffeeMachineState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", waterAmount);
        System.out.printf("%d ml of milk\n", milkAmount);
        System.out.printf("%d g of coffee beans\n", coffeeBeansAmount);
        System.out.printf("%d disposable cups\n", disposableCupsNumber);
        System.out.printf("$%d of money\n", moneyAmount);
    }

    private static String ReadValue() {
        return new Scanner(System.in).nextLine();
    }

    private static void HandleOrder(CoffeeMachine coffeeMachine, CoffeeDrink coffeeDrink) {
        coffeeMachine.waterAmount -= coffeeDrink.waterAmount;
        coffeeMachine.milkAmount -= coffeeDrink.milkAmount;
        coffeeMachine.coffeeBeansAmount -= coffeeDrink.coffeeBeans;
        coffeeMachine.moneyAmount += coffeeDrink.cost;
        --coffeeMachine.disposableCupsNumber;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.PrintCoffeeMachineState();

        System.out.println();
        System.out.println("Write action (buy, fill, take)");

        String enteredOption = ReadValue().toUpperCase();
        Option validatedOption;

        try {
            validatedOption = Option.valueOf(enteredOption);
        } catch (IllegalArgumentException exception) {
            System.out.printf("%s is not supported option", enteredOption);
            return;
        }

        switch (validatedOption) {
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                int coffeeDrinkTypeOrder = Integer.parseInt(ReadValue());

                switch (coffeeDrinkTypeOrder) {
                    case 1:
                        CoffeeDrink espresso = new Espresso();
                        HandleOrder(coffeeMachine, espresso);
                        break;
                    case 2:
                        CoffeeDrink latte = new Latte();
                        HandleOrder(coffeeMachine, latte);
                        break;
                    case 3:
                        CoffeeDrink cappuccino = new Cappuccino();
                        HandleOrder(coffeeMachine, cappuccino);
                        break;
                }
                break;

            case FILL:
                System.out.println("Write how many ml of water you want to add:");
                coffeeMachine.waterAmount += Integer.parseInt(ReadValue());

                System.out.println("Write how many ml of milk you want to add:");
                coffeeMachine.milkAmount += Integer.parseInt(ReadValue());

                System.out.println("Write how many grams of coffee beans you want to add:");
                coffeeMachine.coffeeBeansAmount += Integer.parseInt(ReadValue());

                System.out.println("Write how many disposable cups you want to add:");
                coffeeMachine.disposableCupsNumber += Integer.parseInt(ReadValue());
                break;

            case TAKE:
                System.out.printf("I gave you $%d\n", coffeeMachine.moneyAmount);

                coffeeMachine.moneyAmount = 0;
                break;
        }

        System.out.println();
        coffeeMachine.PrintCoffeeMachineState();
    }
}