package com.source.studsimulator.model;

import android.drm.DrmStore;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Friend;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.RandomAction;
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

    private static List<RandomAction> randomActionsList = new ArrayList<>();


    public static List<StudentActivity> getFoodList() {
        if (foodList.isEmpty()) {
            foodList.add(new Food(0, StudSimulatorApplication.getContext().getString(R.string.neighbourFood), 4, 10, 1, ActionObjects.getAction(0)));
            foodList.add(new Food(3, StudSimulatorApplication.getContext().getString(R.string.doshikFood), 7, 1, 2));
            foodList.add(new Food(7, StudSimulatorApplication.getContext().getString(R.string.stolovkaFood), 12, 7, 1));
            foodList.add(new Food(5, StudSimulatorApplication.getContext().getString(R.string.yourselfFood), 8, 10, 3));
            foodList.add(new Food(10, StudSimulatorApplication.getContext().getString(R.string.fastFood), 20, 3, 1, ActionObjects.getAction(4)));
            foodList.add(new Food(13, StudSimulatorApplication.getContext().getString(R.string.sushiFood), 15, 5, 1, ActionObjects.getAction(2)));
            foodList.add(new Food(20, StudSimulatorApplication.getContext().getString(R.string.burgerFood), 40, -5, 1));
        }
        return foodList;
    }

    public static List<StudentActivity> getUniversityList() {
        if (universityList.isEmpty()) {
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.noVisit), 0, 1, 0, -1, 0, 0, 0, 0, 0));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.visit), 0, 5, -4, -8, 1, 0, 0, 0, 1));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cheat), 0, 3, -1, -5, 2, 0, 0, 0, 2, ActionObjects.getAction(1)));
            universityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.workHard), 0, 10, -6, -15, 4, 0, 5, 0, 3));
        }
        return universityList;
    }

    public static List<StudentActivity> getExtraActivityList() {
        if (extraActivityList.isEmpty()) {
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.english), 5, 3, -2, -5, 0, 0, 10, 0, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.itransition), 0, 6, -4, -8, 4, 20, 2, 0, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.epam), 0, 10, -6, -10, 8, 50, 6, 50, 1));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.shad), 0, 15, -12, -13, 14, 300, 0, 0, 5));
            extraActivityList.add(new Study(StudSimulatorApplication.getContext().getString(R.string.cookingCourses), 5, 2, 4, 0, 0, 0, 0, 0, 1));
        }
        return extraActivityList;
    }

    public static List<StudentActivity> getHobbyList() {
        if (hobbyList.isEmpty()) {
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.read), 0, 7, 0, 1, -1));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.dance), 5, 15, -2, 3, 20));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.film), 10, 20, -1, 1, 40));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.beer), 5, 12, -1, 2, 60));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.vote), 0, 15, 5, 4, 80));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.eurovision), 0, 15, -5, 3, 10));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.help), 0, 6, 1, 3, 25));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.sport), 0, 20, 0, 10, 95));
            hobbyList.add(new Hobby(StudSimulatorApplication.getContext().getString(R.string.walk), 0, 25, 2, 7, 99));
        }
        return hobbyList;
    }

    public static List<Friend> getFriendList() {
        if (friendList.isEmpty()) {
            friendList.add(new Friend(100, -1, 0, StudSimulatorApplication.getContext().getString(R.string.alone), R.drawable.alone, false));
            friendList.add(new Friend(50, 20, 3, StudSimulatorApplication.getContext().getString(R.string.vitya), R.drawable.vitya, ActionObjects.getAction(9)));
            friendList.add(new Friend(50, 5, 5, StudSimulatorApplication.getContext().getString(R.string.zhenya), R.drawable.zhenya, ActionObjects.getAction(9)));
            friendList.add(new Friend(50, 90, 20, StudSimulatorApplication.getContext().getString(R.string.spilbergsasha), R.drawable.sasha, ActionObjects.getAction(9)));
            friendList.add(new Friend(50, 70, 15, StudSimulatorApplication.getContext().getString(R.string.carla), R.drawable.karla, ActionObjects.getAction(9)));
            friendList.add(new Friend(50, 10, 10, StudSimulatorApplication.getContext().getString(R.string.dragonFly), R.drawable.nick, ActionObjects.getAction(9)));
            friendList.add(new Friend(50, 15, 11, StudSimulatorApplication.getContext().getString(R.string.star), R.drawable.star, ActionObjects.getAction(9)));
        }
        return friendList;
    }

    public static List<StudentActivity> getSideJobList() {
        if (sideJobList.isEmpty()) {
            sideJobList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.flaery), -5, -5, 5, 0, 0, 0, 1, 2));
            sideJobList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.vagony), -15, -15, 10, 0, 0, 0, 0, 3, ActionObjects.getAction(8)));
            sideJobList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.klub), -5, -10, 10, 0, 3, 0, 1, 1, ActionObjects.getAction(7)));
            sideJobList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.perehody), -4, -8, 3, 0, 0, 0, 1, 1));
            sideJobList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.freelance), -2, -5, 25, 10, 10, 3, 1, 2));
        }
        return sideJobList;
    }

    public static List<StudentActivity> getWorkList() {
        if (workList.isEmpty()) {
            workList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.Mak), -10, -10, 50, 0, 0, 10, 6, 2));
            workList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.Jtransition), -6, -7, 40, 15, 2, 5, 4, 3));
            workList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.tyndex), -15, -20, 100, 50, 3, 20, 1, 3));
        }
        return workList;
    }

    public static List<StudentActivity> getSummerWorkList() {
        if (summerWorkList.isEmpty()) {
            summerWorkList.add(new Work(StudSimulatorApplication.getContext().getString(R.string.handbook), -20, -20, 150, 30, 50, 20, 20, 4));
        }
        return summerWorkList;
    }

    public static RandomAction getAction(int index) {
        if (randomActionsList.isEmpty()) {
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.neighbour_action_message),
                    4, -3, 0, 0, 0)
            ); // 0
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.spisyvanie_action_message),
                    4, 0, -40, 0, 0)
            ); // 1
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.sushi_bad_message),
                    4, -15, 0, 0, 0)
            ); // 2
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.parents_money_message),
                    0, 0, 0, 0, 10)
            ); // 3
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.friend_fastfood_message),
                    4, 0, 0, 0, 10)
            ); // 4
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.classmate_bd_message),
                    4, 0, 0, 0, -10)
            ); // 5
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.player_bd_message),
                    100, 30, 30, 30, 100)
            ); // 6
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.draka_message),
                    4, -10, 0, 0, 0)
            ); // 7
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.loader_message),
                    4, -10, 0, 0, 0)
            ); // 8
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.friend_busy_message),
                    100, 0, 0, 0, 0)
            ); // 9
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.grant),
                    100, 0, 0, 0, 50)
            ); // 10
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.zero_grant),
                    100, -2, 0, 0, 0)
            ); // 11
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.angry_em),
                    30, -30, 0, 0, 0)
            ); // 12
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.walk_with_girls),
                    30, 30, 0, 0, -50)
            ); // 13
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.sad),
                    30, -40, 0, 0, 0)
            ); // 14
            randomActionsList.add(new RandomAction(
                    StudSimulatorApplication.getContext().getString(R.string.bigger_grant),
                    100, 0, 0, 0, 60)
            ); // 15
        }
        return randomActionsList.get(index);
    }

}
