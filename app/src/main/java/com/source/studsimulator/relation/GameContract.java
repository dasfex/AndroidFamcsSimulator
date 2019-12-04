package com.source.studsimulator.relation;


import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.entity.PlayerStats;

public interface GameContract {

    interface View {
        void refreshPlayerStats(PlayerStats playerStats);
        void updateWeek(int weekNumber);
        void updateEnergyLevel(int energyLevel);
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton();

        void clickOnFoodButton(Food food);
        void unclickOnFoodButton(Food food);

        void clickOnLearnButton(Study study);
        void unclickOnStudyButton(Study study);

        void clickOnWorkButton(Work work);
        void unclickOnWorkButton(Work work);

        void clickOnHobbyButton(Hobby hobby);
        void unclickOnHobbyButton(Hobby hobby);
    }

    interface Model {
        void newWeek();

        void pay(Payable payable);
        void getMoney(Payable payable);

        void eat(Food food);
        void study(Study study);
        void work(Work work);
        void hobby(Hobby hobby);

        void changeEnergyLevel(int energyLevelPoints);

        int getParameter(GameLogic.PlayerStatsEnum characteristic);
        int getEnergyLevel();
        int getWeek();
    }
}