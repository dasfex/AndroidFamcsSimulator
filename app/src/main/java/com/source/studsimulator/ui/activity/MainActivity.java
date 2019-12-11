package com.source.studsimulator.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.source.studsimulator.R;

public class MainActivity extends AppCompatActivity  {

    private Button newGameButton;
    private Button settingsButton;
    private Button exitButton;

  //  private BackgroundSound backgroundSound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



//        backgroundSound = new BackgroundSound();
//        backgroundSound.execute();

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

//    @Override
//    public void setPause(){
//        backgroundSound.cancel(true);
//    }
//
//    @Override
//    public void setPlay() {
//
//        backgroundSound.execute();
//    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        backgroundSound.cancel(true);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }

//    private class BackgroundSound extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.background);
//            player.setLooping(true); // Set looping
//            player.setVolume(1.0f, 1.0f);
//            player.start();
//
//            return null;
//        }
//
//    }
}
