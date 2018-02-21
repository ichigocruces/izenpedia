package eus.arabyte.android.izenpedia.bd.contracts;

import android.provider.BaseColumns;

/**
 * Created by ichigo on 11/01/18.
 */
public abstract class IzenaEskema implements BaseColumns {
    public static final String TABLE_NAME ="izenak";

    public static final String VIEW_EMAKUMEAK ="V_ZERRENDA_EMAKUMEAK";
    public static final String VIEW_GIZONAK ="V_ZERRENDA_GIZONAK";
    public static final String VIEW_GOGOKOAK ="V_ZERRENDA_GOGOKOAK";
    public static final String VIEW_DATUAK="V_DATUAK";
    public static final String VIEW_EUSTAT_EMAKUMEAK="V_EUSTAT_EMAKUMEAK";
    public static final String VIEW_EUSTAT_GIZONAK="V_EUSTAT_GIZONAK";

    public static final String ID = "_id";
    public static final String IZENA = "izena";
    public static final String SEXUA = "sexua";
    public static final String AZALPENA_EU = "azalpena_eu";
    public static final String AZALPENA_ES = "azalpena_es";
    public static final String GOGOKOA = "gogokoa";
    public static final String BATAZBESTE = "batazbeste";
    public static final String EUSTAT = "eustat";
    public static final String TOTAL = "total";


}
