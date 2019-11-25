package com.source.studsimulator.model;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.relation.GameContract;

public class GameLogic implements GameContract.Model {

    private Student student;
    private int gameTime = 1;

    public enum PlayerStats {
        EDUCATION_LEVEL, HEALTH, SATIETY, MONEY
    }

    public GameLogic() {
        student = new Student();
    }

    @Override
    public void newWeek() {
        // тут чекаем результаты всей недели
        gameTime += 1;
    }

    // тут и далее показаны примерный план работы каждого метода
    // по красоте далее нужно будет сделать енамы в controller(а может даже во вью)
    // и передавать их сюда

    @Override
    public void work(Work work) {
        student.changeHealth(-5);
        student.changeMoney(15);
    }

    // обсудим, нужно ли нам это
    @Override
    public void sleep() {
        student.changeHealth(50);
        student.changeSatiety(-20);
    }

    @Override
    public void eat(Food food) {
        student.changeSatiety(food.getSatietyChanging());
        student.changeHealth(food.getHealthChanging());
        pay(food);
    }

    @Override
    public void eatBack(Food food) {
        student.changeSatiety(-food.getSatietyChanging());
        student.changeHealth(-food.getHealthChanging());
        payBack(food);
    }

    // важно не потерять минус, т.к. метод ИЗМЕНЯЕТ на заданную величину
    @Override
    public void pay(Payable payable) {
        student.changeMoney(-payable.getPrice().toInt());
    }


    private void payBack(Payable payable) {
        student.changeMoney(payable.getPrice().toInt());
    }

    @Override
    public void learn() {
        student.changeEducationLevel(15);
        student.changeHealth(-5);
        student.changeSatiety(-5);
    }

    @Override
    public int getParameter(PlayerStats characteristic){
        return student.getParameter(characteristic);
    }

    @Override
    public int getWeek() {
        return gameTime;
    }
}
