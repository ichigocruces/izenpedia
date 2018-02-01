package eus.arabyte.android.izenpedia.dao;

import android.content.Context;

import eus.arabyte.android.izenpedia.bd.Constants;
import eus.arabyte.android.izenpedia.bd.DataBaseHelper;

/**
 * Created by ichigo on 14/01/18.
 */

public class BasicDAO extends DataBaseHelper implements Constants {

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public BasicDAO(Context context) {
        super(context);
    }
}
