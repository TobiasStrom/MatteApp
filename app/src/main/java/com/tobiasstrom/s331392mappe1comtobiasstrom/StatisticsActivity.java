package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {
    private static final String TAG = "StatisticsActivity";

    ListView listView;
    TextView textView;

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
        listView = (ListView) findViewById(R.id.xmlListViw);
        StratisticsAdapter arrayAdapter = new StratisticsAdapter(this, R.layout.listview_row, StatisticsBrain.statistics);
        listView.setAdapter(arrayAdapter);
        textView = (TextView) findViewById(R.id.txt_posent);
        if (StatisticsBrain.statistics.isEmpty()){
            textView.setText(R.string.prosent);
        }else {
            textView.setText(getText(R.string.right) + " "+ Statistics.prosent()+"%");
        }


    }
    public void btnExitStatistics(View view) {
        this.finish();
    }

    public void btnResetStatistics(View view) {
        StatisticsBrain.statistics.clear();
        listView = (ListView) findViewById(R.id.xmlListViw);
        listView.setAdapter(null);
        textView.setText(R.string.prosent);

    }
}