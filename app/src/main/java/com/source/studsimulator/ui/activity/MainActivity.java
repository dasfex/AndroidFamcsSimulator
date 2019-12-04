package com.source.studsimulator.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.source.studsimulator.R;

public class MainActivity extends AppCompatActivity {

    private Button newGameButton;
    private Button settingsButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameLobbyActivity.class);
            startActivity(intent);
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            super.onDestroy();
            System.runFinalization();
            System.exit(0);
        });
    }
}
