package com.source.studsimulator.model;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.relation.GameContract;

public class GameLogic implements GameContract.Model {

    private Student student;
    private double dollarsCost = 2;

    public enum PlayerStats {
        EDUCATION_LEVEL, HEALTH, SATIETY, MONEY, DOLLARS
    }

    public GameLogic() {
        student = new Student();
    }

    // тут и далее показаны примерный план работы каждого метода
    // по красоте далее нужно будет сделать енамы в controller(а может даже во вью)
    // и передавать их сюда

    @Override
    public void work() {
        student.substractCharacteristic(5, PlayerStats.HEALTH);
        student.addMoney(15);
    }

    // обсудим, нужно ли нам это
    @Override
    public void sleep() {
        student.addCharacteristic(50, PlayerStats.HEALTH);
        student.substractCharacteristic(20, PlayerStats.SATIETY);
    }

    @Override
    public void eat(Food food) {
        student.addCharacteristic(food.getSatiety(), PlayerStats.SATIETY);
        student.addCharacteristic(10, PlayerStats.HEALTH);
        pay(food);
    }

    @Override
    public void pay(Payable payable) {
        student.substractMoney(payable.getPrice().toInt());
    }

    @Override
    public void learn() {
        student.addCharacteristic(15, PlayerStats.EDUCATION_LEVEL);
        student.substractCharacteristic(5, PlayerStats.HEALTH);
        student.substractCharacteristic(5, PlayerStats.SATIETY);
    }

    @Override
    public int getParameter(PlayerStats characteristic){
        return student.getParameter(characteristic);
    }
}
