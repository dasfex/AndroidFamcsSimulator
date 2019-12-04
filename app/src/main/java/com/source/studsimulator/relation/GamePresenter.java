package com.source.studsimulator.relation;

import com.source.studsimulator.model.GameLogic.PlayerStatsEnum;
import com.source.studsimulator.model.entity.ContainsRandomAction;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.entity.PlayerStats;
import com.source.studsimulator.ui.entity.ViewState;

import java.util.Random;

public class GamePresenter implements GameContract.Presenter {

    private GameContract.Model model;
    private GameContract.View view;
    private ViewState weekLiveChoicesStaff;

    public GamePresenter(GameContract.View newView, GameContract.Model newModel) {
        view = newView;
        model = newModel;
        weekLiveChoicesStaff = new ViewState();
    }

    @Override
    public void viewCreated() {
        updatePlayerStats();
        view.updateWeek(model.getWeek());
        view.updateEnergyLevel(model.getEnergyLevel());
    }

    @Override
    public void clickOnNewWeekButton() {
        view.cleanMessages();
        applyLiveChoices(weekLiveChoicesStaff);
        model.newWeek();
        updatePlayerStats();
        view.updateWeek(model.getWeek());
        view.updateEnergyLevel(model.getEnergyLevel());
    }

    @Override
    public void writeMessage(String message) {
        view.writeMessage(message);
    }

    private void applyLiveChoices(ViewState weekLiveChoicesStaff) {
        for (Food foodItem : weekLiveChoicesStaff.getFoodList()) {
            model.eat(foodItem);
            applyRandomAction(foodItem);
        }

        for (Study studyItem : weekLiveChoicesStaff.getStudyList()) {
            model.study(studyItem);
            applyRandomAction(studyItem);
        }

        for (Work workItem : weekLiveChoicesStaff.getWorkList()) {
            model.work(workItem);
            applyRandomAction(workItem);
        }

        for (Hobby hobbyItem : weekLiveChoicesStaff.getHobbyList()) {
            model.hobby(hobbyItem);
            applyRandomAction(hobbyItem);
        }

        model.normalizeCharacteristics();
    }

    @Override
    public void clickOnFoodButton(Food food) {
        weekLiveChoicesStaff.addFood(food);
    }

    @Override
    public void unclickOnFoodButton(Food food) {
        weekLiveChoicesStaff.removeFood(food);
    }

    @Override
    public void clickOnLearnButton(Study study) {
        weekLiveChoicesStaff.addStudy(study);
    }

    @Override
    public void unclickOnStudyButton(Study study) {
        weekLiveChoicesStaff.removeStudy(study);
    }

    @Override
    public void clickOnWorkButton(Work work) {
        weekLiveChoicesStaff.addWork(work);
    }

    @Override
    public void unclickOnWorkButton(Work work) {
        weekLiveChoicesStaff.removeWork(work);
    }

    @Override
    public void clickOnHobbyButton(Hobby hobby) {
        weekLiveChoicesStaff.addHobby(hobby);
    }

    @Override
    public void unclickOnHobbyButton(Hobby hobby) {
        weekLiveChoicesStaff.removeHobby(hobby);
    }

    private void changeEnergyLevel() {
        view.updateEnergyLevel(model.getEnergyLevel());
    }

    private int getParameter(PlayerStatsEnum characteristic) {
        return model.getParameter(characteristic);
    }

    private void updatePlayerStats() {
        PlayerStats playerStats = new
                PlayerStats(
                model.getParameter(PlayerStatsEnum.EDUCATION_LEVEL),
                model.getParameter(PlayerStatsEnum.HEALTH),
                model.getParameter(PlayerStatsEnum.SATIETY),
                String.valueOf(model.getParameter(PlayerStatsEnum.MONEY)),
                getProgrammingSkillString(model.getParameter(PlayerStatsEnum.PROGRAMMING_SKILL)),
                getEnglishSkillString(model.getParameter(PlayerStatsEnum.ENGLISH_SKILL)));
        view.refreshPlayerStats(playerStats);
    }

    private String getProgrammingSkillString(int param) {
        if (param < 20) {
            return "Begginer";
        } else if (param < 40) {
            return "Lubitel";
        } else if (param < 80) {
            return "Junior";
        } else if (param < 140) {
            return "Middle";
        } else if (param < 220) {
            return "Senior";
        } else if (param < 300) {
            return "Lead";
        } else {
            return "Korotkevich";
        }
    }

    private String getEnglishSkillString(int param) {
        if (param < 20) {
            return "A1";
        } else if (param < 40) {
            return "A2";
        } else if (param < 60) {
            return "B1";
        } else if (param < 80) {
            return "B2";
        } else if (param < 100) {
            return "C1";
        } else if (param < 120) {
            return "C2";
        } else {
            return "D13";
        }
    }

    private void applyRandomAction(ContainsRandomAction item) {
        if (item.getRandomAction() != null) {
            if (item.getRandomAction().isActive()) {
                model.applyRandomAction(item.getRandomAction());
                view.writeMessage(item.getRandomAction().getMessage());
            }
        }
    }

}
