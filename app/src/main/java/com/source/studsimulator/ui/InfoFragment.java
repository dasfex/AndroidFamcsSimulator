package com.source.studsimulator.ui;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.source.studsimulator.R;

public class InfoFragment extends Fragment {

    private Button newWeekButton;
   // private boolean isClicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_activity, null);

        newWeekButton = view.findViewById(R.id.newWeekButton);

        newWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // надо тут как-то вызывать newWeek в gameLobbyActivity
            }
        });

        return view;
    }
}
