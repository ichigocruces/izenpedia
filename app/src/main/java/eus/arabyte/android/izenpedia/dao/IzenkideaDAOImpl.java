package eus.arabyte.android.izenpedia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.bd.contracts.IzenkideaEskema;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Izenkidea;
import eus.arabyte.android.izenpedia.utils.Hizkuntza;

/**
 * Created by ichigo on 14/01/18.
 */

public class IzenkideaDAOImpl extends BasicDAO implements IzenkideaDAO {


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public IzenkideaDAOImpl(Context context) {
        super(context);
    }


    @Override
    public List<Izenkidea> getListIzenkidea(Izena izena) {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT IZENA, HIZKUNTZA, IZENKIDEA
        FROM IZENKIDEAK
        WHERE IZENA = '?'
        ORDER BY HIZKUNTZA;
         */

        sb.append(SELECT)
                .append(IzenkideaEskema.IZENA).append(COMA)
                .append(IzenkideaEskema.HIZKUNTZA).append(COMA)
                .append(IzenkideaEskema.IZKENKIDEA);

        sb.append(FROM)
                .append(IzenkideaEskema.TABLE_NAME);

        sb.append(WHERE)
                .append(IzenkideaEskema.IZENA)
                .append(EQUAL).append(SIMPLE_QUOTA).append(izena.getIzena()).append(SIMPLE_QUOTA);

        sb.append(ORDER_BY).append(IzenkideaEskema.HIZKUNTZA);

        return this.selecetListIzenkidea(sb.toString());
    }

    private List<Izenkidea> selecetListIzenkidea(String sql) {
        List<Izenkidea> izenkideaList = new ArrayList<Izenkidea>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izenkidea izenkidea = new Izenkidea();
                izenkidea.setIzena(cursor.getString(0));

                String hizkuntza = cursor.getString(1);
                izenkidea.setHizkuntza(Hizkuntza.valueOf(hizkuntza.toUpperCase()));

                izenkidea.setIzenkidea(cursor.getString(2));

                izenkideaList.add(izenkidea);
            } while (cursor.moveToNext());
        }

        return izenkideaList;
    }
}
