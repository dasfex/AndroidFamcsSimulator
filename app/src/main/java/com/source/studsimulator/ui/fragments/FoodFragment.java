package com.source.studsimulator.ui.fragments;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.source.studsimulator.R;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    public enum FOOD_BUTTONS {
        NEIGHBOUR, DOSHIK, STOLOVAYA, COOK, FASTFOOD, SUSHI, BURGERS;
    }

    private RecyclerView buttons;
    private List<StudentActivity> food;
    private int activeButtonIndex = -1;

    private FoodFragment.OnFoodFragmentListener activityListener;

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
        foodRVAdapter.setIndexOfActivatedButton(activeButtonIndex);
        foodRVAdapter.setAdapterListener(position -> {
            int currentPosition = foodRVAdapter.getIndexOfActivatedButton();
            foodRVAdapter.setIndexOfActivatedButton(position);
            changeButtonActivity(position);
            foodRVAdapter.notifyDataSetChanged();
            activityListener.clickOnFoodButton((Food)food.get(position));
        });
        return view;
    }

    private void changeButtonActivity(int position) {
        activeButtonIndex = activeButtonIndex == position ? -1 : position;
    }
  
    public interface OnFoodFragmentListener {
        void clickOnFoodButton(Food food);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FoodFragment.OnFoodFragmentListener) {
            activityListener = (FoodFragment.OnFoodFragmentListener) context;
        }
    }

    private int getIndexOfButton(FOOD_BUTTONS BUTTON) {
        switch (BUTTON) {
            case NEIGHBOUR:
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
        food.add(new Food(0, getString(R.string.neighbourFood), 4, 10));
        food.add(new Food(3, getString(R.string.doshikFood), 7, -5));
        food.add(new Food(7, getString(R.string.stolovkaFood), 12, 7));
        food.add(new Food(5, getString(R.string.yourselfFood), 8, 10));
        food.add(new Food(10, getString(R.string.fastFood), 20, -10));
        food.add(new Food(13, getString(R.string.sushiFood), 15, 5));
        food.add(new Food(20, getString(R.string.burgerFood), 40, -15));
    }
}
