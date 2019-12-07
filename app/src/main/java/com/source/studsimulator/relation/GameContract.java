package com.source.studsimulator.relation;


import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.Payable;
import com.source.studsimulator.model.entity.RandomAction;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.entity.PlayerStats;

public interface GameContract {

    interface View {
        void refreshPlayerStats(PlayerStats playerStats);
        void updateWeek(int weekNumber);
        void updateEnergyLevel(int energyLevel);
        void cleanMessages();
        void writeMessage(String message);
        void printDeadMessage();
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton(int energy);
        void writeMessage(String message);

        void clickOnFoodButton(Food food);
        void unclickOnFoodButton(Food food);

        void clickOnStudyButton(Study study);
        void unclickOnStudyButton(Study study);

        void clickOnWorkButton(Work work);
        void unclickOnWorkButton(Work work);

        void clickOnHobbyButton(Hobby hobby);
        void unclickOnHobbyButton(Hobby hobby);
    }

    interface Model {
        void newWeek(int energy);

        void pay(Payable payable);

        void eat(Food food);
        void study(Study study);
        void work(Work work);
        void hobby(Hobby hobby);
        void applyRandomAction(RandomAction action);
        void normalizeCharacteristics();

        void changeEnergyLevel(int energyLevelPoints);

        int getParameter(GameLogic.PlayerStatsEnum characteristic);
        int getEnergyLevel();
        int getWeek();
    }
}