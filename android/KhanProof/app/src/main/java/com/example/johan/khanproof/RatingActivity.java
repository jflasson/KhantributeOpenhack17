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
import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {
    int paircounter;
    int securepoints;
    int potentialpoints;
    TextView originalText;
    TextView translationText;
    TextView scoreTextView;
    static int minprogress;
    static int maxprogress;
    static ProgressBar levelProgressBar;
    //Change this to change server address
    final String getStringsUrl = "http://node-express-env.ft838mhenj.eu-west-1.elasticbeanstalk.com/texts/string";

    ArrayList<StringPair> samplePairs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rating);
        originalText = findViewById(R.id.originaltext);
        translationText = findViewById(R.id.translationtext);
        levelProgressBar = findViewById(R.id.levelProgressBar);
        scoreTextView = findViewById(R.id.scoreTextView);
        securepoints = 43;
        potentialpoints = 5;
        updateLevelBar();
        samplePairs = new ArrayList<>();
        samplePairs.add(new StringPair("My hovercraft is full of eels!", "Min svävare är full med ålar!"));
        samplePairs.add(new StringPair("The gravitational pull on an object is proportional to its mass.", "Den gravitationskraft som verkar på ett föremål är direkt proportionerlig till föremålets massa."));
        samplePairs.add(new StringPair("I will not buy this record, it is scratched.", "Jag önskar köpa cigaretter."));
        samplePairs.add(new StringPair("Your mother was a hamster and your father smelled of elderberries.", "God afton min herre."));
        samplePairs.add(new StringPair("The derivative of a function represents its rate of change", "Funktionens derivata representerar dess förändringshastighet."));
        paircounter = 0;
        getSampleStrings();
//      Uncomment below to use server instead of sample data
//      fetchStrings();

    }

    public void updateLevelBar() {
        scoreTextView.setText(securepoints + "(" + potentialpoints + ")");
        levelProgressBar.setProgress(securepoints);
        levelProgressBar.setSecondaryProgress(securepoints + potentialpoints);
    }

    public void getSampleStrings(){
        paircounter++;
        if(paircounter > 4) {
            paircounter = 0;
        }
        originalText.setText(samplePairs.get(paircounter).original);
        translationText.setText(samplePairs.get(paircounter).translation);
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
        getSampleStrings();
        potentialpoints += 3;
        updateLevelBar();
    }

    public void onClickMaybe(View view){
        getSampleStrings();
        potentialpoints += 1;
        updateLevelBar();
    }

    public void onClickBad(View view){
        getSampleStrings();
        potentialpoints += 3;
        updateLevelBar();
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
