package com.source.studsimulator.model.entity;

public class Price {

    private int price;

    public Price(int price) {
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public int toInt() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
