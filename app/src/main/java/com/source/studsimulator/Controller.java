package com.source.studsimulator;



public class Controller implements MainContract.Presenter {

    enum EAction{
        LEARN, WORK, SLEEP
    }
    private MainContract.Model model;
    private MainContract.View view;

    Controller(MainContract.View newView, MainContract.Model newModel) {
        view = newView;
        model = newModel;
    }
    @Override
    public void clickOnEatButton() {
        model.eat(new Food(10,"Apple",10));
        view.refreshTextInformation();
    }

    @Override
    public void clickOnSleepButton() {
        model.sleep();
        view.refreshTextInformation();
    }

    @Override
    public void clickOnLearnButton() {
        model.learn();
        view.refreshTextInformation();
    }

    @Override
    public void clickOnWorkButton() {
        model.work();
        view.refreshTextInformation();
    }

    @Override
    public int getParameter(GameLogic.ECharacteristics characteristic){
        return model.getParameter(characteristic);
    }
}