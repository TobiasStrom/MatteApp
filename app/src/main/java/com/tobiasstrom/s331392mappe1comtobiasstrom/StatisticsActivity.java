package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity implements DialogReset.DialogClickListener {
    //Setter variablen vi trenger
    private int right = 0;
    private int wrong = 0;
    //denne verdi er den som statistikkene skal få etter restet
    int valueAfterReset = 0;
    TextView textViewRight;
    TextView textViewWrong;
    SharedPreferences sharedPreferences;

    //metoden som setter språket basert på gitt landskode hentet fra sharedPreferences
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

    //metoden som prøver å hente verdier fra sharedPreferences, setter verdi til 0 dersom den feiler
    private int getStats(SharedPreferences sharedPreferences, String key) {
        int value;
        try {
            value = sharedPreferences.getInt(key, 0);
        } catch (Exception e) {
            value = 0;
        }
        return value;
    }

    //Dialogbox sånn at det ikke bare er å trykke feil på nullstill
    public void resetStats(View v) {
        DialogFragment dialog = new DialogReset();
        dialog.show(getFragmentManager(),"Reset");

    }

    @Override
    public void onYesClick() {
        //metoden nullstiller statistikk verdier til en gitt verdi (0)
        sharedPreferences.edit().putInt("stats_right", valueAfterReset).apply();
        sharedPreferences.edit().putInt("stats_wrong", valueAfterReset).apply();
        textViewRight.setText(String.valueOf(valueAfterReset));
        textViewWrong.setText(String.valueOf(valueAfterReset));
    }

    @Override
    public void onNoClick() {
        //return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Henter verdien og setter land
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setLang(sharedPreferences.getString("languagePref",""));
        right = getStats(sharedPreferences, "stats_right");
        wrong = getStats(sharedPreferences, "stats_wrong");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //setter sånn at utsende blir bra.
        textViewRight = findViewById(R.id.txt_stats_correct);
        textViewWrong = findViewById(R.id.txt_stats_wrong);
        textViewRight.setText(String.valueOf(right));
        textViewWrong.setText(String.valueOf(wrong));
    }
    public void btnExitStatistics(View view) {
        this.finish();
    }
}