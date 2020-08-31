package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
    public void btnExitStatistics(View view) {
        this.finish();
        //startActivity(new Intent(this, StatisticsActivity.class));
    }
}