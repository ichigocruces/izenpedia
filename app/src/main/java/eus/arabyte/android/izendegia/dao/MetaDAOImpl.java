package eus.arabyte.android.izendegia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import eus.arabyte.android.izendegia.bd.contracts.MetaEskema;
import eus.arabyte.android.izendegia.model.Izena;
import eus.arabyte.android.izendegia.model.Meta;
import eus.arabyte.android.izendegia.utils.LurraldeHistoriko;

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
/*
        sb.append("SELECT IZENA, URTEA, sum(ZENBAT) " +
                "        FROM V_META " +
                "        WHERE IZENA = '" + izena.getIzena() + "'" +
                "        GROUP BY IZENA, URTEA");
*/
        sb.append(SELECT)
                .append(MetaEskema.IZENA).append(COMA)
                .append(MetaEskema.URTEA).append(COMA)
                .append(SUM).append(PARENTHESIS_OPEN)
                .append(MetaEskema.ZENBAT).append(PARENTHESIS_CLOSE);

        sb.append(FROM)
                .append(MetaEskema.VIEW_NAME);

        sb.append(WHERE)
                .append(MetaEskema.IZENA)
                .append(EQUAL).append(SIMPLE_QUOTA).append(izena.getIzena()).append(SIMPLE_QUOTA);

        sb.append(GROUP_BY).append(MetaEskema.IZENA).append(COMA).append(MetaEskema.URTEA);

        return this.selecetListMeta(sb.toString());
    }

    @Override
    public Map<LurraldeHistoriko, List<Meta>> getMapListLHMeta(Izena izena) {
        StringBuilder sb = new StringBuilder();

        sb.append(SELECT)
                .append(MetaEskema.IZENA).append(COMA)
                .append(MetaEskema.LH).append(COMA)
                .append(MetaEskema.URTEA).append(COMA)
                .append(MetaEskema.ZENBAT);

        sb.append(FROM)
                .append(MetaEskema.VIEW_NAME);

        sb.append(WHERE)
                .append(MetaEskema.IZENA)
                .append(EQUAL).append(SIMPLE_QUOTA).append(izena.getIzena()).append(SIMPLE_QUOTA);

        sb.append(ORDER_BY).append(MetaEskema.LH).append(COMA).append(MetaEskema.URTEA);

        return this.selectMapMeta(sb.toString());

    }

    private Map<LurraldeHistoriko, List<Meta>> selectMapMeta(String sql) {
        Map<LurraldeHistoriko, List<Meta>> mapList = new LinkedHashMap<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int lhInt = cursor.getInt(1);
                LurraldeHistoriko lh = LurraldeHistoriko.valueOf(lhInt);

                List<Meta> metaList = mapList.get(lh);

                if (metaList == null) {
                    metaList = new ArrayList<>();
                    mapList.put(lh, metaList);
                }

                Meta meta = new Meta();
                meta.setIzena(cursor.getString(0));

                meta.setLh(lh);
                meta.setUrtea(cursor.getInt(2));
                meta.setZenbat(cursor.getInt(3));

                metaList.add(meta);
            } while (cursor.moveToNext());
        }

        return mapList;
    }


}
