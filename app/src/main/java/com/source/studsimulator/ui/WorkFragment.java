package com.source.studsimulator.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.source.studsimulator.R;

import java.util.ArrayList;
import java.util.Collection;

public class WorkFragment extends Fragment {


    //    public enum WORKBUTTONS {
//        FLUERS, LOADER, SECURITY, MUSICIAN, FREELANC, MCDONALDS, ITRANSITION,
//        YANDEX, FACEBOOK
//    }

    public enum BUTTON_STATE {
        ACTIVE, ACCECIBLE, INACCESIBLE
    }

    private ArrayList<Button> workbuttons = new ArrayList<>();
    private ArrayList<Button> works = new ArrayList<>();
    private ArrayList<BUTTON_STATE> isButtonActivated = new ArrayList<>();
    private static final String BUTTON_COLOR = "BUTTON_COLOR_KEY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);
        works.clear();
        workbuttons.clear();
        Log.println(Log.INFO, "INFO", "-------->>>>>>>>" + String.valueOf(workbuttons.size()));

        workbuttons.add(view.findViewById(R.id.flyers));
        workbuttons.add(view.findViewById(R.id.loader));
        workbuttons.add(view.findViewById(R.id.security));
        workbuttons.add(view.findViewById(R.id.musician));
        workbuttons.add(view.findViewById(R.id.freelancer));
        workbuttons.add(view.findViewById(R.id.mcdonalds));
        workbuttons.add(view.findViewById(R.id.itransition));
        workbuttons.add(view.findViewById(R.id.yandex));
        workbuttons.add(view.findViewById(R.id.facebook));

        works.add(view.findViewById(R.id.itransition));
        works.add(view.findViewById(R.id.yandex));
        works.add(view.findViewById(R.id.mcdonalds));
        if (isButtonActivated.size() == 0) {
            System.out.println("ACCESIBLE");
            for (int i = 0; i < workbuttons.size(); ++i) {
                isButtonActivated.add(BUTTON_STATE.ACCECIBLE);
            }
        }

        onActivityCreated(null);
        addButtonsListeners();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < isButtonActivated.size(); ++i) {
            switch (isButtonActivated.get(i)) {
                case ACCECIBLE:
                    workbuttons.get(i).setBackgroundColor(Color.WHITE);
                    break;
                case INACCESIBLE:
                    workbuttons.get(i).setBackgroundColor(Color.LTGRAY);
                    break;
                case ACTIVE:
                    workbuttons.get(i).setBackgroundColor(Color.GRAY);
                    break;
            }
        }
    }

    private void activatedOtherWorks(int index) {
        if (works.contains(workbuttons.get(index))) {
            for (android.widget.Button button : works) {
                if (button != workbuttons.get(index)) {
                    button.setClickable(false);
                    button.setBackgroundColor(Color.LTGRAY);
                    isButtonActivated.set(workbuttons.indexOf(button), BUTTON_STATE.INACCESIBLE);
                }
            }
        }
    }

    private void diactivatedOtherWorks(int index) {
        if (works.contains(workbuttons.get(index))) {
            for (int j = 0; j < works.size(); ++j) {
                if (works.get(j) != workbuttons.get(index)) {
                    works.get(j).setClickable(true);
                    works.get(j).setBackgroundColor(Color.WHITE);
                }

            }
        }
    }

    private void addButtonsListeners() {
        for (int i = 0; i < workbuttons.size(); ++i) {
            int finalI = i;
            workbuttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isButtonActivated.get(finalI) == BUTTON_STATE.ACCECIBLE) {
                        workbuttons.get(finalI).setBackgroundColor(Color.GRAY);
                        activatedOtherWorks(finalI);
                        isButtonActivated.set(finalI, BUTTON_STATE.ACTIVE);
                    } else {
                        isButtonActivated.set(finalI, BUTTON_STATE.ACCECIBLE);
                        workbuttons.get(finalI).setBackgroundColor(Color.WHITE);
                        diactivatedOtherWorks(finalI);
                    }
                }
            });
        }
    }


}
