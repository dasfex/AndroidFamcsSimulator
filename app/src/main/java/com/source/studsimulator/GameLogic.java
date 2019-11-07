package com.source.studsimulator;

public class GameLogic implements MainContract.Model {
    private Student student = new Student();
    private double dollarsCost = 2;

    GameLogic() {

    }

    @Override
    public int Inc() {
        return student.addCharacteristic(1);
    }
}
