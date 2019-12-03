package com.source.studsimulator.model.entity;


public class Hobby implements Payable, StudentActivity {

    private String title;
    private Price price;
    private int satietyChanging;
    private int healthChanging;
    private int energyNeeded;

    public Hobby(String title, int price,
                 int healthChanging, int satietyChanging, int energy) throws IllegalArgumentException {
        if (healthChanging <= 0) {
            throw new IllegalArgumentException("Hobby can't have nonpositive mood changing.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.price = new Price(price);
        this.title = title;
        this.healthChanging = healthChanging;
        this.satietyChanging = satietyChanging;
        this.energyNeeded = energy;
    }

    @Override
    public int getEnergyNeeded() {
        return energyNeeded;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    public int getSatietyChanging() {return satietyChanging; }
}
