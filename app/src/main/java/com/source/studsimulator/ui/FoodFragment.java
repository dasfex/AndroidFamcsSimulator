package com.source.studsimulator.ui;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.source.studsimulator.R;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    private final int BUTTONS_COUNT = 7;

    public enum BUTTONS {
        NEGHBOUR, DOSHIK, STOLOVAYA, COOK, FASTFOOD, SUSHI, BURGERS;
    };

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Boolean> isButtonActivated = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_activity, null);

        for (int i = 0; i < BUTTONS_COUNT; ++i) {
            isButtonActivated.add(false);
        }

        buttons.add(view.findViewById(R.id.neighbourButton));
        buttons.add(view.findViewById(R.id.doshikButton));
        buttons.add(view.findViewById(R.id.stolovayaButton));
        buttons.add(view.findViewById(R.id.cookButton));
        buttons.add(view.findViewById(R.id.fastfoodButton));
        buttons.add(view.findViewById(R.id.sushiButton));
        buttons.add(view.findViewById(R.id.burgersButton));

        colorAndDisactivateButtons();

        addButtonsListeners();

        return view;
    }

    private void colorAndDisactivateButtons() {
        for (int i = 0; i < BUTTONS_COUNT; ++i) {
            isButtonActivated.set(i, false);
            buttons.get(i).setBackgroundColor(Color.WHITE);
        }
    }

    private void activateButton(int ind) {
        colorAndDisactivateButtons();
        buttons.get(ind).setBackgroundColor(Color.GREEN);
        isButtonActivated.set(ind, true);
    }

    private int getIndexOfButton(BUTTONS BUTTON) {
        switch (BUTTON) {
            case NEGHBOUR: return 0;
            case DOSHIK: return 1;
            case STOLOVAYA: return 2;
            case COOK: return 3;
            case FASTFOOD: return 4;
            case SUSHI: return 5;
            case BURGERS: return 6;
            default: return -1;
        }
    }

    private void addButtonsListeners() {
        for (int i = 0; i < BUTTONS_COUNT; ++i) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isButtonActivated.get(finalI)) {
                        colorAndDisactivateButtons();
                    } else {
                        activateButton(finalI);
                    }
                }
            });
        }
    }
}
