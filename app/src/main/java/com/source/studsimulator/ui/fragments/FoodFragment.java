package com.source.studsimulator.ui.fragments;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.source.studsimulator.R;
import com.source.studsimulator.model.ActionObjects;
import com.source.studsimulator.model.entity.Food;
import com.source.studsimulator.model.entity.Hobby;
import com.source.studsimulator.model.entity.StudentActivity;
import com.source.studsimulator.model.entity.Study;
import com.source.studsimulator.ui.StudSimulatorApplication;
import com.source.studsimulator.ui.fragments.adapters.OneActiveButtonAdapter;

import java.util.List;

public class FoodFragment extends Fragment {

    private RecyclerView foodRv;
    private List<StudentActivity> food;
    private int activeButtonIndex = -1;

    private FoodFragment.OnFoodFragmentListener activityListener;
    OneActiveButtonAdapter foodRvAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_activity, null);

        initializeFood();

        foodRv = view.findViewById(R.id.buttonsRecyclerView);
        foodRv.setLayoutManager(new LinearLayoutManager(getContext()));
        foodRv.setHasFixedSize(true);

        OneActiveButtonAdapter foodRvAdapter = new OneActiveButtonAdapter(food);
        foodRv.setAdapter(foodRvAdapter);
        foodRvAdapter.setIndexOfActivatedButton(activeButtonIndex);

//        foodRvAdapter.setAdapterListener(position -> {
//            int currentPosition = foodRvAdapter.getIndexOfActivatedButton();
//            if (currentPosition != -1) {
//                activityListener.unclickFoodButton((Food) food.get(currentPosition));
//            }
//            foodRvAdapter.setIndexOfActivatedButton(position);
//            changeButtonActivity(position);
//            foodRvAdapter.notifyDataSetChanged();
//            if (currentPosition != position) {
//                activityListener.clickOnFoodButton((Food) food.get(position));
//            }
//        });
        initializeRv(view);

        return view;
    }


    public void activateButton(int position) {
        foodRvAdapter.setIndexOfActivatedButton(position);
        changeButtonActivity(position);
        foodRvAdapter.notifyDataSetChanged();
    }

    private void changeButtonActivity(int position) {
        activeButtonIndex = activeButtonIndex == position ? -1 : position;
    }

    public interface OnFoodFragmentListener {
        void clickOnFoodButton(int index);

        void unclickFoodButton(Food food);

        int getEnergy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FoodFragment.OnFoodFragmentListener) {
            activityListener = (FoodFragment.OnFoodFragmentListener) context;
        }
    }

    private void initializeFood() {
        food = ActionObjects.getFoodList();
    }

    @SuppressLint("ShowToast")
    private void initializeRv(View view) {
        foodRv = view.findViewById(R.id.buttonsRecyclerView);
        foodRv.setLayoutManager(new LinearLayoutManager(getContext()));
        foodRv.setHasFixedSize(true);

        foodRvAdapter = new OneActiveButtonAdapter(food);
        foodRv.setAdapter(foodRvAdapter);
        foodRvAdapter.setIndexOfActivatedButton(activeButtonIndex);

        foodRvAdapter.setAdapterListener(position -> {
            int currentPosition = foodRvAdapter.getIndexOfActivatedButton();
            if (currentPosition == position) {
                activityListener.unclickFoodButton((Food) food.get(currentPosition));
                foodRvAdapter.setIndexOfActivatedButton(position);
                changeButtonActivity(position);
                foodRvAdapter.notifyDataSetChanged();
            } else {
                activityListener.clickOnFoodButton(position);
            }
        });
    }
}
