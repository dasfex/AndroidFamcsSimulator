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
        void updateWeekInformation(GamePresenter.PlayerInformation newInformation);

        void cleanRandomActionsMessages();
        void writeRandomActionMessage(String message);

        void printDeadMessage();
        void activateWorkButton(int number, Work.TypeOfWork type);
        void activateStudyButton(int number, Study.TYPE_OF_STUDY type);
        void activateFoodButton(int number);
        void activateHobhyButton(int number);
        void notAvailableMessage(String message);

        void unClick(Work workItem, Work.TypeOfWork type);
    }

    interface Presenter {
        void viewCreated();
        void clickOnNewWeekButton(int energy);

        void clickOnFoodButton(int position);
        void unclickOnFoodButton(Food food);

        void clickOnStudyButton(int position, Study.TYPE_OF_STUDY type);
        void unclickOnStudyButton(Study study);

        void clickOnWorkButton(int number, Work.TypeOfWork type);
        void deactivateWorkButton(Work work);

        void clickOnHobbyButton(int position);
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
        String getStudyStage();
    }
}