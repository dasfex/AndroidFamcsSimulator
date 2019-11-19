package com.source.studsimulator.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.source.studsimulator.R;

import java.util.ArrayList;

public class HobbyFragment extends Fragment {

    private ArrayList<Button> lonelyHobbyButtons = new ArrayList<>();
    private ArrayList<Boolean> isLonelyButtonActivated = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hobby_fragment_activity, null);

        lonelyHobbyButtons.add(view.findViewById(R.id.read));
        lonelyHobbyButtons.add(view.findViewById(R.id.dance));
        lonelyHobbyButtons.add(view.findViewById(R.id.beer));
        lonelyHobbyButtons.add(view.findViewById(R.id.film));
        lonelyHobbyButtons.add(view.findViewById(R.id.vote));

        return view;
    }
}
