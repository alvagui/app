package omicron.app;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;


public class SettingsActivity extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public void onBuildHeaders(java.util.List<Header> target) {
    	loadHeadersFromResource(R.xml.pref_headers, target);
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            String settings = getArguments().getString("settings");
            if ("notifications".equals(settings)) {
                addPreferencesFromResource(R.xml.pref_notification);
            } else if ("data_sync".equals(settings)) {
                addPreferencesFromResource(R.xml.pref_data_sync);
            } else if ("general".equals(settings)) {
                addPreferencesFromResource(R.xml.pref_general);
            }
        }

    }
    
    	@Override
       protected boolean isValidFragment (String fragmentName) {

         return SettingsFragment.class.getName().equals(fragmentName);

       }
    
}
