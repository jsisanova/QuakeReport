package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    // Inside the SettingsActivity file, create a EarthquakePreferenceFragment that extends PreferenceFragment
    // Classes that implement Preference.OnPreferenceChangeListener interface are setup to listen for any Preference changes made by the user.
    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        // Setup a preference from settings_main.xml file the user can edit
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            // Update the preference summary (the UI) when the SettingsActivity is launched
            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);

            // Update with orderBy preference
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);
        }

        @Override
        // Update the displayed preference summary after it has been changed
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            // Properly update the summary of a ListPreference (using the label, instead of the key):
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }

        // Helper method (used in onCreate()), in order to update the preference summary when the settings activity is launched
        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the current EarthquakePreferenceFragment instance to listen for changes to the preference we pass in
            preference.setOnPreferenceChangeListener(this);
            // Read the current value of the preference stored in the SharedPreferences on the device,
            // and display that in the preference summary (so that the user can see the current value of the preference)
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }
    }
}