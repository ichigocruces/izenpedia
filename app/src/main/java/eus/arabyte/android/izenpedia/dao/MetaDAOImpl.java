package eus.arabyte.android.izenpedia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.bd.contracts.MetaEskema;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Meta;

/**
 * Created by ichigo on 14/01/18.
 */

public class MetaDAOImpl extends BasicDAO implements MetaDAO {


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public MetaDAOImpl(Context context) {
        super(context);
    }


    private List<Meta> selecetListMeta(String sql) {
        List<Meta> metaList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Meta meta = new Meta();
                meta.setIzena(cursor.getString(0));

                //FIXME: discomment when data is OK
//                String lh = cursor.getString(1);
//                meta.setLh(LurraldeHistoriko.valueOf(lh));

                meta.setUrtea(cursor.getInt(1));
                meta.setZenbat(cursor.getInt(2));

                metaList.add(meta);
            } while (cursor.moveToNext());
        }

        return metaList;
    }

    @Override
    public List<Meta> getListMeta(Izena izena) {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT IZENA, URTEA, sum(ZENBAT)
        FROM V_META
        WHERE IZENA = ?
        GROUP BY IZENA, URTEA
         */

        sb.append("SELECT IZENA, URTEA, sum(ZENBAT) " +
                "        FROM V_META " +
                "        WHERE IZENA = '" + izena.getIzena() + "'" +
                "        GROUP BY IZENA, URTEA");

//        sb.append(SELECT)
//                .append(MetaEskema.IZENA).append(COMA)
//                .append(MetaEskema.LH).append(COMA)
//                .append(MetaEskema.URTEA).append(COMA)
//                .append(MetaEskema.ZENBAT);
//
//        sb.append(FROM)
//                .append(MetaEskema.VIEW_NAME);
//
//        sb.append(WHERE)
//                .append(MetaEskema.IZENA)
//                .append(IGUAL).append(COMILLA_SIMPLE).append(izena.getIzena()).append(COMILLA_SIMPLE);
//
//        sb.append(ORDER_BY).append(MetaEskema.LH).append(COMA).append(MetaEskema.URTEA);

        return this.selecetListMeta(sb.toString());
    }
}
