package com.source.studsimulator.model.entity;

import java.util.Random;

public class RandomAction {
    private String message;
    //вероятность от 1 до 100
    private int probability;
    private int healthChanging;
    private int educationChanging;
    private int satietyChanging;
    private int moneyChanging;
    private Random rand = new Random();

    public RandomAction(String message, int probability,
                        int healthChanging, int educationChanging, int satietyChanging,
                        int moneyChanging) {
        this.message = message;
        this.probability = probability;
        this.healthChanging = healthChanging;
        this.educationChanging = educationChanging;
        this.satietyChanging = satietyChanging;
        this.moneyChanging = moneyChanging;
    }

    public boolean isActive() {
        int x = rand.nextInt(100);
        return (x <= probability);
    }

    public String getMessage() {
        return message;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getEducationChanging() {
        return educationChanging;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    public int getMoneyChanging() {
        return moneyChanging;
    }
}
