package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {
    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        cf.setLocale(new Locale(landskode));
        res.updateConfiguration(cf,dm);
        getSharedPreferences("LANGUAGE",MODE_PRIVATE).edit().putString("landskode",landskode).apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settland(getSharedPreferences("LANGUAGE",MODE_PRIVATE).getString("landskode",""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }
    public void btnExitStatistics(View view) {
        this.finish();
        //startActivity(new Intent(this, StatisticsActivity.class));
    }
}