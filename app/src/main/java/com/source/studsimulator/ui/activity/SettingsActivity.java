package com.source.studsimulator.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.source.studsimulator.R;
import com.source.studsimulator.ui.StudSimulatorApplication;

@SuppressLint("Registered")
public class SettingsActivity extends AppCompatActivity {

    private Button githubButton;

    @Override
    protected void onCreate(Bundle savedIstanceState) {
        super.onCreate(savedIstanceState);
        setContentView(R.layout.settings_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        githubButton = findViewById(R.id.gitButton);
        githubButton.setOnClickListener(v -> GoToGithub());
    }

    private void GoToGithub() {
        Uri uriUrl = Uri.parse(StudSimulatorApplication.getContext().getString(R.string.github));
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
