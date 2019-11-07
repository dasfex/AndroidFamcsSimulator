package com.source.studsimulator;


public interface MainContract {
    public interface View {
        void refreshTextInformation();
        void refreshGradientInformation();
    }

    public interface Presenter {
        int button();
    }

    public interface Model {
        int Inc();
    }
}