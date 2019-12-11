package com.source.studsimulator.ui.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

public class SoundActivity extends AppCompatActivity {
    public static void hearSound(Context context, int soundIndex){
        MediaPlayer sound = MediaPlayer.create(context, soundIndex);
        sound.start();
    }
}
