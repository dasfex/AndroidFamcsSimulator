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

public class StudyFragment extends Fragment {

    public enum BUTTON_STATE {
        ACTIVE, ACCECIBLE, INACCESIBLE
    }

    private ArrayList<Button> universityButtons = new ArrayList<>();
    private ArrayList<Button> extraActivity = new ArrayList<>();
    private ArrayList<BUTTON_STATE> isButtonActivated = new ArrayList<>();
    private int indexOfActivatedButton = -1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_fragment_activity, null);
        universityButtons.clear();
        extraActivity.clear();

        universityButtons.add(view.findViewById(R.id.visitButton));
        universityButtons.add(view.findViewById(R.id.noVisitButton));
        universityButtons.add(view.findViewById(R.id.cheatButton));
        universityButtons.add(view.findViewById(R.id.hardUnButton));

        extraActivity.add(view.findViewById(R.id.hellButton));
        extraActivity.add(view.findViewById(R.id.languageButton));
        extraActivity.add(view.findViewById(R.id.itraButton));
        extraActivity.add(view.findViewById(R.id.epamButton));
        extraActivity.add(view.findViewById(R.id.progaButton));
        extraActivity.add(view.findViewById(R.id.cookingButton));

        if (isButtonActivated.size() == 0) {
            for (int i = 0; i < extraActivity.size(); ++i) {
                isButtonActivated.add(StudyFragment.BUTTON_STATE.ACCECIBLE);
            }
        }

        onActivityCreated(null);
        addButtonsListeners();
        colorUniversityButtons();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < isButtonActivated.size(); ++i) {
            switch (isButtonActivated.get(i)) {
                case ACCECIBLE:
                    extraActivity.get(i).setBackgroundColor(Color.WHITE);
                    break;
                case ACTIVE:
                    extraActivity.get(i).setBackgroundColor(Color.RED);
                    break;
            }
        }
    }

    private void addButtonsListeners() {
        for (int i = 0; i < universityButtons.size(); ++i) {
            int finalI = i;
            universityButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indexOfActivatedButton == finalI) {
                        indexOfActivatedButton = -1;
                    } else {
                        indexOfActivatedButton = finalI;
                    }
                    colorUniversityButtons();
                }
            });
        }
        for (int i = 0; i < extraActivity.size(); ++i) {
            int finalI = i;
            extraActivity.get(finalI).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isButtonActivated.get(finalI) == StudyFragment.BUTTON_STATE.ACCECIBLE) {
                        isButtonActivated.set(finalI, StudyFragment.BUTTON_STATE.ACTIVE);
                        extraActivity.get(finalI).setBackgroundColor(Color.RED);
                    } else {
                        extraActivity.get(finalI).setBackgroundColor(Color.WHITE);
                        isButtonActivated.set(finalI, StudyFragment.BUTTON_STATE.ACCECIBLE);
                    }
                }
            });
        }
    }

    private void colorUniversityButtons() {
        if (indexOfActivatedButton != -1) {
            universityButtons.get(indexOfActivatedButton).setBackgroundColor(Color.RED);
        }
        for (int i = 0; i < universityButtons.size(); ++i) {
            if (i != indexOfActivatedButton) {
                universityButtons.get(i).setBackgroundColor(Color.WHITE);
            }
        }
    }

}
