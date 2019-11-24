package com.source.studsimulator.relation;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Payable;

public interface GameContract {

    interface View {
        void refreshPlayerStats(com.source.studsimulator.ui.entity.PlayerStats playerStats);
        void refreshGradientInformation();
        void updateWeek(int weekNumber);
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton();
        void clickOnFoodButton(Food food);
        void clickOnLearnButton();
        void clickOnSleepButton();
        void clickOnWorkButton();
    }

    interface Model {
        void newWeek();
        void eat(Food food);
        void pay(Payable payable);
        // как и с едой, добавить класс для методов обучения
        // и передавать его далее
        void learn();
        void sleep();
        // аналогично тут
        void work();
        int getParameter(GameLogic.PlayerStats characteristic);
        int getWeek();
    }
}