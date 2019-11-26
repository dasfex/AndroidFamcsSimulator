package com.source.studsimulator.model.entity;

public class Study implements Payable, StudentActivity {

    private String title;
    private Price price;
    private int educationChanging;
    private int satietyChanging;
    private int healthChanging;
    private int programmingSkillRequied;

    public Study(String title, int price, int educationChanging, int satietyChanging, int healthChanging, int programmingSkillRequied) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (programmingSkillRequied < 0) {
            throw new IllegalArgumentException("Study can't require negative programming skill.");
        }
        this.title = title;
        this.price = new Price(price);
        this.educationChanging = educationChanging;
        this.satietyChanging = satietyChanging;
        this.healthChanging = healthChanging;
        this.programmingSkillRequied = programmingSkillRequied;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getProgrammingSkillRequied() {
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
