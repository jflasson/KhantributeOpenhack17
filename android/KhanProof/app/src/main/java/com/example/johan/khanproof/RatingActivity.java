package com.example.johan.khanproof;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RatingActivity extends AppCompatActivity {

    TextView originalText;
    TextView translationText;
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
    final String getStringsUrl = "http://node-express-env.ft838mhenj.eu-west-1.elasticbeanstalk.com/texts/string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rating);
        originalText = findViewById(R.id.originaltext);
        translationText = findViewById(R.id.translationtext);
        levelProgressBar = findViewById(R.id.levelProgressBar);
        levelProgressBar.setProgress(43);
        levelProgressBar.setSecondaryProgress(43+15);
        fetchStrings();
    }

    public void fetchStrings(){
        DownloadTask task = new DownloadTask();
        String result = null;
        try {
            result = task.execute(getStringsUrl).get();
            parseResult(result);
        } catch (Exception e) {
            Log.e("jforss", "well that's unfortunate");
        }

    }

    public void parseResult(String response){
        String[] parts = response.split("\"");
        for(String part : parts) {
            part = part.replace("\"", "");
        }
        setTranslations(parts);

    }

    public void setTranslations(String [] parts) {
        originalText.setText(parts[7]);
        translationText.setText(parts[11]);
    }

    public void onClickGood(View view){
        fetchStrings();
        toast("Good pressed");
    }

    public void onClickMaybe(View view){
        fetchStrings();
        toast("Maybe pressed");
    }

    public void onClickBad(View view){
        fetchStrings();
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

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                Log.i("jforss", "DONE");
                return result;


            } catch (Exception e) {
                e.printStackTrace();
                Log.i("jforss", "FAILED");
                return "failed";
            }
        }
    }
}
