package com.source.studsimulator.model.entity;


public class Hobby implements Payable, StudentActivity, ContainsRandomAction {

    private String title;
    private Price price;
    private int satietyChanging;
    private int healthChanging;
    private int energyNeeded;
    private int friendshipLevelRequired;
    private boolean isEnable = true;
    RandomAction randomAction;

    public Hobby(String title, int price,
                 int healthChanging, int satietyChanging, int energy,
                 int friendshipLevelRequired) throws IllegalArgumentException {
        if (healthChanging <= 0) {
            throw new IllegalArgumentException("Hobby can't have nonpositive mood changing.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.price = new Price(price);
        this.title = title;
        this.healthChanging = healthChanging;
        this.satietyChanging = satietyChanging;
        this.energyNeeded = energy;
        this.friendshipLevelRequired = friendshipLevelRequired;
    }
    public Hobby(String title, int price,
                 int healthChanging, int satietyChanging, int energy,
                 int friendshipLevelRequired, RandomAction randomAction) throws IllegalArgumentException {
        if (healthChanging <= 0) {
            throw new IllegalArgumentException("Hobby can't have nonpositive mood changing.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be less than zero.");
        }
        if (title.equals("")) {
            throw new IllegalArgumentException("Title can't be empty string.");
        }
        if (energy < 0) {
            throw new IllegalArgumentException("Energy must be positive.");
        }
        this.price = new Price(price);
        this.title = title;
        this.healthChanging = healthChanging;
        this.satietyChanging = satietyChanging;
        this.energyNeeded = energy;
        this.friendshipLevelRequired = friendshipLevelRequired;
        this.randomAction = randomAction;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }

    @Override
    public int getEnergyNeeded() {
        return energyNeeded;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    public int getSatietyChanging() {
        return satietyChanging;
    }

    public int getFriendshipLevelRequired() {
        return friendshipLevelRequired;
    }

    @Override
    public void setEnable(int characteristicForBlock) {
        isEnable = characteristicForBlock > friendshipLevelRequired;
    }

    @Override
    public boolean isEnable() {
        return isEnable;
    }

    @Override
    public RandomAction getRandomAction() {
        return randomAction;
    }
}
