package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by illonabe on 11/08/2016.
 */
public class Preferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode

    // Shared preferences file name
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public static final String HIZKUNTZA = "Hizkuntza";

    // Singleton
    private static Preferences preferences = null;

    private Preferences(Context context) {
        this._context = context;
        //pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        editor = pref.edit();
    }

    public static Preferences getInstance(Context context){
        if(preferences==null){
            preferences = new Preferences(context);
        }

        return preferences;
    }

    //FirstTimeLaunch
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //Hizkuntza
    public void setHizkuntza(String hizkuntza) {
        editor.putString(HIZKUNTZA, hizkuntza);
        editor.commit();
    }

    public String getHizkuntza() {
        return pref.getString(HIZKUNTZA,null);
    }

}
