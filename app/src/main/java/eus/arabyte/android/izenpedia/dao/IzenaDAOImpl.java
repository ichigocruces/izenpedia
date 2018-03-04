package eus.arabyte.android.izenpedia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.bd.contracts.IzenaEskema;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.Constants;

/**
 * Created by ichigo on 14/01/18.
 */

public class IzenaDAOImpl extends BasicDAO implements IzenaDAO {


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public IzenaDAOImpl(Context context) {
        super(context);
    }

    /**
     * Returns a list of the popular 'Izena'
     *
     * @param gender String
     *
     * @return List<Izena>
     */
    @Override
    public List<Izena> getPopularIzenak(String gender) {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT IZENA, EUSTAT, GOGOKOA
        FROM V_EUSTAT_EMAKUMEAK/V_EUSTAT_GIZONAK;
         */

        sb.append(SELECT)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.EUSTAT).append(COMA)
                .append(IzenaEskema.GOGOKOA);

        String view = IzenaEskema.VIEW_EUSTAT_EMAKUMEAK;
        if (Constants.GIZONA.equals(gender)) {
            view = IzenaEskema.VIEW_EUSTAT_GIZONAK;
        }
        sb.append(FROM)
                .append(view);

        return this.selecetPopularListIzenak(sb.toString());
    }

    /**
     * Returns a list of the 'Izena' by gender
     *
     * @param sex String
     * @return List<Izena>
     */
    @Override
    public List<Izena> getListIzenakByGender(String sex) {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT IZENA, GOGOKOA
        FROM IZENAK
        ORDER BY IZENA;
         */

        String tableName = IzenaEskema.TABLE_NAME;

        if (sex != null && !"".equals(sex.trim())) {
            if (Constants.EMAKUME.equals(sex)) {
                tableName = IzenaEskema.VIEW_EMAKUMEAK;
            } else {
                tableName = IzenaEskema.VIEW_GIZONAK;
            }

        }

        sb.append(SELECT)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.GOGOKOA);

        sb.append(FROM)
                .append(tableName);

        sb.append(ORDER_BY).append(IzenaEskema.IZENA);

        return this.selecetListIzenak(sb.toString(), sex);
    }

    /**
     * Returns a list of the favorites 'Izena'
     *
     * @return List<Izena>
     */
    @Override
    public List<Izena> getStartedIzenak() {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT IZENA, SEXUA, GOGOKOA
        FROM IZENAK
        WHERE GOGOKOA = '1'
        ORDER BY IZENA:
         */

        sb.append(SELECT)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.GOGOKOA);

        sb.append(FROM)
                .append(IzenaEskema.VIEW_GOGOKOAK);

        sb.append(WHERE)
                .append(IzenaEskema.GOGOKOA).append(EQUAL)
                .append(SIMPLE_QUOTA).append(Constants.FAV_SI).append(SIMPLE_QUOTA);

        sb.append(ORDER_BY).append(IzenaEskema.IZENA);

        return this.selecetListIzenak(sb.toString());
    }

    /**
     * Returns the object 'Izena' of the 'name' given
     *
     * @param name String
     * @return Izena
     */
    @Override
    public Izena getIzena(String name) {
        StringBuilder sb = new StringBuilder();

        /*
        SELECT IZENA, SEXUA, AZALPENA_EU, AZALPENA_ES, GOGOKOA, Batazbeste, Eustat, Total
        FROM V_DATUAK
        WHERE IZENA = ?:
         */

        sb.append(SELECT)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.AZALPENA_EU).append(COMA)
                .append(IzenaEskema.AZALPENA_ES).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.BATAZBESTE).append(COMA)
                .append(IzenaEskema.EUSTAT).append(COMA)
                .append(IzenaEskema.TOTAL);

        sb.append(FROM)
                .append(IzenaEskema.VIEW_DATUAK);

        sb.append(WHERE)
                .append(IzenaEskema.IZENA).append(EQUAL)
                .append(SIMPLE_QUOTA).append(name).append(SIMPLE_QUOTA);

        return this.selecetIzenak(sb.toString());
    }

    private Izena selecetIzenak(String sql) {
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setIzena(cursor.getString(0));
                izena.setSexua(cursor.getString(1));
                izena.setAzalpenaEu(cursor.getString(2));
                izena.setAzalpenaEs(cursor.getString(3));

                //Gogokoa
                if (cursor.getString(4) == null) {
                    izena.setGogokoa(0);
                } else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(4)));
                }

                izena.setBatazbeste(cursor.getString(5) != null ? Double.valueOf(cursor.getString(5)) : null);
                izena.setEustat(cursor.getString(6) != null ? Integer.valueOf(cursor.getString(6)) : null);
                izena.setTotal(cursor.getString(7) != null ? Double.valueOf(cursor.getString(7)) : null);

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        if (izenaList != null && !izenaList.isEmpty()) {
            return izenaList.get(0);
        }

        return null;
    }

    private List<Izena> selecetListIzenak(String sql) {
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setIzena(cursor.getString(0));
                izena.setSexua(cursor.getString(1));

                //Gogokoa
                if (cursor.getString(2) == null) {
                    izena.setGogokoa(0);
                } else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(2)));
                }

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        return izenaList;
    }

    private List<Izena> selecetListIzenak(String sql, String sexua) {
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setIzena(cursor.getString(0));
                izena.setSexua(sexua);

                //Gogokoa
                if (cursor.getString(1) == null) {
                    izena.setGogokoa(0);
                } else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(1)));
                }

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        return izenaList;
    }

    private List<Izena> selecetPopularListIzenak(String sql) {
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setIzena(cursor.getString(0));
                izena.setEustat(cursor.getInt(1));
                //Gogokoa
                if (cursor.getString(2) == null) {
                    izena.setGogokoa(0);
                } else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(2)));
                }

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        return izenaList;
    }

}
