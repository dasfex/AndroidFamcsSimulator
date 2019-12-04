package com.source.studsimulator.model;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;
import com.source.studsimulator.ui.StudSimulatorApplication;
import java.util.ArrayList;
import java.util.List;

public class ActionObjects {

    private static List<StudentActivity> foodList = new ArrayList<>();
    private static List<StudentActivity> universityList = new ArrayList<>();
    private static List<StudentActivity> extraActivityList = new ArrayList<>();
    private static List<StudentActivity> hobbyList = new ArrayList<>();
    private static List<StudentActivity> sideJobList = new ArrayList<>();
    private static List<StudentActivity> workList = new ArrayList<>();
    private static List<StudentActivity> summerWorkList = new ArrayList<>();

    private static List<Friend> friendList = new ArrayList<>();


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
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.noVisit), 0, 0, 0, 5, 0, 0, 0, 0, 0));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.visit), 0, 15, -4, -15, 1, 1, 0, 0, 1));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cheat), 0, 2, -1, -10, 2, 2, 0, 0, 2));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.workHard), 0, 20, -6, -20, 4, 0, 5, 0, 3));
        }
        return universityList;
    }

    public static List<StudentActivity> getExtraActivityList() {
        if (extraActivityList.isEmpty()) {
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.english), 5, 8, -2, -5, 0, 0, 10, 0, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.itransition), 0, 10, -4, -8, 4, 20, 2, 0, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.epam), 0, 12, -6, -10, 8, 50, 6, 50, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.shad), 0, 30, -12, -50, 14, 100, 0, 0, 5));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cookingCourses), 5, 20, 4, 0, 0, 0, 0, 0, 1));
        }
        return extraActivityList;
    }

    public static List<StudentActivity> getHobbyList() {
        if (hobbyList.isEmpty()) {
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.read), 0, 7, 0, 1, 0));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.dance), 0, 15, -2, 3, 20));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.film), 0, 12, -1, 1, 40));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.beer), 0, 10, -1, 2, 60));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.vote), 0, 15, 5, 4, 80));
        }
        return hobbyList;
    }

    public static List<Friend> getFriendList() {
        if (friendList.isEmpty()) {
            // нужно как-то не менять уровень дружбы с собой
            friendList.add(new Friend(100, 0, 0.0, "С собой", R.drawable.hobby));
            friendList.add(new Friend(50, 0, 0.0, "Витя", R.drawable.food));
            friendList.add(new Friend(50, 0, 0.0, "Женя", R.drawable.info));
        }
        return friendList;
    }

    public static List<StudentActivity> getSideJobList() {
        if (sideJobList.isEmpty()) {
            sideJobList.add(new Work("Раздавать флаеры", -5, -5, 5, 0, 0, 0, 1, 2));
            sideJobList.add(new Work("Разгружать вагоны", -15, -15, 10, 0, 0, 0, 0, 3));
            sideJobList.add(new Work("Поохранять клуб", -5, -10, 10, 0, 3, 0, 1, 1));
            sideJobList.add(new Work("Играть в переходах", -4, -8, 3, 0, 0, 0, 1, 1));
            sideJobList.add(new Work("Пофрилансить", -2, -5, 25, 10, 10, 3, 1, 2));
        }
        return sideJobList;
    }

    public static List<StudentActivity> getWorkList() {
        if (workList.isEmpty()) {
            workList.add(new Work("MCDonald's", -10, -10, 50, 20, 30, 10, 6, 2));
            workList.add(new Work("Jtransition", -6, -7, 40, 5, 2, 5, 4, 3));
            workList.add(new Work("Тындекс", -20, -20, 100, 50, 3, 20, 1, 3));
        }
        return workList;
    }

    public static List<StudentActivity> getSummerWorkList() {
        if (summerWorkList.isEmpty()) {
            summerWorkList.add(new Work("Handbook", -20, -20, 150, 30, 50, 20, 20, 4));
        }
        return summerWorkList;
    }


}
