package com.corphish.tsp;

import android.content.Context;
import android.content.SharedPreferences;

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
    private final String prefKey = "tspPrefs";

    /*
     * Whether or not to manually clearPreferences
     * If manual mode is enabled, this basically works like a normal SharedPreferences
     */
    private boolean manualMode = false;

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
        return getSharedPreferences().edit();
    }

    /*
     * To clear the sharedPreferences.
     * By default, sharedPreferences will be cleared on newInstance
     */
    public void clearPreferences() {
        getSharedPreferenceEditor().clear().apply();
    }
}
