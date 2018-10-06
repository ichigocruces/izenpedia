package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

public class PropertiesReader {

    private static PropertiesReader propertiesReader = new PropertiesReader();

    private static Context context;

    private static final String APP_FILENAME = "app.properties";
    private static final String URL_GOOGLEPLAY = "url.googleplay.izenpedia";
    private static final String INIGO_LINKEDIN = "inigo.linkedin";
    private static final String INIGO_GITHUB = "inigo.github";
    private static final String IBAI_LINKEDIN = "ibai.linkedin";
    private static final String IBAI_GITHUB = "ibai.github";
    private static final String URL_IZENPEDIA_ES = "url.izenpedia.es";
    private static final String URL_IZENPEDIA_EU = "url.izenpedia.eu";



    private PropertiesReader() {
        super();
    }

    public static PropertiesReader getInstance(Context context) {
        if (PropertiesReader.context == null) {
            PropertiesReader.context = context;
        }
        return propertiesReader;
    }

    public String getProperty(String key) {
        if(Utils.isBlank(key)){
            return null;
        }
        String value = null;
        try {
            InputStream is = context.getAssets().open(APP_FILENAME);
            Properties props = new Properties();
            props.load(is);

            value = props.getProperty(key);

            is.close();

        } catch (IOException ioEx) {
            Log.e("PropertiesReader", "getProperty: Error al recuperar las propiedades", ioEx);
        }

        return value;
    }

    public String getUrlGoogleplay() {
        return propertiesReader.getProperty(URL_GOOGLEPLAY);
    }

    public String getInigoLinkedin() {
        return propertiesReader.getProperty(INIGO_LINKEDIN);
    }

    public String getInigoGithub() {
        return propertiesReader.getProperty(INIGO_GITHUB);
    }

    public String getIbaiLinkedin() {
        return propertiesReader.getProperty(IBAI_LINKEDIN);
    }

    public String getIbaiGithub() {
        return propertiesReader.getProperty(IBAI_GITHUB);
    }

    public String getUrlIzenpedia(Locale locale) {
        if(Constants.LOCALE_ES.equals(locale)){
            return propertiesReader.getProperty(URL_IZENPEDIA_ES);
        }
        return propertiesReader.getProperty(URL_IZENPEDIA_EU);
    }

}
