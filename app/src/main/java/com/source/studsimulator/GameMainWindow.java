package com.source.studsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameMainWindow extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter controller = new Controller(this, new GameLogic());

    private Button mEatButton;
    private Button mSleepButton;
    private Button mStudyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_window);
        ImageButton info = findViewById(R.id.infoButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запускаем фрагмент с информацией
            }
        });
        mEatButton = findViewById(R.id.eatButton);
        mEatButton.setText("Сытость: "
                + controller.getParameter(GameLogic.ECharacteristics.SATIETY));
        mEatButton.setOnClickListener(v-> {
            mEatButton.setText("Сытость: "
                + controller.getParameter(GameLogic.ECharacteristics.SATIETY));
            controller.clickOnEatButton();

        });
        mSleepButton = findViewById(R.id.sleepButton);
        mSleepButton.setText("Здоровье: "
                + controller.getParameter(GameLogic.ECharacteristics.HEALTH));
        mSleepButton.setOnClickListener(
                v -> {
                    mSleepButton.setText("Здоровье: "
                            + controller.getParameter(GameLogic.ECharacteristics.HEALTH));
                    controller.clickOnSleepButton();
                }
        );
        mStudyButton = findViewById(R.id.studyButton);
        mStudyButton.setText("Успеваймость: "
                + controller.getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL));
        mStudyButton.setOnClickListener(v-> {
            mStudyButton.setText("Успеваймость: "
                    + controller.getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL));
            controller.clickOnLearnButton();
        });

    }


    @Override
    public void refreshGradientInformation() {

    }

    @Override
    public void refreshTextInformation() {

    }

}
