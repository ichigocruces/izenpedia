package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleManager {

    /**
     * Sets the language of the application
     *
     * @param context Context
     * @param lang String
     */
    public static void setLocale(Context context, String lang) {
        Locale myLocale = new Locale(lang);
        Locale.setDefault(myLocale);

        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

    }
}
