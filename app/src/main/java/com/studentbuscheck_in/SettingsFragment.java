package com.studentbuscheck_in;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;
    private Preference defaultEmail;
    private String defaultDistrict;
    private boolean rememberDistrictInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Preference rememberCheckBox = findPreference("pref_remember_district_info");
        rememberCheckBox.setEnabled(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        rememberDistrictInfo = prefs.getBoolean("pref_remember_district_info", true);

        this.setDefaultPreferences(rememberDistrictInfo);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    private void setDefaultPreferences (boolean rememberDefaults) {
        Preference defaultInfo = findPreference("pref_remember_district_info");
        if (rememberDefaults) {
            defaultInfo.setDefaultValue(false);
        } else {
            defaultInfo.setDefaultValue(true);

        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("pref_remember_district_info")){
            rememberDistrictInfo = prefs.getBoolean(key,true);
        }
        this.setDefaultPreferences(rememberDistrictInfo);

    }

    @Override
    public void onPause() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();

    }


}