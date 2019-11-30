package com.source.studsimulator.model;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.ui.StupSimulatorApplication;

import java.util.ArrayList;
import java.util.List;

public class ActionObjects {
    private static List<StudentActivity> foodList = new ArrayList<>();


    public static List<StudentActivity> getFoodList() {
        if (foodList.isEmpty()) {
            foodList.add(new Food(0, StupSimulatorApplication.getContext().getString(R.string.neighbourFood), 4, 10, 1));
            foodList.add(new Food(3, StupSimulatorApplication.getContext().getString(R.string.doshikFood), 7, -5, 2));
            foodList.add(new Food(7, StupSimulatorApplication.getContext().getString(R.string.stolovkaFood), 12, 7, 1));
            foodList.add(new Food(5, StupSimulatorApplication.getContext().getString(R.string.yourselfFood), 8, 10, 3));
            foodList.add(new Food(10, StupSimulatorApplication.getContext().getString(R.string.fastFood), 20, -10, 1));
            foodList.add(new Food(13, StupSimulatorApplication.getContext().getString(R.string.sushiFood), 15, 5, 1));
            foodList.add(new Food(20, StupSimulatorApplication.getContext().getString(R.string.burgerFood), 40, -15, 1));
        }
        return foodList;
    }
}
