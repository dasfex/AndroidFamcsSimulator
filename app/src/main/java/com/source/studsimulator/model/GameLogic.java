package com.source.studsimulator.model;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.RandomAction;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.relation.GameContract;

public class GameLogic implements GameContract.Model {

    private Student student;
    private int gameTime = 1;
    private int energyLevel = 16;

    public enum PlayerStatsEnum {
        EDUCATION_LEVEL, HEALTH, SATIETY, MONEY,
        PROGRAMMING_SKILL, ENGLISH_SKILL
    }

    public GameLogic() {
        student = new Student();
    }

    @Override
    public int getEnergyLevel() {
        return energyLevel;
    }

    @Override
    public void changeEnergyLevel(int energyLevelPoints) {
        energyLevel += energyLevelPoints;
    }

    @Override
    public void newWeek(int energy) {
        gameTime += 1;
        energyLevel = energy;
    }

    @Override
    public void work(Work work) {
        student.changeHealth(work.getHealthChanging());
        student.changeSatiety(work.getSatietyChanging());
        student.changeMoney(work.getAmountOfMoney());
        student.changeEnglishSkill(work.getEnglishSkillIncrease());
        student.changeProgrammingSkill(work.getProgrammingSkillIncrease());
    }

    @Override
    public void eat(Food food) {
        student.changeSatiety(food.getSatietyChanging());
        student.changeHealth(food.getHealthChanging());
        pay(food);
    }

    @Override
    public void hobby(Hobby hobby) {
        student.changeSatiety(hobby.getSatietyChanging());
        student.changeHealth(hobby.getHealthChanging());
        pay(hobby);
    }

    // важно не потерять минус, т.к. метод ИЗМЕНЯЕТ на заданную величину
    @Override
    public void pay(Payable payable) {
        student.changeMoney(-payable.getPrice().toInt());
    }

    @Override
    public void study(Study study) {
        student.changeHealth(study.getHealthChanging());
        student.changeSatiety(study.getSatietyChanging());
        student.changeEducationLevel(study.getEducationChanging());
        student.changeEnglishSkill(study.getEnglishSkillIncrease());
        student.changeProgrammingSkill(study.getProgrammingSkillIncrease());
        pay(study);
    }

    @Override
    public int getParameter(PlayerStatsEnum characteristic) {
        return student.getParameter(characteristic);
    }

    @Override
    public int getWeek() {
        return gameTime;
    }

    public void applyRandomAction(RandomAction action) {
        student.changeHealth(action.getHealthChanging());
        student.changeSatiety(action.getSatietyChanging());
        student.changeEducationLevel(action.getStudyChanging());
        student.changeMoney(action.getMoneyChanging());
    }

    @Override
    public void normalizeCharacteristics() {
        student.normalizeCharacteristics();
    }
}
