package com.corphish.tsp;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by avinabadalal on 21/07/17.
 * Instance based non-persistent shared preference
 */

public class TemporarySharedPreference {
    /*
     * App context to work on
     */
    private Context context;

    /*
     * Preference key
     */
    private static final String prefKey = "tspPrefs";

    /*
     * Whether or not to manually clearPreferences
     * If manual mode is enabled, this basically works like a normal SharedPreferences
     */
    private boolean manualMode = false;

    /*
     * Timestamp key
     */
    private final String timeStampKey = "timeStamp";

    /*
     * Constructor, accepts only context
     */
    public TemporarySharedPreference(Context context) {
        this.context = context;

        clearPreferences();
    }

    /*
     * Constructor, accepts context and manual mode
     */
    public TemporarySharedPreference(Context context, boolean manualMode) {
        this.context = context;
        this.manualMode = manualMode;

        if (!manualMode) clearPreferences();
        else checkTimeStamps();
    }

    /*
     * Returns shared preference
     */
    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(prefKey, Context.MODE_PRIVATE);
    }

    /*
     * Returns shared preference editor
     */
    public SharedPreferences.Editor getSharedPreferenceEditor() {
        applyTimeStamp();
        return getSharedPreferences().edit();
    }

    /*
     * To clear the sharedPreferences.
     * By default, sharedPreferences will be cleared on newInstance
     */
    public void clearPreferences() {
        getSharedPreferenceEditor().clear().apply();
    }

    public static void clearPreferences(Context context) {
        context.getSharedPreferences(prefKey, Context.MODE_PRIVATE).edit().clear().apply();
    }

    /*
     * Will get date in YYYYMMDD format, to be used in attaching timestamp in preference
     */
    private int getSimpleDate() {
        return Integer.parseInt(new SimpleDateFormat("yyyyMMdd",Locale.ENGLISH).format(new Date()));
    }

    /*
     * Will apply timestamp to preference
     */
    private void applyTimeStamp() {
        getSharedPreferences().edit().putInt(timeStampKey, getSimpleDate()).apply();
    }

    /*
     * Will check timestamps, and will clear preferences if its old
     */
    private void checkTimeStamps() {
        if (getSharedPreferences().getInt(timeStampKey, 0) < getSimpleDate())
            clearPreferences();
    }
}
