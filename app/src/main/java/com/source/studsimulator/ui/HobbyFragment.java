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

    private ArrayList<Button> buttons = new ArrayList<>();
    private ArrayList<Boolean> isButtonActivated = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        // !!!
        isButtonActivated.clear();
        buttons.clear();

        buttons.add(view.findViewById(R.id.readButton));
        buttons.add(view.findViewById(R.id.danceButton));
        buttons.add(view.findViewById(R.id.beerButton));
        buttons.add(view.findViewById(R.id.filmButton));
        buttons.add(view.findViewById(R.id.voteButton));

        for (int i = 0; i < buttons.size(); ++i) {
            isButtonActivated.add(false);
        }

        addButtonListeners();

        return view;
    }

    private void addButtonListeners() {
        for (int i = 0; i < buttons.size(); ++i) {
            int finalI = i;
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isButtonActivated.get(finalI)) {
                        buttons.get(finalI).setBackgroundColor(0x7700ff00);
                        isButtonActivated.set(finalI, true);
                    } else {
                        buttons.get(finalI).setBackgroundColor(Color.WHITE);
                        isButtonActivated.set(finalI, false);
                    }
                }
            });
        }
    }
}
