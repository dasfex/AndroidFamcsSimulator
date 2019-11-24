package com.source.studsimulator.ui.entity;

public class PlayerStats {

    private int educationLevel;
    private int health;
    private int satiety;
    private String money;

    public PlayerStats(int educationLevel, int health, int satiety, String money) {
        this.educationLevel = educationLevel;
        this.health = health;
        this.satiety = satiety;
        this.money = money;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(int educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
