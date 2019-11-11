package com.source.studsimulator;

public class Food extends Price {

    Food(int price, String title, int satiety) {
        setPrice(price);
        this.title = title;
        this.satiety = satiety;
    }

    public int getSatiety() {
        return satiety;
    }

    public String getTitle() {
        return title;
    }

    private String title;
    private int satiety;
}
