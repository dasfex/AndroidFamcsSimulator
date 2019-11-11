package com.source.studsimulator;

import java.lang.IllegalArgumentException;


public class Student {
    private int money = 50;
    private int dollars = 0;
    private int health = 50;
    private int hunger = 50;
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
            case HUNGER:
                hunger += add;
                break;
            case EDUCATION_LEVEL:
                educationLevel += add;
                break;
        }
    }

    public void substractCharacteristic(int substract, GameLogic.ECharacteristics characteristic) throws IllegalArgumentException {
        if (substract <= 0) {
            throw new IllegalArgumentException("Subtrahend can't be less or equal than zero.");
        }
        switch (characteristic) {
            case HEALTH:
                health = Math.min(health - substract, 0);
                break;
            case HUNGER:
                hunger -= substract;
                break;
            case EDUCATION_LEVEL:
                educationLevel -= substract;
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

    // методы для получения значений
}