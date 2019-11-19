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

    private ArrayList<Button> lonelyHobbyButtons = new ArrayList<>();
    private ArrayList<Boolean> isLonelyButtonActivated = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        // !!!
        isLonelyButtonActivated.clear();
        lonelyHobbyButtons.clear();

        lonelyHobbyButtons.add(view.findViewById(R.id.readButton));
        lonelyHobbyButtons.add(view.findViewById(R.id.danceButton));
        lonelyHobbyButtons.add(view.findViewById(R.id.beerButton));
        lonelyHobbyButtons.add(view.findViewById(R.id.filmButton));
        lonelyHobbyButtons.add(view.findViewById(R.id.voteButton));

        for (int i = 0; i < buttons.size(); ++i) {
            isLonelyButtonActivated.add(false);
        }

        addButtonListeners();

        return view;
    }

    private void addButtonListeners() {
        for (int i = 0; i < buttons.size(); ++i) {
            int finalI = i;
            lonelyHobbyButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isLonelyButtonActivated.get(finalI)) {
                        lonelyHobbyButtons.get(finalI).setBackgroundColor(0x7700ff00);
                        isLonelyButtonActivated.set(finalI, true);
                    } else {
                        lonelyHobbyButtons.get(finalI).setBackgroundColor(Color.WHITE);
                        isLonelyButtonActivated.set(finalI, false);
                    }
                }
            });
        }
    }
}
