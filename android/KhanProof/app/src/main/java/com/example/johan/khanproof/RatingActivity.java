package com.example.johan.khanproof;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
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
    static TextView potentialpointsTv;
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
        potentialpointsTv = (TextView) findViewById(R.id.potentialpoints);
        pointsleftTv = (TextView) findViewById(R.id.pointstoleftlevel);
    }

    public void onClickGood(View view){
        toast("Good pressed");
        setLevelBar(90, 5, 5);
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

    static public void setLevelBar(int secure, int potential, int left) {
        securePointsTv.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT, secure));
        potentialpointsTv.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT, potential));
        pointsleftTv.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.FILL_PARENT, left));
    }
}
