package eus.arabyte.android.izenpedia.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by illonabe on 22/08/2016.
 */
public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "izendegia.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

}