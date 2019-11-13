package com.source.studsimulator.model;

import java.lang.IllegalArgumentException;


public class Student {
    private int money = 50;
    private int dollars = 0;
    private int health = 50;
    private int satiety = 50;
    private int educationLevel = 0;

    public Student() {

    }

    public void addCharacteristic(int add, GameLogic.ECharacteristics characteristic) throws IllegalArgumentException {
        if (add <= 0) {
            throw new IllegalArgumentException("Addend can't be less or equal than zero.");
        }
        switch (characteristic) {
            case HEALTH:
                health = Math.min(health + add, 100);
                break;
            case SATIETY:
                satiety = Math.min(satiety + add, 100);
                break;
            case EDUCATION_LEVEL:
                educationLevel = Math.min(educationLevel + add, 100);
                break;
        }
    }

    public void substractCharacteristic(int substract, GameLogic.ECharacteristics characteristic) throws IllegalArgumentException {
        if (substract <= 0) {
            throw new IllegalArgumentException("Subtrahend can't be less or equal than zero.");
        }
        switch (characteristic) {
            case HEALTH:
                health = Math.max(health - substract, 0);
                break;
            case SATIETY:
                satiety = Math.max(satiety - substract, 0);
                break;
            case EDUCATION_LEVEL:
                educationLevel = Math.max(educationLevel - substract, 0);
                break;
        }
    }

    public void addMoney(int add) throws IllegalArgumentException {
        if (add <= 0) {
            throw new IllegalArgumentException("Addend can't be less or equal than zero.");
        }
        money += add;
    }

    public void substractMoney(int substract) throws IllegalArgumentException {
        if (substract <= 0) {
            throw new IllegalArgumentException("Subtrahend can't be less or equal than zero.");
        }
        money -= substract;
    }

    public int getParameter(GameLogic.ECharacteristics characteristic){
        switch (characteristic) {
            case HEALTH:
                return health;
            case SATIETY:
                return satiety;
            case EDUCATION_LEVEL:
                return educationLevel;
            case MONEY:
                return money;
            case DOLLARS:
                return dollars;
        }
        return 0;
    }

    // методы для получения значений
}