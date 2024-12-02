package com.appkotlin.isa2android2310;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SuccessActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private static final int DELAY_MILLISECONDS = 5000; // 5 seconds delay


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
// Check if the Android version is Lollipop or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Change the status bar color to black
            getWindow().setStatusBarColor(Color.BLACK);
        }
        // Create MediaPlayer and load the sound file
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);


        // Start playing the sound
        mediaPlayer.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_MILLISECONDS);



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release the MediaPlayer when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
