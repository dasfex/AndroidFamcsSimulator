package com.source.studsimulator.model;


import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.RandomAction;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.relation.GameContract;
import com.source.studsimulator.ui.StudSimulatorApplication;

import java.util.Random;

public class GameLogic implements GameContract.Model {

    private Student student;
    private int gameTime = 1;
    private int energyLevel = 16;
    private String studyStage = StudSimulatorApplication.getContext().getString(R.string.semestr);
    private Random random = new Random();
    private boolean isKicked = false;
    private int generalEducationChange = 0;

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
        updateStudyStage();
        checkForEndGame();
    }

    @Override
    public String getStudyStage() {
        return studyStage;
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
    public void hobby(Hobby hobby, Friend friend) {
        student.changeSatiety(hobby.getSatietyChanging());
        student.changeHealth(hobby.getHealthChanging());
        if (friend != null) {
            student.changeHealth(friend.getHealthChanging());
        }
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
        increaseEducationChanging(study.getEducationChanging());
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

    public boolean isKicked() {
        return isKicked;
    }

    public void applyRandomAction(RandomAction action) {
        student.changeHealth(action.getHealthChanging());
        student.changeSatiety(action.getSatietyChanging());
        student.changeEducationLevel(action.getEducationChanging());
        student.changeMoney(action.getMoneyChanging());
        increaseEducationChanging(action.getEducationChanging());
    }

    private void increaseEducationChanging(int change) {
        if (change < 0) {
            throw new RuntimeException("CHANGE IS NEGATIVE");
        }
        generalEducationChange += change;
    }

    @Override
    public void normalizeCharacteristics() {
        student.normalizeCharacteristics();
    }

    @Override
    public void weekCharacteristicDecrease() {
        student.changeHealth(-random.nextInt(5));
        student.changeSatiety(-random.nextInt(5));
        student.changeEducationLevel(-random.nextInt(5));
    }

    private void updateStudyStage() {
        int week = gameTime;
        week %= 52;
        if (week <= 16 || (week > 22 && week <= 36)) {
            studyStage = StudSimulatorApplication.getContext().getString(R.string.semestr);
        } else if ((week > 16 && week <= 20) || (week > 36 && week <= 40)) {
            studyStage = StudSimulatorApplication.getContext().getString(R.string.session);
        } else {
            studyStage = StudSimulatorApplication.getContext().getString(R.string.holidays);
        }
    }

    private void checkForEndGame() {
        if (studyStage.equals(StudSimulatorApplication.getContext().getString(R.string.session)) &&
            student.getParameter(PlayerStatsEnum.EDUCATION_LEVEL) < 70) {
            isKicked = true;
        }
        if (gameTime % 52 == 16 || gameTime % 52 == 40) {
            if (generalEducationChange < 100) {
                isKicked = true;
            }
            generalEducationChange = 0;
        }
    }
}
