package com.source.studsimulator.model.entity;

public class Work implements StudentActivity, ContainsRandomAction {

    private String title;
    private int healthChanging;
    private int satietyChanging;
    private int amountOfMoney;
    private int programmingSkillRequired;
    private int englishSkillRequired;
    private int programmingSkillIncrease;
    private int englishSkillIncrease;
    private int energyNeeded;
    private boolean isEnable = true;

    RandomAction randomAction;

    public enum TypeOfWork {
        SUMMER, SIDE, FULL_TIME
    }

    public Work(String title, int healthChanging,
                int satietyChanging, int amountOfMoney,
                int programmingSkillRequired, int englishSkillRequired,
                int programmingSkillIncrease, int englishSkillIncrease, int energy) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Work can't reduce money.");
        }
        if (programmingSkillRequired < 0) {
            throw new IllegalArgumentException("Work can't require negative programming skill.");
        }
        if (englishSkillRequired < 0) {
            throw new IllegalArgumentException("Work can't require negative English skill.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.title = title;
        this.healthChanging = healthChanging;
        this.satietyChanging = satietyChanging;
        this.amountOfMoney = amountOfMoney;
        this.programmingSkillRequired = programmingSkillRequired;
        this.englishSkillRequired = englishSkillRequired;
        this.programmingSkillIncrease = programmingSkillIncrease;
        this.englishSkillIncrease = englishSkillIncrease;
        this.energyNeeded = energy;
    }

    public Work(String title, int healthChanging,
                int satietyChanging, int amountOfMoney,
                int programmingSkillRequired, int englishSkillRequired,
                int programmingSkillIncrease, int englishSkillIncrease, int energy, RandomAction randomAction) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (amountOfMoney < 0) {
            throw new IllegalArgumentException("Work can't reduce money.");
        }
        if (programmingSkillRequired < 0) {
            throw new IllegalArgumentException("Work can't require negative programming skill.");
        }
        if (englishSkillRequired < 0) {
            throw new IllegalArgumentException("Work can't require negative English skill.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.title = title;
        this.healthChanging = healthChanging;
        this.satietyChanging = satietyChanging;
        this.amountOfMoney = amountOfMoney;
        this.programmingSkillRequired = programmingSkillRequired;
        this.englishSkillRequired = englishSkillRequired;
        this.programmingSkillIncrease = programmingSkillIncrease;
        this.englishSkillIncrease = englishSkillIncrease;
        this.energyNeeded = energy;
        this.randomAction = randomAction;
    }
    @Override
    public int getEnergyNeeded() {
        return energyNeeded;
    }

    public int getProgrammingSkillIncrease() {
        return programmingSkillIncrease;
    }

    public int getEnglishSkillIncrease() {
        return englishSkillIncrease;
    }

    public String getTitle() {
        return title;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public int getProgrammingSkillRequired() {
        return programmingSkillRequired;
    }

    public int getEnglishSkillRequired() {
        return englishSkillRequired;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    @Override
    public void setEnable(int characteristicForBlock) {
        isEnable = isGood(characteristicForBlock);
    }

    @Override
    public boolean isEnable() {
        return isEnable;
    }

    private boolean isGood(int characteristicForBlock) {
        return true;
    }

    @Override
    public RandomAction getRandomAction() {
        return randomAction;
    }
}
