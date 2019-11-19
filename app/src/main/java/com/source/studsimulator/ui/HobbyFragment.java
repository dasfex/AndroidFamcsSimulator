package com.source.studsimulator.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.source.studsimulator.R;

import java.util.ArrayList;

public class HobbyFragment extends Fragment {

    public enum HOBBY_BUTTONS {
        READ, DANCE, BEER, FILM, VOTE
    }

    public enum BUTTON_STATE {
        ACTIVE, ACCECIBLE
    }

    private ArrayList<Button> lonelyButtons = new ArrayList<>();
    private ArrayList<BUTTON_STATE> isLonelyButtonActivated = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        lonelyButtons.clear();

        lonelyButtons.add(view.findViewById(R.id.readButton));
        lonelyButtons.add(view.findViewById(R.id.danceButton));
        lonelyButtons.add(view.findViewById(R.id.beerButton));
        lonelyButtons.add(view.findViewById(R.id.filmButton));
        lonelyButtons.add(view.findViewById(R.id.voteButton));

        if (isLonelyButtonActivated.size() == 0) {
            for (int i = 0; i < lonelyButtons.size(); ++i) {
                isLonelyButtonActivated.add(BUTTON_STATE.ACCECIBLE);
            }
        }

        onActivityCreated(null);
        addButtonListeners();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < isLonelyButtonActivated.size(); ++i) {
            switch (isLonelyButtonActivated.get(i)) {
                case ACCECIBLE:
                    lonelyButtons.get(i).setBackgroundColor(Color.WHITE);
                    break;
                case ACTIVE:
                    lonelyButtons.get(i).setBackgroundColor(Color.GREEN);
                    break;
            }
        }
    }

    private void addButtonListeners() {
        for (int i = 0; i < lonelyButtons.size(); ++i) {
            int finalI = i;
            lonelyButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLonelyButtonActivated.get(finalI) == BUTTON_STATE.ACCECIBLE) {
                        lonelyButtons.get(finalI).setBackgroundColor(Color.GREEN);
                        isLonelyButtonActivated.set(finalI, BUTTON_STATE.ACTIVE);
                    } else {
                        lonelyButtons.get(finalI).setBackgroundColor(Color.WHITE);
                        isLonelyButtonActivated.set(finalI, BUTTON_STATE.ACCECIBLE);
                    }
                }
            });
        }
    }
}
