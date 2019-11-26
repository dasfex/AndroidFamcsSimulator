package com.source.studsimulator.relation;

import com.source.studsimulator.model.GameLogic.PlayerStats;
import com.source.studsimulator.model.entity.Food;
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

    private int getParameter(PlayerStats characteristic) {
        return model.getParameter(characteristic);
    }

    private void updatePlayerStats() {
        PlayerStatsObject playerStats = new
                PlayerStatsObject(
                model.getParameter(PlayerStats.EDUCATION_LEVEL),
                model.getParameter(PlayerStats.HEALTH),
                model.getParameter(PlayerStats.SATIETY),
                String.valueOf(model.getParameter(PlayerStats.MONEY)));
        view.refreshPlayerStats(playerStats);
    }

    public class PlayerStatsObject {
        private int educationLevel;
        private int health;
        private int satiety;
        private String money;

        public PlayerStatsObject(int educationLevel, int health, int satiety, String money) {
            this.educationLevel = educationLevel;
            this.health = health;
            this.satiety = satiety;
            this.money = money;
        }

        public int getEducationLevel() {
            return educationLevel;
        }

        public int getHealth() {
            return health;
        }

        public int getSatiety() {
            return satiety;
        }

        public String getMoney() {
            return money;
        }
    }
}