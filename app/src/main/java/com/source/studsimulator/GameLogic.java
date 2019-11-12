package com.source.studsimulator;


public class GameLogic implements MainContract.Model {
    private Student student;
    private double dollarsCost = 2;

    enum ECharacteristics {
        EDUCATION_LEVEL, HEALTH, SATIETY, MONEY, DOLLARS
    }

    GameLogic() {
        student = new Student();
    }

    // тут и далее показаны примерный план работы каждого метода
    // по красоте далее нужно будет сделать енамы в controller(а может даже во вью)
    // и передавать их сюда
//
//    public void makeAction(Controller.EAction action) {
//        switch(action){
//            case WORK:
////                student.substractCharacteristic(5, ECharacteristics.HEALTH);
////                student.addMoney(15);
//                break;
//            case SLEEP:
////        student.addCharacteristic(50, ECharacteristics.HEALTH);
////        student.substractCharacteristic(20, ECharacteristics.SATIETY);
//                break;
//            case LEARN:
////        student.addCharacteristic(15, ECharacteristics.EDUCATION_LEVEL);
////        student.substractCharacteristic(5, ECharacteristics.HEALTH);
////        student.substractCharacteristic(5, ECharacteristics.SATIETY);
//                break;
//        }
//    }

    @Override
    public void work() {
//        student.substractCharacteristic(5, ECharacteristics.HEALTH);
//        student.addMoney(15);
    }

    // обсудим, нужно ли нам это
    public void sleep() {
//        student.addCharacteristic(50, ECharacteristics.HEALTH);
//        student.substractCharacteristic(20, ECharacteristics.SATIETY);
    }

    @Override
    public void eat(Food food) {
        student.addCharacteristic(food.getSatiety(), ECharacteristics.SATIETY);
        student.addCharacteristic(10, ECharacteristics.HEALTH);
        pay(food);
    }

    @Override
    public void pay(Price price) {
        student.substractMoney(price.getPrice());
    }

    @Override
    public void learn() {
//        student.addCharacteristic(15, ECharacteristics.EDUCATION_LEVEL);
//        student.substractCharacteristic(5, ECharacteristics.HEALTH);
//        student.substractCharacteristic(5, ECharacteristics.SATIETY);
    }

    @Override
    public int getParameter(GameLogic.ECharacteristics characteristic){
        return student.getParameter(characteristic);
    }
}
