package com.source.studsimulator.model.entity;

import java.util.Random;

public class RandomAction implements Payable {
    private String message;
    //вероятность от 1 до 20
    private int probability;
    private Price price;
    private int healthChanging;
    private  int studyChanging;
    private Random rand = new Random();

    public RandomAction( String message, int probability, int price, int healthChanging, int studyChanging) {
        this.message = message;
        this.probability = probability;
        this.price = new Price(price);
        this.healthChanging = healthChanging;
        this.studyChanging = studyChanging;
    }

    public boolean isActive() {
        int x = rand.nextInt(20);
        return (x <= probability);
    }

    public String getMessage() {
        return message;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getStudyChanging() {
        return studyChanging;
    }

}
