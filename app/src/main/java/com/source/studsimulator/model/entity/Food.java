package com.source.studsimulator.model.entity;

public class Food implements Payable {

    private Price price;
    private String title;
    private int satietyChanging;
    private int healthChanging;

    public Food(int price, String title, int satiety, int health) throws IllegalArgumentException {
        if (satiety <= 0) {
            //return new IllegalArgumentException("Food can't have negative satiety.");
        }
        this.price = new Price(price);
        this.title = title;
        this.satietyChanging = satiety;
        this.healthChanging = health;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public int getHealthChanging() {return healthChanging; }
}
