package com.tobiasstrom.s331392mappe1comtobiasstrom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public void settland(String landskode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cf.setLocale(new Locale(landskode));
        }
        res.updateConfiguration(cf,dm);
        getSharedPreferences("LANGUAGE",MODE_PRIVATE).edit().putString("landskode",landskode).apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 555) {
            if (resultCode == RESULT_OK) {
                settland(getSharedPreferences("LANGUAGE",MODE_PRIVATE).getString("landskode",""));
                recreate();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        settland(getSharedPreferences("LANGUAGE",MODE_PRIVATE).getString("landskode",""));
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
        Intent intent=new Intent(this,SetPreferencesActivity.class);
        startActivity(intent);

    }

    public void btnStatistics(View view) {
        startActivity(new Intent(this, StatisticsActivity.class));
    }
}