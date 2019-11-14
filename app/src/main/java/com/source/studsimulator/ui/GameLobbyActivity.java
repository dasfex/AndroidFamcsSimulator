package com.source.studsimulator.ui;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView dollarsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lobby_activity);
        ImageButton info = findViewById(R.id.infoButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запускаем фрагмент с информацией
            }
        });
        initPlayerStatsView();
        presenter.viewCreated();
    }

    private void initPlayerStatsView() {
        moneyTextView = findViewById(R.id.moneyCount);
        dollarsTextView = findViewById(R.id.dollarsCount);

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
        dollarsTextView.setText(stats.getDollars());
    }
}
