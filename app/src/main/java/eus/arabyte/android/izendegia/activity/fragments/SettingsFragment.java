package eus.arabyte.android.izendegia.activity.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.DisplayMetrics;

import java.util.Locale;

import eus.arabyte.android.izendegia.R;
import eus.arabyte.android.izendegia.activity.MainActivity;
import eus.arabyte.android.izendegia.utils.LocaleManager;
import eus.arabyte.android.izendegia.utils.Preferences;

/**
 * Created by ichigo on 5/11/17.
 */

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        PreferenceManager pm = getPreferenceManager();
        ListPreference hizkuntza = (ListPreference) pm.findPreference(Preferences.HIZKUNTZA);
        hizkuntza.setOnPreferenceChangeListener(new PreferenceChangeListener());

        getActivity().setTitle(R.string.preferences);

    }

    /**
     * PreferenceChangeListener
     */
    private class PreferenceChangeListener implements Preference.OnPreferenceChangeListener{

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            if (Preferences.HIZKUNTZA.equals(preference.getKey())) {
                LocaleManager.setLocale(getContext(),(String) newValue);

                //on language change restart fragment
                ((MainActivity)getActivity()).restartActivity();
            }

            return true;
        }
    }
}
