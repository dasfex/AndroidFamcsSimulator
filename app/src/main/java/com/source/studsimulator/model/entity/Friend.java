package com.source.studsimulator.model.entity;

import android.widget.ImageView;
import android.widget.TextView;

public class Friend {

    private int friendshipLevel = 0;
    private int weeksWithoutContact = 0;
    private double busyProbability = 0.2;
    private TextView name;
    private ImageView photo;

    public Friend(int friendshipLevel, int weeksWithoutContact, double busyProbability,
                  TextView name, ImageView photo) {
        this.friendshipLevel = friendshipLevel;
        this.weeksWithoutContact = weeksWithoutContact;
        this.busyProbability = busyProbability;
        this.photo = photo;
        this.name = name;
    }
}
