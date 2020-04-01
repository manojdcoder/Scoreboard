package me.dcoder.scoreboard.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import me.dcoder.scoreboard.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_settings, rootKey);
    }
}