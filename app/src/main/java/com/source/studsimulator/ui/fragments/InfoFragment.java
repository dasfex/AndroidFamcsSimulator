package com.source.studsimulator.ui.fragments;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.source.studsimulator.R;

public class InfoFragment extends Fragment {

    private Button newWeekButton;
    private ImageView photo;

    private OnInformationFragmentListener activityListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_activity, null);
        photo = view.findViewById(R.id.artyomTheStudent);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showToast(v);
            }
        });
        newWeekButton = view.findViewById(R.id.newWeekButton);

        newWeekButton.setOnClickListener(v -> activityListener.onNewWeekClicked());

        return view;
    }

    public void showToast(View view) {
        Toast toast3 = Toast.makeText(view.getContext(),
                R.string.art, Toast.LENGTH_SHORT);
        toast3.setGravity(Gravity.NO_GRAVITY, 0, 0);

        LinearLayout toastContainer = (LinearLayout) toast3.getView();
            toastContainer.setBackgroundColor(Color.TRANSPARENT);
        ImageView artImage = new ImageView(view.getContext());
        artImage.setImageResource(R.drawable.hello_artyom);
        artImage.setMaxWidth(300);
        artImage.setMaxHeight(300);
            toastContainer.addView(artImage, 0);

        toast3.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInformationFragmentListener) {
            activityListener = (OnInformationFragmentListener) context;
        }
    }

    public interface OnInformationFragmentListener {
        void onNewWeekClicked();
    }
}
