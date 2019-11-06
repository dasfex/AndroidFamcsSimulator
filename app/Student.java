package com.source.studsimulator

import java.lang.IllegalArgumentException


public class Student {
    private int health;
    private int hunger;
    private int educationLevel;

    public Student() {

    }

    // мб добавить енам для каждой характеристики, чтобы методы не плодить
    public void addCharacteristic(int add) throws IllegalArgumentException {
        if (add <= 0) {
            throw IllegalArgumentException("Addend can't be less than zero.")
        }
    }

    public void substractCharacteristic(int substract) throws IllegalArgumentException {
        if (substract <= 0) {
            throw IllegalArgumentException("Subtrahend can't be less than zero.")
        }
    }
}