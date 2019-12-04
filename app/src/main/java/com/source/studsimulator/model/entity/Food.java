package com.source.studsimulator.model.entity;


import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Price;

public class Food implements Payable, StudentActivity, ContainsRandomAction {

    private String title;
    private Price price;
    private int satietyChanging;
    private int healthChanging;
    private int energyNeeded;
    private RandomAction randomAction;

    public Food(int price, String title, int satiety, int health, int energy) throws IllegalArgumentException {
        if (satiety <= 0) {
            throw new IllegalArgumentException("Food can't have nonpositive satiety.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.price = new Price(price);
        this.title = title;
        this.satietyChanging = satiety;
        this.healthChanging = health;
        this.energyNeeded = energy;
    }

    public Food(int price, String title, int satiety, int health, int energy, RandomAction randomAction) throws IllegalArgumentException {
        if (satiety <= 0) {
            throw new IllegalArgumentException("Food can't have nonpositive satiety.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.price = new Price(price);
        this.title = title;
        this.satietyChanging = satiety;
        this.healthChanging = health;
        this.energyNeeded = energy;
        this.randomAction = randomAction;
    }

    @Override
    public int getEnergyNeeded() {
        return energyNeeded;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public int getHealthChanging() {return healthChanging; }

    @Override
    public void setEnable(int characteristicForBlock) {}

    @Override
    public boolean isEnable() {
        return true;
    }

    @Override
    public RandomAction getRandomAction() {
        return randomAction;
    }
}
