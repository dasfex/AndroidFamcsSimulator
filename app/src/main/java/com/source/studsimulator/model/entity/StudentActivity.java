package com.source.studsimulator.model.entity;

public interface StudentActivity {
    String getTitle();
    int getEnergyNeeded();
    void setEnable(int characteristicForBlock);
    boolean isEnable();
}
