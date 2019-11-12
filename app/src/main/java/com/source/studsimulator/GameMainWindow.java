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
    private Button mWorkButton;

    private TextView mMoney;
    private TextView mDollars;

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

        mMoney = findViewById(R.id.moneyCount);
        mDollars = findViewById(R.id.dollarsCount);

        mEatButton = findViewById(R.id.eatButton);
        mEatButton.setText(new StringBuilder().append("Сытость: ").append(controller.getParameter(GameLogic.ECharacteristics.SATIETY)).toString());
        mEatButton.setOnClickListener(v -> {
            controller.clickOnEatButton();
        });

        mSleepButton = findViewById(R.id.sleepButton);
        mSleepButton.setText(new StringBuilder().append("Здоровье: ").append(controller.getParameter(GameLogic.ECharacteristics.HEALTH)).toString());
        mSleepButton.setOnClickListener(
                v -> {
                    controller.clickOnSleepButton();
                }
        );

        mStudyButton = findViewById(R.id.studyButton);
        mStudyButton.setText(new StringBuilder().append("Успеваемость: ").append(controller.getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL)).toString());
        mStudyButton.setOnClickListener(v -> {
            controller.clickOnLearnButton();
        });


        mWorkButton = findViewById(R.id.workButton);
        mWorkButton.setOnClickListener(v -> {
            controller.clickOnWorkButton();
        });



    }


    @Override
    public void refreshGradientInformation() {

    }

    @Override
    public void refreshTextInformation() {
        mStudyButton.setText(new StringBuilder().append("Успеваемость: ").append(controller.
                getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL)).toString());
        mSleepButton.setText(new StringBuilder().append("Здоровье: ").append(controller.
                getParameter(GameLogic.ECharacteristics.HEALTH)).toString());
        mEatButton.setText(new StringBuilder().append("Сытость: ").append(controller.
                getParameter(GameLogic.ECharacteristics.SATIETY)).toString());
        mMoney.setText("" + controller.getParameter(GameLogic.ECharacteristics.MONEY));
        mDollars.setText("" + controller.getParameter(GameLogic.ECharacteristics.DOLLARS));
    }

}
