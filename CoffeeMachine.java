package org.example;

import javax.print.attribute.standard.Finishings;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static int FindMinima(int a, int b, int c) {
        int minima = Math.min(a, b);
        minima = Math.min(minima, c);

        return minima;
    }

    public static void main(String[] args) {
    }
}


class CoffeeMachine {
    // Initial amount in milliliters
    private int waterAmount;
    private int milkAmount;

    // Initial amount of coffee beans in grams
    private int coffeeBeansAmount;

    // Initial amount of money in dollars
    private int moneyAmount;

    // Initial number of the disposable cups in pieces
    private int disposableCupsNumber;

    public CoffeeMachine() {
        this.waterAmount = 400;
        this.milkAmount = 540;
        this.coffeeBeansAmount = 120;
        this.moneyAmount = 550;
        this.disposableCupsNumber = 9;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    public int getCoffeeBeansAmount() {
        return coffeeBeansAmount;
    }

    public void setCoffeeBeansAmount(int coffeeBeansAmount) {
        this.coffeeBeansAmount = coffeeBeansAmount;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public int getDisposableCupsNumber() {
        return disposableCupsNumber;
    }

    public void setDisposableCupsNumber(int disposableCupsNumber) {
        this.disposableCupsNumber = disposableCupsNumber;
    }
}

abstract class CoffeeDrink {
    protected int waterAmount;
    protected int milkAmount;
    protected int coffeeBeansAmount;
    protected int price;

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public int getCoffeeBeansAmount() {
        return coffeeBeansAmount;
    }

    public int getPrice() {
        return price;
    }
}

class Cappuccino extends CoffeeDrink {
    public Cappuccino() {
        super.waterAmount = 200;
        super.milkAmount = 100;
        super.coffeeBeansAmount = 12;
        super.price = 6;
    }

    @Override
    public int getWaterAmount() {
        return super.getWaterAmount();
    }

    @Override
    public int getMilkAmount() {
        return super.getMilkAmount();
    }

    @Override
    public int getCoffeeBeansAmount() {
        return super.getCoffeeBeansAmount();
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }
}

class Espresso extends CoffeeDrink {
    public Espresso() {
        super.waterAmount = 250;
        super.milkAmount = 0;
        super.coffeeBeansAmount = 16;
        super.price = 4;
    }

    @Override
    public int getWaterAmount() {
        return super.getWaterAmount();
    }

    @Override
    public int getMilkAmount() {
        return super.getMilkAmount();
    }

    @Override
    public int getCoffeeBeansAmount() {
        return super.getCoffeeBeansAmount();
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }
}

class Latte extends CoffeeDrink {
    public Latte() {
        super.waterAmount = 350;
        super.milkAmount = 75;
        super.coffeeBeansAmount = 20;
        super.price = 7;
    }

    @Override
    public int getWaterAmount() {
        return super.getWaterAmount();
    }

    @Override
    public int getMilkAmount() {
        return super.getMilkAmount();
    }

    @Override
    public int getCoffeeBeansAmount() {
        return super.getCoffeeBeansAmount();
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }
}
