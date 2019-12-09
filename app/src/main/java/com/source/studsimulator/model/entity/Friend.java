package com.source.studsimulator.model.entity;

import java.util.Random;

public class Friend implements ContainsRandomAction {

    private int friendshipLevel;
    private int busyProbability;
    private int healthChanging;
    private boolean isFriendshipLevelCanChange;
    private boolean lastBusiest;
    private boolean isMeet;
    private String name;
    private int photoId;
    private RandomAction randomAction;
    private Random random = new Random();

    public Friend(int friendshipLevel, int busyProbability, int healthChanging,
                  String name, int photoId, boolean isFriendshipLevelCanChange) {
        this.friendshipLevel = friendshipLevel;
        this.busyProbability = busyProbability;
        this.healthChanging = healthChanging;
        this.name = name;
        this.photoId = photoId;
        this.isFriendshipLevelCanChange = isFriendshipLevelCanChange;
    }

    public Friend(int friendshipLevel, int busyProbability, int healthChanging,
                  String name, int photoId, RandomAction randomAction) {
        this.friendshipLevel = friendshipLevel;
        this.busyProbability = busyProbability;
        this.healthChanging = healthChanging;
        this.name = name;
        this.photoId = photoId;
        this.isFriendshipLevelCanChange = true;
        this.randomAction = randomAction;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public boolean isBusy() {
        isMeet = true;
        int x = random.nextInt(100);
        lastBusiest = x <= busyProbability;
        return lastBusiest;
    }

    public String getName() {
        return name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getHealthChanging() {
        return healthChanging;
    }

    @Override
    public RandomAction getRandomAction() {
        return randomAction;
    }

    public boolean isMeet() {
        boolean result = isMeet;
        isMeet = false;
        return result;
    }

    public void changeCharacteristics() {
        if (isFriendshipLevelCanChange) {
            if (lastBusiest) {
                friendshipLevel += 5;
            } else {
                friendshipLevel += 1;
            }
        }
    }

    public void decreaseCharacteristics() {
        if (isFriendshipLevelCanChange) {
            friendshipLevel -= 4;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
