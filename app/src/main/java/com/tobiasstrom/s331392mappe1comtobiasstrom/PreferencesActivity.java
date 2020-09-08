package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioGroup radioGroupLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }
    public void btnExitPreferences(View view) {
        this.finish();

    }


}