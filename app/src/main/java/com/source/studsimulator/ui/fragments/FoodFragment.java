package com.source.studsimulator.ui.fragments;

import com.source.studsimulator.model.entity.Food;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Payable;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    public enum FOOD_BUTTONS {
        NEGHBOUR, DOSHIK, STOLOVAYA, COOK, FASTFOOD, SUSHI, BURGERS;
    }

    private RecyclerView buttons;
    private List<Payable> food;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_activity, null);

        buttons = view.findViewById(R.id.buttonsRecyclerView);

        buttons.setLayoutManager(new LinearLayoutManager(getContext()));

        buttons.setHasFixedSize(true);

        initializeFood();

        OneActiveButtonAdapter foodRVAdapter = new OneActiveButtonAdapter(food);
        buttons.setAdapter(foodRVAdapter);
        foodRVAdapter.setAdapterListener(position -> {
            foodRVAdapter.setIndexOfActivatedButton(position);
            foodRVAdapter.notifyDataSetChanged();
        });

        return view;
    }

    private int getIndexOfButton(FOOD_BUTTONS BUTTON) {
        switch (BUTTON) {
            case NEGHBOUR:
                return 0;
            case DOSHIK:
                return 1;
            case STOLOVAYA:
                return 2;
            case COOK:
                return 3;
            case FASTFOOD:
                return 4;
            case SUSHI:
                return 5;
            case BURGERS:
                return 6;
            default:
                return -1;
        }
    }

    private void initializeFood() {
        food = new ArrayList<>();
        food.add(new Food(0, "Брать у соседа", 4, 10));
        food.add(new Food(3, "Кушать дошик", 7, -5));
        food.add(new Food(7, "Ходить в столовую", 12, 7));
        food.add(new Food(5, "Готовить самому", 8, 10));
        food.add(new Food(10, "Кушать фастфуд", 20, -10));
        food.add(new Food(13, "Кушать суши", 15, 5));
        food.add(new Food(20, "Покупать бургеры за 2000", 40, -15));
    }
}
