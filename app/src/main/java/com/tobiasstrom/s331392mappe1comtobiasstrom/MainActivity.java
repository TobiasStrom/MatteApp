package com.tobiasstrom.s331392mappe1comtobiasstrom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cf.setLocale(new Locale(landskode));
        }
        res.updateConfiguration(cf,dm);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 555) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            settland(sharedPreferences.getString("languagePref",""));
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ONcreate","hello");
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        settland(sharedPreferences.getString("languagePref", ""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnStartGame(View view){
        startActivity(new Intent(this, GameActivity.class));

    }

    public void btnPreferences(View view){
        //Intent intent = new Intent(this,PreferencesActivity.class);
        //startActivityForResult(intent,555);
        //startActivity(new Intent(this, PreferencesActivity.class));
        Intent intent = new Intent(this,SetPreferencesActivity.class);
        startActivityForResult(intent,555);

    }

    public void btnStatistics(View view) {
        startActivity(new Intent(this, StatisticsActivity.class));
    }
}