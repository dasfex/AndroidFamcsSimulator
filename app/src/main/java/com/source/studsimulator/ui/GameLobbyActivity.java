package com.source.studsimulator.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.source.studsimulator.relation.GameContract;
import com.source.studsimulator.R;
import com.source.studsimulator.model.GameLogic;
import com.source.studsimulator.relation.GamePresenter;
import com.source.studsimulator.ui.entity.PlayerStats;

public class GameLobbyActivity extends AppCompatActivity implements GameContract.View {

    private GameContract.Presenter presenter = new GamePresenter(this, new GameLogic());

    private Button eatButton;
    private Button sleepButton;
    private Button studyButton;
    private Button workButton;

    private TextView moneyTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lobby_activity);

        initPlayerStatsView();
        presenter.viewCreated();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infoButton:

        }
    }

    private void initPlayerStatsView() {
        moneyTextView = findViewById(R.id.moneyCount);

        eatButton = findViewById(R.id.eatButton);
        eatButton.setOnClickListener(v -> {
            presenter.clickOnEatButton();
        });

        sleepButton = findViewById(R.id.sleepButton);
        sleepButton.setOnClickListener(
                v -> {
                    presenter.clickOnSleepButton();
                }
        );

        studyButton = findViewById(R.id.studyButton);
        studyButton.setOnClickListener(v -> {
            presenter.clickOnLearnButton();
        });

        workButton = findViewById(R.id.workButton);
        workButton.setOnClickListener(v -> {
            presenter.clickOnWorkButton();
        });
    }

    @Override
    public void refreshGradientInformation() {

    }

    @Override
    public void refreshPlayerStats(PlayerStats stats) {
        studyButton.setText(String.format(getString(R.string.study), stats.getEducationLevel()));
        sleepButton.setText(String.format(getString(R.string.sleep), stats.getHealth()));
        eatButton.setText(String.format(getString(R.string.satiety), stats.getSatiety()));
        moneyTextView.setText(stats.getMoney());
    }
}
