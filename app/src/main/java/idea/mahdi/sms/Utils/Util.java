package idea.mahdi.sms.Utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import idea.mahdi.sms.Application;

public class Util {
    public static String getShare(String key, String def) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Application.getInstance());
        return preferences.getString(key, def);
    }

    public static String getShare(String key) {
        return getShare(key, null);
    }

    public static void setShare(String key, String val) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(Application.getInstance()).edit();
        editor.putString(key, val);
        editor.apply();
    }
}
