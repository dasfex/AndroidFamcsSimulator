package com.source.studsimulator;



public class Controller implements MainContract.Presenter {
    private MainContract.Model model;
    private MainContract.View view;

    Controller(MainContract.View newView, MainContract.Model newModel) {
        view = newView;
        model = newModel;
    }
}