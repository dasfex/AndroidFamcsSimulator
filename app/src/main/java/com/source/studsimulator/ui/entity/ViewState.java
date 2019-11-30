package com.source.studsimulator.ui.entity;

import com.source.studsimulator.model.entity.Food;

import java.util.ArrayList;
import java.util.List;

public class ViewState {
    private List<Food> foodList;

    public ViewState() {
        foodList = new ArrayList<>();
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
}
