package com.source.studsimulator.relation;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.GameLogic;

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
    public void clickOnEatButton() {
        model.eat(new Food(10, "Apple", 10, 10));
        //поскольку характеристики добавляются не сразу, updatePlayerStats() здесь и далее быть не должно,
        // но можно добавлять для наглядности во время тестирования
        //updatePlayerStats();
    }

    @Override
    public void clickOnSleepButton() {
        model.sleep();
    }

    @Override
    public void clickOnLearnButton() {
        model.learn();
    }

    @Override
    public void clickOnWorkButton() {
        model.work();
    }

    private int getParameter(GameLogic.PlayerStats characteristic) {
        return model.getParameter(characteristic);
    }

    private void updatePlayerStats() {
        com.source.studsimulator.ui.entity.PlayerStats playerStats = new
                com.source.studsimulator.ui.entity.PlayerStats(
                String.valueOf(getParameter(GameLogic.PlayerStats.EDUCATION_LEVEL)),
                String.valueOf(getParameter(GameLogic.PlayerStats.HEALTH)),
                String.valueOf(getParameter(GameLogic.PlayerStats.SATIETY)),
                String.valueOf(getParameter(GameLogic.PlayerStats.MONEY)));
        view.refreshPlayerStats(playerStats);
    }
}