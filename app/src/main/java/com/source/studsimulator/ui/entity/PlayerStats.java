package com.source.studsimulator.ui.entity;

public class PlayerStats {

    private String educationLevel;
    private String health;
    private String satiety;
    private String money;

    public PlayerStats(String educationLevel, String health, String satiety, String money) {
        this.educationLevel = educationLevel;
        this.health = health;
        this.satiety = satiety;
        this.money = money;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getSatiety() {
        return satiety;
    }

    public void setSatiety(String satiety) {
        this.satiety = satiety;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
