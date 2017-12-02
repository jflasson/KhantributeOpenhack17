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
    static TextView securePointsTv;
    static TextView potentialPointsTv;
    static TextView pointsleftTv;
    int securePoints;
    int potentialPoints;
    int leftPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rating);
        originalText = (TextView) findViewById(R.id.originaltext);
        goodButton = (Button) findViewById(R.id.btnGood);
        maybeButton = (Button) findViewById(R.id.btnMaybe);
        badButton = (Button) findViewById(R.id.btnBad);
        securePointsTv = (TextView) findViewById(R.id.securepoints);
        potentialPointsTv = (TextView) findViewById(R.id.potentialpoints);
        pointsleftTv = (TextView) findViewById(R.id.pointstoleftlevel);
        ProgressBar levelProgressBar = (ProgressBar) findViewById(R.id.levelProgressBar);
        levelProgressBar.setProgress(25);
        levelProgressBar.setSecondaryProgress(55);
    }

    public void onClickGood(View view){
        toast("Good pressed");
        setLevelBar(90, 5, 5);
    }

    public void onClickMaybe(View view){
        toast("Maybe pressed");
        setLevelBar(25, 55, 30);
    }

    public void onClickBad(View view){
        toast("Bad pressed");
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    static public void setLevelBar(int secure, int potential, int left) {
        securePointsTv.setLayoutParams(new TableLayout.LayoutParams(securePointsTv.getHeight(), securePointsTv.getWidth(), secure));
        potentialPointsTv.setLayoutParams(new TableLayout.LayoutParams(potentialPointsTv.getHeight(), potentialPointsTv.getWidth(), potential));
        pointsleftTv.setLayoutParams(new TableLayout.LayoutParams(pointsleftTv.getHeight(), pointsleftTv.getWidth(), left));
    }
}
