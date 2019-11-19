package com.source.studsimulator.ui;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.source.studsimulator.R;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    public enum FOOD_BUTTONS {
        NEGHBOUR, DOSHIK, STOLOVAYA, COOK, FASTFOOD, SUSHI, BURGERS;
    }

    private ArrayList<Button> buttons = new ArrayList<>();
    private int numberOfActivatedButton = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_activity, null);

        // надо тут разобраться с жизненным циклом
        // и нормально методы поперегружать
        // а пока такая заглушка

        buttons.clear();

        buttons.add(view.findViewById(R.id.neighbourButton));
        buttons.add(view.findViewById(R.id.doshikButton));
        buttons.add(view.findViewById(R.id.stolovayaButton));
        buttons.add(view.findViewById(R.id.cookButton));
        buttons.add(view.findViewById(R.id.fastfoodButton));
        buttons.add(view.findViewById(R.id.sushiButton));
        buttons.add(view.findViewById(R.id.burgersButton));

        addButtonsListeners();
        colorAndDisactivateButtons();

        return view;
    }

    private void colorAndDisactivateButtons() {
        if (numberOfActivatedButton != -1) {
            buttons.get(numberOfActivatedButton).setBackgroundColor(Color.GREEN);
        }
        for (int i = 0; i < buttons.size(); ++i) {
            if (i != numberOfActivatedButton) {
                buttons.get(i).setBackgroundColor(Color.WHITE);
            }
        }
    }

    private void activateButton(int ind) {
        numberOfActivatedButton = ind;
        colorAndDisactivateButtons();
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

    private void addButtonsListeners() {
        for (int i = 0; i < buttons.size(); ++i) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (numberOfActivatedButton == finalI) {
                        numberOfActivatedButton = -1;
                        colorAndDisactivateButtons();
                    } else {
                        activateButton(finalI);
                    }
                }
            });
        }
    }
}
