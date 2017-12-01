package com.example.johan.khanproof;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    TextView originalText;
    Button goodButton;
    Button maybeButton;
    Button badButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rating);


        originalText = (TextView) findViewById(R.id.originaltext);
        goodButton = (Button) findViewById(R.id.btnGood);
        maybeButton = (Button) findViewById(R.id.btnMaybe);
        badButton = (Button) findViewById(R.id.btnBad);
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
}
