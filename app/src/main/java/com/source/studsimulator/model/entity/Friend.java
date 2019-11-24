package com.source.studsimulator.model.entity;

import android.widget.ImageView;
import android.widget.TextView;

public class Friend {

    private int friendshipLevel = 0;
    private int weeksWithoutContact = 0;
    private double busyProbability = 0.2;
    private String name;
    private int photoId;

    public Friend(int friendshipLevel, int weeksWithoutContact, double busyProbability,
                  String name, int photoId) {
        this.friendshipLevel = friendshipLevel;
        this.weeksWithoutContact = weeksWithoutContact;
        this.busyProbability = busyProbability;
        this.name = name;
        this.photoId = photoId;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public int getWeeksWithoutContact() {
        return weeksWithoutContact;
    }

    public double getBusyProbability() {
        return busyProbability;
    }

    public String getName() {
        return name;
    }

    public int getPhotoId() {
        return photoId;
    }

    @Override
    public String toString() {
        return name;
    }
}
