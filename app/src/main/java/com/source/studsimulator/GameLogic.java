package com.source.studsimulator;


public class GameLogic implements MainContract.Model {
    private Student student = new Student();
    private double dollarsCost = 2;

    enum ECharacteristics {
        HEALTH, HUNGER, EDUCATION_LEVEL;
    };

    GameLogic() {

    }

    public void job() {
//        student.substractCharacteristic(5, ECharacteristics.HEALTH);
//        student.addMoney(15);
    }

    public void sleep() {
//        student.addCharacteristic(50, ECharacteristics.HEALTH);
//        student.substractCharacteristic(20, ECharacteristics.HUNGER);
    }

    public void eating(Food food) {
//        student.addCharacteristic(food.getSatiety(), ECharacteristics.HUNGER);
//        student.addCharacteristic(10, ECharacteristics.HEALTH);
    }

    public void pay(Price price) {
        student.substractMoney(price.getPrice());
    }

    public void learning() {
//        student.addCharacteristic(15, ECharacteristics.EDUCATION_LEVEL);
//        student.substractCharacteristic(5, ECharacteristics.HEALTH);
//        student.substractCharacteristic(5, ECharacteristics.HUNGER);
    }

}
