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

    private Button neighbourButton;
    private Button doshikButton;
    private Button stolovayaButton;
    private Button cookButton;
    private Button fastfoodButton;
    private Button sushiButton;
    private Button burgersButton;

    private ArrayList<Boolean> isButtonActivated = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_activity, null);

        for (int i = 0; i < BUTTONS_COUNT; ++i) {
            isButtonActivated.add(false);
        }

        neighbourButton = view.findViewById(R.id.neighbourButton);
        doshikButton = view.findViewById(R.id.doshikButton);
        stolovayaButton = view.findViewById(R.id.stolovayaButton);
        cookButton = view.findViewById(R.id.cookButton);
        fastfoodButton = view.findViewById(R.id.fastfoodButton);
        sushiButton = view.findViewById(R.id.sushiButton);
        burgersButton = view.findViewById(R.id.burgersButton);

        colorAndDisactivateButtons();

        addButtonsListeners();

        return view;
    }

    private void colorAndDisactivateButtons() {
        for (int i = 0; i < BUTTONS_COUNT; ++i) {
            isButtonActivated.set(i, false);
        }

        neighbourButton.setBackgroundColor(Color.WHITE);
        doshikButton.setBackgroundColor(Color.WHITE);
        stolovayaButton.setBackgroundColor(Color.WHITE);
        cookButton.setBackgroundColor(Color.WHITE);
        fastfoodButton.setBackgroundColor(Color.WHITE);
        sushiButton.setBackgroundColor(Color.WHITE);
        burgersButton.setBackgroundColor(Color.WHITE);
    }

    private void addButtonsListeners() {
        neighbourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                neighbourButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(0, true);
            }
        });

        doshikButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                doshikButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(1, true);
            }
        });

        stolovayaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                stolovayaButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(2, true);
            }
        });

        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                cookButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(3, true);
            }
        });

        fastfoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                fastfoodButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(4, true);
            }
        });

        sushiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                sushiButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(5, true);
            }
        });

        burgersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorAndDisactivateButtons();
                burgersButton.setBackgroundColor(Color.GREEN);
                isButtonActivated.set(6, true);
            }
        });
    }
}
