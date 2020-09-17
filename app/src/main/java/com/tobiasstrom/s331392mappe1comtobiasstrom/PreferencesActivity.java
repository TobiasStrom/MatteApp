package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PreferencesActivity extends AppCompatActivity {
    private static final String TAG = "PreferencesActivity";
    private int numberOfQuestions;
    private int unConfermdNumberOfQuestion;
    Button btn_5;
    Button btn_10;
    Button btn_25;
    private RadioGroup radioGroup;
    private RadioGroup radioGroupLanguage;


    private static final String STATE_NUMBEROFQUESTION = "NumberOfQuestion";


    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf,dm);
        getSharedPreferences("LANGUAGE",MODE_PRIVATE).edit().putString("landskode",landskode).apply();
    }

    public void submitChanges(View v) {
        numberOfQuestions = unConfermdNumberOfQuestion;
        GameActivity.setNumberOfQuestions(numberOfQuestions);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupLanguage);
        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        if(radioButton.getId() == R.id.radioGerman) {
            settland("de");
        } else {
            settland("");
        }

        recreate();

    }

    private void adjustLanguageRadioButtonToggles(String countryCode) {

        RadioButton norwegianButton = (RadioButton) findViewById(R.id.radioNorwegian);
        RadioButton germanButton = (RadioButton) findViewById(R.id.radioGerman);

        if(countryCode.equals("de")) {
            norwegianButton.setChecked(false);
            germanButton.setChecked(true);

        } else {
            //hvis språker er ikke tysk, da må den være norsk og i dette tilfelle gjør ingenting.
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String countrycode = getSharedPreferences("LANGUAGE",MODE_PRIVATE).getString("landskode","");
        settland(countrycode);
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_preferences);
        adjustLanguageRadioButtonToggles(countrycode);
        int numberOfQuestion = GameActivity.getNumberOfQuestions();
        oneDark(numberOfQuestion);

    }
    public void btnExitPreferences(View view) {
        setResult(RESULT_OK);
        this.finish();
    }

    public void btn_question_5 (View view){
        oneDark(5);
        unConfermdNumberOfQuestion = 5;

    }
    public void btn_question_10 (View view){
        unConfermdNumberOfQuestion = 10;
        oneDark(10);

    }
    public void btn_question_25 (View view){
        Log.e(TAG, "btn_question_25: 25" );
        unConfermdNumberOfQuestion = 25;

        oneDark(25);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_NUMBEROFQUESTION, numberOfQuestions);
        Log.e(TAG, "onRestoreInstanceState: " + numberOfQuestions );

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState: " + numberOfQuestions );
        numberOfQuestions = savedInstanceState.getInt(STATE_NUMBEROFQUESTION);

        numberOfQuestions = GameActivity.getNumberOfQuestions();
        oneDark(numberOfQuestions);
    }
    public void oneDark(int number){
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_25 = (Button) findViewById(R.id.btn_25);


        btn_5.setBackgroundResource(R.xml.rounded_button_inchworm_dark);
        btn_10.setBackgroundResource(R.xml.rounded_button_inchworm_dark);
        btn_25.setBackgroundResource(R.xml.rounded_button_inchworm_dark);
        if(number == 5){
            btn_5.setBackgroundResource(R.xml.rounded_button_inchworm);
        }
        if (number == 10){
            btn_10.setBackgroundResource(R.xml.rounded_button_inchworm);
        }
        if(number == 25){
            btn_25.setBackgroundResource(R.xml.rounded_button_inchworm);
        }

    }
}