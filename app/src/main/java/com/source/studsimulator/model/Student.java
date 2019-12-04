package com.source.studsimulator.model;


public class Student {

    private int money = 50;
    private int health = 50;
    private int satiety = 50;
    private int educationLevel = 0;
    private int programmingSkill = 0;
    private int englishSkill = 0;

    public Student() {

    }

    public void changeHealth(int change) {
        health += change;
    }

    public void changeSatiety(int change) {
        satiety += change;
    }

    public void changeEducationLevel(int change) { educationLevel += change; }

    public void changeMoney(int change) { money += change; }

    public void changeProgrammingSkill(int change) {
        if (change < 0) {
            throw new IllegalArgumentException("Programming skill can't decrease.");
        }
        programmingSkill += change;
    }

    public void changeEnglishSkill(int change) {
        if (change < 0) {
            throw new IllegalArgumentException("English skill can't decrease.");
        }
        englishSkill += change;
    }

    public void normalizeCharacteristics() {
        health = Math.min(health, 100);
        satiety = Math.min(satiety, 100);
        educationLevel = Math.min(educationLevel, 100);
        health = Math.max(health, 0);
        satiety = Math.max(satiety, 0);
        educationLevel = Math.max(educationLevel, 0);
    }

    public int getParameter(GameLogic.PlayerStatsEnum characteristic) {
        switch (characteristic) {
            case HEALTH:
                return health;
            case SATIETY:
                return satiety;
            case EDUCATION_LEVEL:
                return educationLevel;
            case MONEY:
                return money;
            case PROGRAMMING_SKILL:
                return programmingSkill;
            case ENGLISH_SKILL:
                return englishSkill;
        }
        return 0;
    }

}