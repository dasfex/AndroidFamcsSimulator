package com.source.studsimulator.model.entity;

public class Study implements Payable, StudentActivity, ContainsRandomAction {

    private String title;
    private Price price;
    private int educationChanging;
    private int satietyChanging;
    private int healthChanging;
    private int programmingSkillIncrease;
    private int programmingSkillRequired;
    private int englishSkillIncrease;
    private int englishSkillRequired;
    private int energyNeeded;
    private RandomAction randomAction;

    public Study(String title, int price,
                 int educationChanging, int satietyChanging, int healthChanging,
                 int programmingSkillIncrease, int programmingSkillRequired,
                 int englishSkillIncrease, int englishSkillRequired,
                 int energy) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (programmingSkillIncrease < 0 || englishSkillIncrease < 0) {
            throw new IllegalArgumentException("Skill can't decrease.");
        }
        if (programmingSkillRequired < 0 || englishSkillRequired < 0) {
            throw new IllegalArgumentException("Study can't require negative skill.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.title = title;
        this.price = new Price(price);
        this.educationChanging = educationChanging;
        this.satietyChanging = satietyChanging;
        this.healthChanging = healthChanging;
        this.programmingSkillIncrease = programmingSkillIncrease;
        this.programmingSkillRequired = programmingSkillRequired;
        this.englishSkillIncrease = englishSkillIncrease;
        this.englishSkillRequired = englishSkillRequired;
        this.energyNeeded = energy;
    }
    public Study(String title, int price,
                 int educationChanging, int satietyChanging, int healthChanging,
                 int programmingSkillIncrease, int programmingSkillRequired,
                 int englishSkillIncrease, int englishSkillRequired,
                 int energy, RandomAction randomAction) {
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (programmingSkillIncrease < 0 || englishSkillIncrease < 0) {
            throw new IllegalArgumentException("Skill can't decrease.");
        }
        if (programmingSkillRequired < 0 || englishSkillRequired < 0) {
            throw new IllegalArgumentException("Study can't require negative skill.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.title = title;
        this.price = new Price(price);
        this.educationChanging = educationChanging;
        this.satietyChanging = satietyChanging;
        this.healthChanging = healthChanging;
        this.programmingSkillIncrease = programmingSkillIncrease;
        this.programmingSkillRequired = programmingSkillRequired;
        this.englishSkillIncrease = englishSkillIncrease;
        this.englishSkillRequired = englishSkillRequired;
        this.energyNeeded = energy;
        this.randomAction = randomAction;
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

    public int getEducationChanging() {
        return educationChanging;
    }

    public int getProgrammingSkillIncrease() {
        return programmingSkillIncrease;
    }

    public int getProgrammingSkillRequired() {
        return programmingSkillRequired;
    }

    public int getEnglishSkillIncrease() {
        return englishSkillIncrease;
    }

    public int getEnglishSkillRequired() {
        return englishSkillRequired;
    }

    @Override
    public Price getPrice() {
        return price;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setEnable(int characteristicForBlock) {}

    @Override
    public boolean isEnable() {
        return true;
    }

    @Override
    public RandomAction getRandomAction() {
        return randomAction;
    }
}
