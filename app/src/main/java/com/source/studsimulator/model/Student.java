package com.source.studsimulator.model;


import com.source.studsimulator.relation.GamePresenter;

public class Student {
    private int money;
    private int health;
    private int satiety;
    private int educationLevel;
    private int programmingSkill;
    private int englishSkill;

    public Student(GamePresenter.GameSettings settings) {
        money = settings.money;
        health = settings.health;
        satiety = settings.satiety;
        educationLevel = settings.educationLevel;
        programmingSkill = settings.programmingSkill;
        englishSkill = settings.englishSkill;
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
        programmingSkill += change;
    }

    public void changeEnglishSkill(int change) {
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