package com.source.studsimulator.ui.fragments;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
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
import com.source.studsimulator.relation.GamePresenter;
import com.source.studsimulator.ui.StudSimulatorApplication;
import com.source.studsimulator.ui.activity.SoundActivity;


public class InfoFragment extends Fragment {

    private Button newWeekButton;
    private ImageView photo;
    private TextView playerCourse;
    private TextView programmingSkill;
    private TextView englishSkill;
    private TextView studyStage;
    private TextView randomActionsMessages;

    private InfoState state = new InfoState();

    private OnInformationFragmentListener activityListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment_activity, null);

        initializeWidgets(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        playerCourse.setText(state.playerCourse);
        programmingSkill.setText(state.programmingSkill);
        englishSkill.setText(state.englishSkill);
        studyStage.setText(state.studyStage);
        randomActionsMessages.setText(state.randomActionsMessages);
    }

    @Override
    public void onPause() {
        super.onPause();
        state.playerCourse = String.valueOf(playerCourse.getText());
        state.englishSkill = String.valueOf(englishSkill.getText());
        state.programmingSkill = String.valueOf(programmingSkill.getText());
        state.studyStage = String.valueOf(studyStage.getText());
        state.randomActionsMessages = String.valueOf(randomActionsMessages.getText());
    }

    private void initializeWidgets(View view) {
        photo = view.findViewById(R.id.artyomTheStudent);
        photo.setOnClickListener(this::showToast);

        playerCourse = view.findViewById(R.id.playerCourse);
        programmingSkill = view.findViewById(R.id.programmingLevel);
        englishSkill = view.findViewById(R.id.englishLevel);
        studyStage = view.findViewById(R.id.studyStage);

        newWeekButton = view.findViewById(R.id.newWeekButton);
        newWeekButton.setOnClickListener(v -> activityListener.onNewWeekClicked());

        randomActionsMessages = view.findViewById(R.id.randomActionsMessages);
    }

    private void showToast(View view) {
        SoundActivity.hearSound(this.getContext(), R.raw.toast);
        Toast clickToast = Toast.makeText(view.getContext(),
                R.string.art, Toast.LENGTH_SHORT);
        clickToast.setGravity(Gravity.NO_GRAVITY, 0, 0);

        LinearLayout toastContainer = (LinearLayout) clickToast.getView();
        toastContainer.setBackgroundColor(Color.TRANSPARENT);

        ImageView artImage = new ImageView(view.getContext());
        artImage.setImageResource(R.drawable.hello_artyom);
        toastContainer.addView(artImage, 0);

        clickToast.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInformationFragmentListener) {
            activityListener = (OnInformationFragmentListener) context;
        }
    }

    public void cleanView(){
        randomActionsMessages.setText("");
    }

    public void writeMessage(String message) {
        if (!randomActionsMessages.getText().equals("")) {
            randomActionsMessages.append("\n");
        }
        randomActionsMessages.append(message);
    }

    public InfoState getState() {
        return state;
    }

    public void updateWeekInformation(GamePresenter.PlayerInformation newInformation) {
        playerCourse.setText(newInformation.getCourse());
        programmingSkill.setText(newInformation.getProgrammingSkill());
        englishSkill.setText(newInformation.getEnglishSkill());
        studyStage.setText(newInformation.getStudyStage());
    }

    public interface OnInformationFragmentListener {
        void onNewWeekClicked();
    }

    public void infoStateLoad(InfoState state) {
        this.state = state;
    }

    public static class InfoState {
        public String playerCourse;
        public String programmingSkill;
        public String englishSkill;
        public String studyStage;
        public String randomActionsMessages;

        public InfoState() {
            playerCourse = "1";
            programmingSkill = StudSimulatorApplication.getContext().getString(
                    R.string.begginer_prog);
            englishSkill = StudSimulatorApplication.getContext().getString(R.string.a1);
            studyStage = StudSimulatorApplication.getContext().getString(R.string.semestr);
            randomActionsMessages = "";
        }
    }
}
