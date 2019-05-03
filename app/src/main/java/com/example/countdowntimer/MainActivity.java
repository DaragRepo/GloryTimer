package com.example.countdowntimer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView time;

    private final static int MIN = 30;

    private final static int MAX= 600;

    private SeekBar seekBar;

    private Button go;


    public void StartTimer(View view) {


        seekBar.setEnabled(false);

        if (go.getText().equals("Stop")) {
            recreate();
        } else {
            go.setText("Stop");
            new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer airHorn = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    airHorn.start();
                    recreate();
                }
            }.start();
        }

    }


    public void updateTimer(int timeLeft) {

        int minutes = timeLeft / 60;

        int seconds = timeLeft - (minutes * 60) ;

        if (seconds < 10) {
            time.setText(minutes + ":0" + seconds);
        } else {
            time.setText(minutes + ":" + seconds);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);

        go = findViewById(R.id.start);


        seekBar.setProgress(MIN);

        seekBar.setMax(MAX);

        time = findViewById(R.id.textView);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}
