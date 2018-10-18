package eus.arabyte.android.izendegia.bd.contracts;

import android.provider.BaseColumns;

/**
 * Created by ichigo on 11/01/18.
 */
public abstract class MetaEskema implements BaseColumns {
    public static final String TABLE_NAME ="meta";

    public static final String VIEW_NAME ="v_meta";

    public static final String IZENA = "izena";
    public static final String LH = "lh";
    public static final String URTEA = "urtea";
    public static final String ZENBAT = "zenbat";

}
