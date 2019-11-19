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

public class WorkFragment extends Fragment {


    //    public enum WORK_BUTTONS {
//        FLUERS, LOADER, SECURITY, MUSICIAN, FREELANC, MCDONALDS, ITRANSITION,
//        YANDEX, FACEBOOK
//    }

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Button> works = new ArrayList<>();
    private ArrayList<Boolean> isButtonActivated = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);

        works.clear();
        buttons.clear();
        isButtonActivated.clear();

        buttons.add(view.findViewById(R.id.flyers));
        buttons.add(view.findViewById(R.id.loader));
        buttons.add(view.findViewById(R.id.security));
        buttons.add(view.findViewById(R.id.musician));
        buttons.add(view.findViewById(R.id.freelancer));
        buttons.add(view.findViewById(R.id.mcdonalds));
        buttons.add(view.findViewById(R.id.itransition));
        buttons.add(view.findViewById(R.id.yandex));
        buttons.add(view.findViewById(R.id.facebook));

        works.add(view.findViewById(R.id.itransition));
        works.add(view.findViewById(R.id.yandex));
        works.add(view.findViewById(R.id.mcdonalds));

        for (int i = 0; i < buttons.size(); ++i) {
            isButtonActivated.add(false);
        }

        addButtonsListeners();

        return view;
    }

    private void activateOtherWorks(int index) {
        if (works.contains(buttons.get(index))) {
            for (int j = 0; j < works.size(); ++j) {
                if (works.get(j) != buttons.get(index)) {
                    works.get(j).setClickable(false);
                    works.get(j).setBackgroundColor(Color.LTGRAY);
                }

            }
        }
    }

    private void disactivatedOtherWorks(int index) {
        if (works.contains(buttons.get(index))) {
            for (int j = 0; j < works.size(); ++j) {
                if (works.get(j) != buttons.get(index)) {
                    works.get(j).setClickable(true);
                    works.get(j).setBackgroundColor(Color.WHITE);
                }

            }
        }
    }

    private void addButtonsListeners() {
        for (int i = 0; i < buttons.size(); ++i) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isButtonActivated.get(finalI)) {
                        buttons.get(finalI).setBackgroundColor(Color.GRAY);
                        activateOtherWorks(finalI);
                        isButtonActivated.set(finalI, true);
                    } else {
                        isButtonActivated.set(finalI, false);
                        buttons.get(finalI).setBackgroundColor(Color.WHITE);
                        disactivatedOtherWorks(finalI);
                    }
                }
            });
        }
    }
}
