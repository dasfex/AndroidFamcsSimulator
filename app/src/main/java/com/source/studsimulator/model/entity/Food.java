package com.source.studsimulator.model.entity;


import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Price;

public class Food implements Payable, StudentActivity {

    private String title;
    private Price price;
    private int satietyChanging;
    private int healthChanging;

    public Food(int price, String title, int satiety, int health) throws IllegalArgumentException {
        if (satiety <= 0) {
            throw new IllegalArgumentException("Food can't have nonpositive satiety.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
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
