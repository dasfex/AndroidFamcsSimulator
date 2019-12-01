package com.source.studsimulator.model;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.ui.StudSimulatorApplication;

import java.util.ArrayList;
import java.util.List;

public class ActionObjects {

    private static List<StudentActivity> foodList = new ArrayList<>();
    private static List<StudentActivity> universityList = new ArrayList<>();
    private static List<StudentActivity> extraActivityList = new ArrayList<>();


    public static List<StudentActivity> getFoodList() {
        if (foodList.isEmpty()) {
            foodList.add(new Food(0, StudSimulatorApplication.getContext().getString(R.string.neighbourFood), 4, 10, 1));
            foodList.add(new Food(3, StudSimulatorApplication.getContext().getString(R.string.doshikFood), 7, -5, 2));
            foodList.add(new Food(7, StudSimulatorApplication.getContext().getString(R.string.stolovkaFood), 12, 7, 1));
            foodList.add(new Food(5, StudSimulatorApplication.getContext().getString(R.string.yourselfFood), 8, 10, 3));
            foodList.add(new Food(10, StudSimulatorApplication.getContext().getString(R.string.fastFood), 20, -10, 1));
            foodList.add(new Food(13, StudSimulatorApplication.getContext().getString(R.string.sushiFood), 15, 5, 1));
            foodList.add(new Food(20, StudSimulatorApplication.getContext().getString(R.string.burgerFood), 40, -15, 1));
        }
        return foodList;
    }

    public static List<StudentActivity> getUniversityList() {
        if (universityList.isEmpty()) {
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.noVisit), 0, 0, 0, 5, 0, 0));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.visit), 0, 15, -4, -15, 0,1));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cheat), 0, 2, -1, -10, 0,2));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.workHard), 0, 20, -6, -20, 0,3));
        }
        return universityList;
    }

    public static List<StudentActivity> getExtraActivityList() {
        if (extraActivityList.isEmpty()) {
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.english), 5, 8, -2, -5, 0, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.itransition), 0, 10, -4, -8, 6, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.epam), 0, 12, -6, -10, 8, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.shad), 0, 30, -12, -50, 10, 5));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cookingCourses), 5, 20, 4, 0, 0, 1));
        }
        return extraActivityList;
    }
}
