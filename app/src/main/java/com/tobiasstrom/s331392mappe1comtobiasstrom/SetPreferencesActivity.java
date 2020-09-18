package com.tobiasstrom.s331392mappe1comtobiasstrom;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import java.util.Locale;

public class SetPreferencesActivity extends PreferenceActivity {

    //Oppretter variablene vi trenger
    SharedPreferences sharedPreferences;
    String currentLang;

    //Setter språk
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

    //event listener som hører på endringer, hvis språket blir endret, skal det oppdateres i selve preferences "scene"
    SharedPreferences.OnSharedPreferenceChangeListener spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if (!sharedPreferences.getString("languagePref", "").equals(currentLang)) {
                recreate();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentLang = sharedPreferences.getString("languagePref","");
        setLang(currentLang);
        super.onCreate(savedInstanceState);
        //event listener registreres
        sharedPreferences.registerOnSharedPreferenceChangeListener(spChanged);
        getFragmentManager().beginTransaction().replace(android.R.id.content,new
                PrefsFragment()).commit();
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState); addPreferencesFromResource(R.xml.preferences);
        }
    }
}
