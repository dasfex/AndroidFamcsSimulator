package com.source.studsimulator.model.entity;

public class Food implements Payable {

    private Price price;

    public Food(int price, String title, int satiety) {
        this.price = new Price(price);
        this.title = title;
        this.satiety = satiety;
    }

    public int getSatiety() {
        return satiety;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    private String title;
    private int satiety;
}
