package com.source.studsimulator.model.entity;

public class Work {

    private String title;
    private int healthChanging;
    private int amountOfMoney;
    private int programmingSkillRequied;
    private int englishSkillRequied;

    public int getProgrammingSkillIncrease() {
        return programmingSkillIncrease;
    }

    public int getEnglishSkillIncrease() {
        return englishSkillIncrease;
    }

    private int programmingSkillIncrease;
    private int englishSkillIncrease;

    public String getTitle() {
        return title;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public int getProgrammingSkillRequied() {
        return programmingSkillRequied;
    }

    public int getEnglishSkillRequied() {
        return englishSkillRequied;
    }

    public Work(String title, int healthChanging, int amountOfMoney,
                int programmingSkillRequied, int englishSkillRequied,
                int programmingSkillIncrease, int englishSkillIncrease) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Work can't reduce money.");
        }
        if (programmingSkillRequied < 0) {
            throw new IllegalArgumentException("Work can't require negative programming skill.");
        }
        if (englishSkillRequied < 0) {
            throw new IllegalArgumentException("Work can't require negative English skill.");
        }
        this.title = title;
        this.healthChanging = healthChanging;
        this.amountOfMoney = amountOfMoney;
        this.programmingSkillRequied = programmingSkillRequied;
        this.englishSkillRequied = englishSkillRequied;
        this.programmingSkillIncrease = programmingSkillIncrease;
        this.englishSkillIncrease = englishSkillIncrease;
    }
}
