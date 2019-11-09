package com.source.studsimulator;

public class GameLogic implements MainContract.Model {
    private Student student = new Student();
    private double dollarsCost = 2;

    enum ECharacteristics {
        HEALTH, HUNGER, EDUCATION_LEVEL
    };

    GameLogic() {

    }

}
