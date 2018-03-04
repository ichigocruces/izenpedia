package eus.arabyte.android.izenpedia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import eus.arabyte.android.izenpedia.bd.contracts.GogokoaEskema;
import eus.arabyte.android.izenpedia.model.Izena;

/**
 * Created by ichigo on 8/02/18.
 */

public class GogokoaDAOImpl extends BasicDAO implements GogokoaDAO {

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public GogokoaDAOImpl(Context context) {
        super(context);
    }

    @Override
    public void addGogokoa(Izena izena) {
        SQLiteDatabase bd = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(GogokoaEskema.IZENA, izena.getIzena());

        bd.insert(GogokoaEskema.TABLE_NAME,null, contentValues);
    }

    @Override
    public void removeGogokoa(Izena izena) {

        SQLiteDatabase bd = getWritableDatabase();

        //where clause
        StringBuilder sb = new StringBuilder();
        sb.append(GogokoaEskema.IZENA).append(EQUAL).append(QUESTION_MARK);

        //params
        String[] params = new String[1];
        params[0] = izena.getIzena();

        bd.delete(GogokoaEskema.TABLE_NAME, sb.toString(), params);
    }
}
