package com.source.studsimulator.relation;


import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Work;

public interface GameContract {

    interface View {
        void refreshPlayerStats(com.source.studsimulator.ui.entity.PlayerStats playerStats);
        void updateWeek(int weekNumber);
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton();
        void clickOnFoodButton(Food food);
        void unclickFoodButton(Food food);
        void clickOnLearnButton();
        void clickOnSleepButton();
        void clickOnWorkButton(Work work);
    }

    interface Model {
        void newWeek();
        void eat(Food food);
        void eatBack(Food food);
        void pay(Payable payable);
        // как и с едой, добавить класс для методов обучения
        // и передавать его далее
        void learn();
        void sleep();
        // аналогично тут
        void work(Work work);
        int getParameter(GameLogic.PlayerStats characteristic);
        int getWeek();
    }
}