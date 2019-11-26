package com.source.studsimulator.relation;


import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Work;

public interface GameContract {

    interface View {
        void refreshPlayerStats(GamePresenter.PlayerStatsObject playerStats);
        void updateWeek(int weekNumber);
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton();
        void clickOnFoodButton(Food food);
        void clickOnLearnButton();
        void clickOnWorkButton(Work work);
    }

    interface Model {
        void newWeek();
        void eat(Food food);
        void pay(Payable payable);
        //
        void learn();
        void work(Work work);
        int getParameter(GameLogic.PlayerStats characteristic);
        int getWeek();
    }
}