package com.example.android.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Boolean isActive = false;
    Button button ;
    CountDownTimer count;
    public void reset(){
        textView.setText("00.30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        count.cancel();
        button.setText("START");
        isActive = false;
    }
    public void OnButton (View view) {
        if (isActive) {
          reset();
        } else {
            isActive = true;
            seekBar.setEnabled(false);
            button.setText("STOP");

             count = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    reset();
                }
            };
            count.start();
        }
    }
    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes *60);
        String secondseconds  = Integer.toString(seconds);
        String secondMinutes = Integer.toString(minutes);

        if (minutes <9){
            secondMinutes = "0" + minutes;
        }

        if (seconds < 9){
            secondseconds = "0" + seconds;
        }
        textView.setText(secondMinutes +":" + secondseconds);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      button = (Button) findViewById(R.id.button);
         seekBar = (SeekBar) findViewById(R.id.seekBar);
          textView = (TextView) findViewById(R.id.textView);
        seekBar.setMax(600);
        seekBar.setProgress(30);

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
