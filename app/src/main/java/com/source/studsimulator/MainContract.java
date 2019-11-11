package com.source.studsimulator;


public interface MainContract {
    public interface View {
        void refreshTextInformation();
        void refreshGradientInformation();
    }

    public interface Presenter {
        void clickOnEatButton();
        void clickOnLearnButton();
    }

    public interface Model {
        void eat(Food food);
        void pay(Price price);
        // как и с едой, добавить класс для методов обучения
        // и передавать его далее
        void learn();
        // аналогично тут
        void work();
    }
}