package com.source.studsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameMainWindow extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter controller = new Controller(this, new GameLogic());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_window);
        ImageButton info = findViewById(R.id.infoButton);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // запускаем фрагмент с информаицей
            }
        });
    }

    @Override
    public void refreshTextInformation() {

    }

    @Override
    public void refreshGradientInformation() {

    }
}
