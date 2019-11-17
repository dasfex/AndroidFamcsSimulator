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

public class WorkFragment extends Fragment {


    //    public enum WORKBUTTONS {
//        FLUERS, LOADER, SECURITY, MUSICIAN, FREELANC, MCDONALDS, ITRANSITION,
//        YANDEX, FACEBOOK
//    }

    private ArrayList<Button> workbuttons = new ArrayList<>();
    private ArrayList<Button> works = new ArrayList<>();
    private ArrayList<Boolean> isButtonActivated = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);
        works.clear();
        workbuttons.clear();
        isButtonActivated.clear();
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

        for (int i = 0; i < workbuttons.size(); ++i) {
            isButtonActivated.add(false);
        }

        addButtonsListeners();

        return view;
    }

    private void activatedOtherWorks(int index) {
        if (works.contains(workbuttons.get(index))) {
            for (int j = 0; j < works.size(); ++j) {
                if (works.get(j) != workbuttons.get(index)) {
                    works.get(j).setClickable(false);
                    works.get(j).setBackgroundColor(Color.LTGRAY);
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
                    if (!isButtonActivated.get(finalI)) {
                        workbuttons.get(finalI).setBackgroundColor(Color.GRAY);
                        activatedOtherWorks(finalI);
                        isButtonActivated.set(finalI, true);
                    } else {
                        isButtonActivated.set(finalI, false);
                        workbuttons.get(finalI).setBackgroundColor(Color.WHITE);
                        diactivatedOtherWorks(finalI);

                    }
                }
            });
        }
    }


}
