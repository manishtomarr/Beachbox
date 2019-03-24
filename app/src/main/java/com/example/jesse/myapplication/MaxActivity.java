package com.example.jesse.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MaxActivity extends AppCompatActivity implements SensorEventListener {

    //Workout Screen
    // I used the force equation to calculate the points and using some other minor restrictions we had it add only if it's not a decimal
    //again this can be edited
    //if you need helping understanding this, I used a youtube video just google accelerometer android studio

    private static final String TAG = "MainActivity";

    public static long START_TIME_IN_MILLIS = 600000;
    private static int histcount = 0;
    public static List<Long> histlist = new ArrayList<Long>();
    private static long addscore = 0;

    private SensorManager sensorManager;
    Sensor accelerometer;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis=START_TIME_IN_MILLIS;

    TextView force, impactscore, timer, timertext, currimpactscore, currforce;
    Typeface tizafont;
    public static EditText edittimer;
    public int time = 100000;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super. onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);

        force = (TextView) findViewById(R.id.force);
        impactscore = (TextView) findViewById(R.id.impactscore);
        currimpactscore = (TextView) findViewById(R.id.currimpactscore);
        currforce = (TextView) findViewById(R.id.currforce);
        timertext = (TextView) findViewById(R.id.timertext);
        timer = findViewById(R.id.timer);
        EditText edittimer = (EditText) findViewById(R.id.TimerET);

        tizafont = Typeface.createFromAsset(getAssets(), "tiza.ttf");
        force.setTypeface(tizafont);
        impactscore.setTypeface(tizafont);
        currimpactscore.setTypeface(tizafont);
        currforce.setTypeface(tizafont);
        timertext.setTypeface(tizafont);
        timer.setTypeface(tizafont);

        addscore = 0;

        timertext.setText("Timer");
        if(!(mTimerRunning)){
            startTimer();
        }


        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context. SENSOR_SERVICE) ;

        accelerometer = sensorManager. getDefaultSensor(Sensor. TYPE_ACCELEROMETER) ;
        sensorManager. registerListener(MaxActivity. this, accelerometer, SensorManager. SENSOR_DELAY_NORMAL) ;

        Log. d(TAG, "onCreate: Registered accelerometer listener");

    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 60) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                if(mTimeLeftInMillis == 0){
                    mCountDownTimer.cancel();
                    mCountDownTimer.onFinish();
                }
            }

            @Override
            public void onFinish() {
                histcount++;
                histlist.add(addscore);
                goBack();
            }
            private void goBack() {
                finish();
            }
        }.start();

        mTimerRunning = true;

    }
    private void pauseimer() {}
    private void resetTimer(){}

    @Override
    public void onBackPressed() {
        finish();
        ///add to history
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timer.setText(timeLeftFormatted);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        DecimalFormat df = new DecimalFormat("#.00");
        double aforce;
        double nforce = 0;
        //double recenthigh = 0;
        double s1, s2, s3;
        s1 = sensorEvent.values[0];
        s2 = sensorEvent.values[1];
        s3 = sensorEvent.values[2];
        aforce = Math.sqrt(10*(sensorEvent.values[0]*sensorEvent.values[0]) + (sensorEvent.values[1]*sensorEvent.values[1]) + (sensorEvent.values[2])*sensorEvent.values[2]);
        //aforce = aforce - SensorManager.GRAVITY_EARTH;
        aforce *= .145;
        Log. d(TAG, "Force is" + aforce + "N");
        aforce *= 10000000;
        aforce = (int) aforce;
        aforce /= 1000;
        nforce = Math.abs(((aforce - SensorManager.GRAVITY_EARTH) / 14240)-1);

        //////////////////////5 Second High Score
        //while (nforce > recenthigh) {
        //    recenthigh = nforce;
        //}
        //if ((mTimeLeftInMillis/1000)%5==0) { /// /
        //    force.setText(df.format(recenthigh));
        //    force.append('\n' + Long.toString((mTimeLeftInMillis)/1000));
        //}

        ////testing amplification of score
        if (nforce > .99) { nforce *= 10;
            addscore += nforce;
        }

        if (nforce <.99) {
            currforce.setText("0");
        }
        else {
            currforce.setText(df.format(nforce));
        }
        //force.setText('\n' + "Current Impact");
        force.setText(df.format(addscore));

        currimpactscore.setText("Current Impact Score");
        impactscore.setText( "Impact Score" );
    }
    public void stop(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
        //startActivity(intent);
        finish();
        ///write the high score to the file

        histlist.add(addscore);

        //File savefile = new File(getExternalFilesDir(null), "savefile.txt");
        //FileWriter fw = new FileWriter(savefile);
        //BufferedWriter bw = new BufferedWriter(fw);
        //PrintWriter pw = new PrintWriter(bw);
        //pw.append('\n' + String.valueOf(addscore));
        //pw.close();
    }
    public void GoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
    public void nd(View view) {
        startActivity(new Intent(this, MaxActivity.class));
        //MaxActivity.START_TIME_IN_MILLIS = 400000;
    }

    public void GoLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void GoHistory(View view) {
        startActivity(new Intent(this, histActivity.class));
    }

    public void onClick(View view) {
        Snackbar.make(view, "Coding Required", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}