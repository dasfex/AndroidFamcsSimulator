package com.source.studsimulator.ui.entity;

import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.model.entity.Work;

import java.util.ArrayList;
import java.util.List;

public class ViewState {
    private List<Food> foodList;
    private List<Study> studyList;
    private List<Work> workList;


    public ViewState() {
        foodList = new ArrayList<>();
        studyList = new ArrayList<>();
        workList = new ArrayList<>();
    }

    public void addFood(Food foodItem) {
        foodList.add(foodItem);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void removeFood(Food food) {
        foodList.remove(food);
    }

    public void addStudy(Study studyItem) {
        studyList.add(studyItem);
    }

    public List<Study> getStudyList() {
        return studyList;
    }

    public void removeStudy(Study study) {
        studyList.remove(study);
    }

    public void addWork(Work workItem) {
        workList.add(workItem);
    }

    public List<Work> getWorkList() {
        return workList;
    }

    public void removeWork(Work work) {
        workList.remove(work);
    }


}
