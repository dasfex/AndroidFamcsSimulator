package com.source.studsimulator.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

    private TextView moneyTextView;

    ImageButton infoButton;
    ImageButton foodButton;
    ImageButton studyButton;

    Fragment informationFragment;
    Fragment foodFragment;
    Fragment studyFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_lobby_activity);

        informationFragment = new InfoFragment();
        foodFragment = new FoodFragment();
        studyFragment = new StudyFragment();

        infoButton = findViewById(R.id.infoButton);
        foodButton = findViewById(R.id.foodButton);
        studyButton = findViewById(R.id.studyButton);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentLayout, informationFragment).commit();

        setOnClickListenersForFragmentButtons();

        initPlayerStatsView();
        presenter.viewCreated();
    }

    private void initPlayerStatsView() {
        moneyTextView = findViewById(R.id.moneyCount);

    }

    @Override
    public void refreshGradientInformation() {

    }

    @Override
    public void refreshPlayerStats(PlayerStats stats) {
        //studyButton.setText(String.format(getString(R.string.study), stats.getEducationLevel()));
        //sleepButton.setText(String.format(getString(R.string.sleep), stats.getHealth()));
        //eatButton.setText(String.format(getString(R.string.satiety), stats.getSatiety()));
        moneyTextView.setText(stats.getMoney());
    }

    private void setOnClickListenersForFragmentButtons() {
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentById(R.id.fragmentLayout) != informationFragment) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentLayout, informationFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentById(R.id.fragmentLayout) != foodFragment) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentLayout, foodFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().findFragmentById(R.id.fragmentLayout) != studyFragment) {
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentLayout, studyFragment);
                    fragmentTransaction.commit();
                }
            }
        });
    }
}
