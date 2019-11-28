package com.source.studsimulator.ui.entity;

public class PlayerStats {
    private int educationLevel;
    private int health;
    private int satiety;
    private String money;
    private String programmingSkill;
    private String englishSkill;

    public PlayerStats(int educationLevel, int health, int satiety,
                      String money, String programmingSkill, String englishSkill) {
        this.educationLevel = educationLevel;
        this.health = health;
        this.satiety = satiety;
        this.money = money;
        this.programmingSkill = programmingSkill;
        this.englishSkill = englishSkill;
    }

    public int getEducationLevel() {
        return educationLevel;
    }

    public int getHealth() {
        return health;
    }

    public int getSatiety() {
        return satiety;
    }

    public String getMoney() {
        return money;
    }

    public String getProgrammingSkill() {
        return programmingSkill;
    }

    public String getEnglishSkill() {
        return englishSkill;
    }
}