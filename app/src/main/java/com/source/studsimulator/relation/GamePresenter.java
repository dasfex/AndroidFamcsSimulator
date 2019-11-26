package com.source.studsimulator.relation;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Work;

public class GamePresenter implements GameContract.Presenter {

    private GameContract.Model model;
    private GameContract.View view;

    public GamePresenter(GameContract.View newView, GameContract.Model newModel) {
        view = newView;
        model = newModel;
    }

    @Override
    public void viewCreated() {
        updatePlayerStats();
        view.updateWeek(model.getWeek());
    }

    @Override
    public void clickOnNewWeekButton() {
        model.newWeek();
        updatePlayerStats();
        view.updateWeek(model.getWeek());
    }

    @Override
    public void clickOnFoodButton(Food food) {
        model.eat(food);
    }

    @Override
    public void clickOnLearnButton() {
        model.learn();
    }

    @Override
    public void clickOnWorkButton(Work work) {
        model.work(work);
    }

    private int getParameter(GameLogic.PlayerStats characteristic) {
        return model.getParameter(characteristic);
    }

    private void updatePlayerStats() {
        com.source.studsimulator.ui.entity.PlayerStats playerStats = new
                com.source.studsimulator.ui.entity.PlayerStats(
                (getParameter(GameLogic.PlayerStats.EDUCATION_LEVEL)),
                (getParameter(GameLogic.PlayerStats.HEALTH)),
                (getParameter(GameLogic.PlayerStats.SATIETY)),
                String.valueOf(getParameter(GameLogic.PlayerStats.MONEY)));
        view.refreshPlayerStats(playerStats);
    }
}