package com.source.studsimulator;

import java.lang.IllegalArgumentException;


public class Student {
    private int money = 50;
    private int dollars = 0;
    private int health = 50;
    private int hunger = 50;
    private int educationLevel = 50;

    public Student() {

    }

    // мб добавить енам для каждой характеристики, чтобы методы не плодить
    public int addCharacteristic(int add) throws IllegalArgumentException {
        if (add <= 0) {
            throw new IllegalArgumentException("Addend can't be less than zero.");
        }
        money += add;
        return money;
    }

    public int substractCharacteristic(int substract) throws IllegalArgumentException {
        if (substract <= 0) {
            throw new IllegalArgumentException("Subtrahend can't be less than zero.");
        }
        money -= substract;
        return money;
    }
}