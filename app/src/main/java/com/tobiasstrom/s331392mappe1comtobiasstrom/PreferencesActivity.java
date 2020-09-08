package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class PreferencesActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioGroup radioGroupLanguage;

    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf,dm);
        getSharedPreferences("LANGUAGE",MODE_PRIVATE).edit().putString("landskode",landskode).apply();
    }

    public void submitChanges(View v) {
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
    }
    public void btnExitPreferences(View view) {
        setResult(RESULT_OK);
        this.finish();
    }


}