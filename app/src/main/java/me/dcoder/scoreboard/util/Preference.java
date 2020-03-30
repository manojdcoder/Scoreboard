package me.dcoder.scoreboard.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class Preference {

    private Context context;
    private SharedPreferences sharedPreferences;

    public Preference(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean contains(int preferenceKey) {
        return sharedPreferences.contains(context.getString(preferenceKey));
    }

    public void remove(int preferenceKey) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(context.getString(preferenceKey));
        editor.apply();
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void setString(int preferenceKey, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(preferenceKey), value);
        editor.apply();
    }

    public String getString(int preferenceKey) {
        return sharedPreferences.getString(context.getString(preferenceKey), "");
    }

    public void setInt(int preferenceKey, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getString(preferenceKey), value);
        editor.apply();
    }

    public int getInt(int preferenceKey) {
        return sharedPreferences.getInt(context.getString(preferenceKey), 0);
    }

    public void setBoolean(int preferenceKey, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(preferenceKey), value);
        editor.apply();
    }

    public boolean getBoolean(int preferenceKey) {
        return sharedPreferences.getBoolean(context.getString(preferenceKey), false);
    }
}
