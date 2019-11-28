package com.source.studsimulator.model.entity;

public class Study implements Payable, StudentActivity {

    private String title;
    private Price price;
    private int educationChanging;
    private int satietyChanging;
    private int healthChanging;
    private int programmingSkillRequied;
    private int energyNeeded;

    public Study(String title, int price, int educationChanging, int satietyChanging, int healthChanging, int programmingSkillRequied, int energy) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (programmingSkillRequied < 0) {
            throw new IllegalArgumentException("Study can't require negative programming skill.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.title = title;
        this.price = new Price(price);
        this.educationChanging = educationChanging;
        this.satietyChanging = satietyChanging;
        this.healthChanging = healthChanging;
        this.programmingSkillRequied = programmingSkillRequied;
        this.energyNeeded = energy;
    }

    @Override
    public int getEnergyNeeded() {
        return energyNeeded;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getProgrammingSkillRequired() {
        return programmingSkillRequied;
    }

    public int getEducationChanging() {
        return educationChanging;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
