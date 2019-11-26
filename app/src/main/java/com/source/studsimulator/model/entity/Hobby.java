package com.source.studsimulator.model.entity;


public class Hobby implements Payable, StudentActivity {

    private String title;
    private Price price;
    private int satietyChanging;
    private int moodChanging;

    public Hobby(String title, int price, int moodChanging, int satietyChanging) throws IllegalArgumentException {
        if (moodChanging <= 0) {
            throw new IllegalArgumentException("Hobby can't have nonpositive mood changing.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        this.price = new Price(price);
        this.title = title;
        this.moodChanging = moodChanging;
        this.satietyChanging = satietyChanging;
    }

    public int getMoodChanging() {
        return moodChanging;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public int getSatietyChanging() {return satietyChanging; }
}
