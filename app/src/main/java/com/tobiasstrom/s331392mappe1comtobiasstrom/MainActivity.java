package com.tobiasstrom.s331392mappe1comtobiasstrom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnStartGame(View view){
        startActivity(new Intent(this, GameActivity.class));

    }
    public void btnPreferences(View view){
        startActivity(new Intent(this, PreferencesActivity.class));
    }

    public void btnStatistics(View view) {
        startActivity(new Intent(this, StatisticsActivity.class));
    }
}