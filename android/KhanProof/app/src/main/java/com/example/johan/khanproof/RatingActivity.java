package com.example.johan.khanproof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    TextView originalText;
    Button goodButton;
    Button maybeButton;
    Button badButton;
    LinearLayout levelprogressbar;
    int securePoints;
    int potentialPoints;
    int leftPoints;
    static int minprogress;
    static int maxprogress;
    static ProgressBar levelProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rating);
        originalText = findViewById(R.id.originaltext);
        levelProgressBar = findViewById(R.id.levelProgressBar);
        levelProgressBar.setProgress(25);
        levelProgressBar.setSecondaryProgress(55);
    }

    public void onClickGood(View view){
        toast("Good pressed");
    }

    public void onClickMaybe(View view){
        toast("Maybe pressed");
    }

    public void onClickBad(View view){
        toast("Bad pressed");
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    static public void setLevelBarMaxAndMin(int min, int max) {
        minprogress = min;
        maxprogress = max;
        levelProgressBar.setMax(max - min);
    }

    static public void setLevelBarPrimaryProgress(int progress) {
        levelProgressBar.setProgress(progress - minprogress);
    }
    static public void setLevelBarSecondaryProgress(int progress) {
        levelProgressBar.setSecondaryProgress(progress - minprogress);
    }
}
