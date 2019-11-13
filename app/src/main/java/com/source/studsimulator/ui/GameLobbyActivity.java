package com.source.studsimulator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.source.studsimulator.relation.MainContract;
import com.source.studsimulator.R;
import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.relation.Controller;

public class GameLobbyActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter controller = new Controller(this, new GameLogic());

    private Button eatButton;
    private Button sleepButton;
    private Button studyButton;
    private Button workButton;

    private TextView moneyTextView;
    private TextView dollarsTextView;

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

        moneyTextView = findViewById(R.id.moneyCount);
        dollarsTextView = findViewById(R.id.dollarsCount);

        eatButton = findViewById(R.id.eatButton);
        eatButton.setText(new StringBuilder().append("Сытость: ").append(controller.getParameter(GameLogic.ECharacteristics.SATIETY)).toString());
        eatButton.setOnClickListener(v -> {
            controller.clickOnEatButton();
        });

        sleepButton = findViewById(R.id.sleepButton);
        sleepButton.setText(new StringBuilder().append("Здоровье: ").append(controller.getParameter(GameLogic.ECharacteristics.HEALTH)).toString());
        sleepButton.setOnClickListener(
                v -> {
                    controller.clickOnSleepButton();
                }
        );

        studyButton = findViewById(R.id.studyButton);
        studyButton.setText(new StringBuilder().append("Успеваемость: ").append(controller.getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL)).toString());
        studyButton.setOnClickListener(v -> {
            controller.clickOnLearnButton();
        });


        workButton = findViewById(R.id.workButton);
        workButton.setOnClickListener(v -> {
            controller.clickOnWorkButton();
        });
    }


    @Override
    public void refreshGradientInformation() {

    }

    @Override
    public void refreshTextInformation() {
        studyButton.setText(new StringBuilder().append("Успеваемость: ").append(controller.
                getParameter(GameLogic.ECharacteristics.EDUCATION_LEVEL)).toString());
        sleepButton.setText(new StringBuilder().append("Здоровье: ").append(controller.
                getParameter(GameLogic.ECharacteristics.HEALTH)).toString());
        eatButton.setText(new StringBuilder().append("Сытость: ").append(controller.
                getParameter(GameLogic.ECharacteristics.SATIETY)).toString());
        moneyTextView.setText("" + controller.getParameter(GameLogic.ECharacteristics.MONEY));
        dollarsTextView.setText("" + controller.getParameter(GameLogic.ECharacteristics.DOLLARS));
    }
}
