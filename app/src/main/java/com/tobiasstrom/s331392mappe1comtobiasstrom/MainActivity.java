package com.tobiasstrom.s331392mappe1comtobiasstrom;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //Oppretter sharedPreferences
    SharedPreferences sharedPreferences;

    //Metoden som skjekker land.
    public void setLang(String prefix) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration cf = res.getConfiguration();
        //setLocale krever api lvl 17 (current definert api lvl er 15) Dette er en av forslagene til AndroidStudio
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cf.setLocale(new Locale(prefix));
        }
        res.updateConfiguration(cf,dm);
    }

    @Override
    //metoden tilbyr muligheten å kalle metoden settland etter at preferanse fragment er over
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 555) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            setLang(sharedPreferences.getString("languagePref",""));
            recreate();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setLang(sharedPreferences.getString("languagePref", ""));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Bytter til start spill skjermen
    public void btnStartGame(View view){
        startActivity(new Intent(this, GameActivity.class));
    }

    public void btnPreferences(View view){
        //Intent intent = new Intent(this,PreferencesActivity.class);
        //startActivityForResult(intent,555);
        //startActivity(new Intent(this, PreferencesActivity.class));
        Intent intent = new Intent(this,SetPreferencesActivity.class);
        startActivityForResult(intent,555);
         /*
            dette vil kjøre metoden onActivityResult etter at preferanse activity blir ferdig
            Det er noe som er nødvendig til å oppdatere språket.
            Oncreate kjøres ikke hvis fragmentet preferanser er over.
         */

    }
    //Åpener stattestikk .
    public void btnStatistics(View view) {
        startActivity(new Intent(this, StatisticsActivity.class));
    }
}