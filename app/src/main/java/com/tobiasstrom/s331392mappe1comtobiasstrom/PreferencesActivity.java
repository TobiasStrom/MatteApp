package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }
    public void btnExitPreferences(View view) {
        this.finish();
        //startActivity(new Intent(this, StatisticsActivity.class));
    }

}