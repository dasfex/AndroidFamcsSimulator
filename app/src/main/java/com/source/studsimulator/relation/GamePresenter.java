package com.source.studsimulator.relation;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.GameLogic;

public class GamePresenter implements GameContract.Presenter {

    enum PlayerAction {
        LEARN, WORK, SLEEP
    }

    private GameContract.Model model;
    private GameContract.View view;

    public GamePresenter(GameContract.View newView, GameContract.Model newModel) {
        view = newView;
        model = newModel;
    }

    @Override
    public void viewCreated() {
        updatePlayerStats();
    }

    @Override
    public void clickOnEatButton() {
        model.eat(new Food(10, "Apple", 10));
        updatePlayerStats();
    }

    @Override
    public void clickOnSleepButton() {
        model.sleep();
        updatePlayerStats();
    }

    @Override
    public void clickOnLearnButton() {
        model.learn();
        updatePlayerStats();
    }

    @Override
    public void clickOnWorkButton() {
        model.work();
        updatePlayerStats();
    }

    @Override
    public int getParameter(GameLogic.PlayerStats characteristic) {
        return model.getParameter(characteristic);
    }

    private void updatePlayerStats() {
        com.source.studsimulator.ui.entity.PlayerStats playerStats = new com.source.studsimulator.ui.entity.PlayerStats(
                String.valueOf(getParameter(GameLogic.PlayerStats.EDUCATION_LEVEL)),
                String.valueOf(getParameter(GameLogic.PlayerStats.HEALTH)),
                String.valueOf(getParameter(GameLogic.PlayerStats.SATIETY)),
                String.valueOf(getParameter(GameLogic.PlayerStats.MONEY)),
                String.valueOf(getParameter(GameLogic.PlayerStats.DOLLARS)));
        view.refreshPlayerStats(playerStats);
    }
}