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


    public enum WORK_BUTTONS {
        FLYERS, LOADER, SECURITY, MUSICIAN, FREELANCER,
        MCDONALDS, ITRANSITION, YANDEX,
        FACEBOOK
    }

    public enum BUTTON_STATE {
        ACTIVE, ACCECIBLE, INACCESIBLE
    }

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Button> works = new ArrayList<>();
    private ArrayList<BUTTON_STATE> isButtonActivated = new ArrayList<>();
    private static final String BUTTON_COLOR = "BUTTON_COLOR_KEY"; //  на самом деле тут hex число

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.work_fragment_activity, null);

        works.clear();
        buttons.clear();

        buttons.add(view.findViewById(R.id.flyersButton));
        buttons.add(view.findViewById(R.id.loaderButton));
        buttons.add(view.findViewById(R.id.securityButton));
        buttons.add(view.findViewById(R.id.musicianButton));
        buttons.add(view.findViewById(R.id.freelancerButton));
        buttons.add(view.findViewById(R.id.mcdonaldsButton));
        buttons.add(view.findViewById(R.id.itransitionButton));
        buttons.add(view.findViewById(R.id.yandexButton));
        buttons.add(view.findViewById(R.id.facebookButton));

        works.add(view.findViewById(R.id.itransitionButton));
        works.add(view.findViewById(R.id.yandexButton));
        works.add(view.findViewById(R.id.mcdonaldsButton));

        if (isButtonActivated.size() == 0) {
            for (int i = 0; i < buttons.size(); ++i) {
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
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    break;
                case INACCESIBLE:
                    buttons.get(i).setBackgroundColor(Color.LTGRAY);
                    break;
                case ACTIVE:
                    buttons.get(i).setBackgroundColor(Color.GRAY);
                    break;
            }
        }
    }

    private void activateOtherWorks(int index) {
        if (works.contains(buttons.get(index))) {
            for (android.widget.Button button : works) {
                if (button != buttons.get(index)) {
                    button.setClickable(false);
                    button.setBackgroundColor(Color.LTGRAY);
                    isButtonActivated.set(buttons.indexOf(button), BUTTON_STATE.INACCESIBLE);
                }
            }
        }
    }

    private void disactivateOtherWorks(int index) {
        if (works.contains(buttons.get(index))) {
            for (android.widget.Button button : works) {
                if (button != buttons.get(index)) {
                    button.setClickable(true);
                    button.setBackgroundColor(Color.WHITE);
                    isButtonActivated.set(buttons.indexOf(button), BUTTON_STATE.ACCECIBLE);
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
                    if (isButtonActivated.get(finalI) == BUTTON_STATE.ACCECIBLE) {
                        buttons.get(finalI).setBackgroundColor(Color.GRAY);
                        activateOtherWorks(finalI);
                        isButtonActivated.set(finalI, BUTTON_STATE.ACTIVE);
                    } else {
                        isButtonActivated.set(finalI, BUTTON_STATE.ACCECIBLE);
                        buttons.get(finalI).setBackgroundColor(Color.WHITE);
                        disactivateOtherWorks(finalI);
                    }
                }
            });
        }
    }
}
