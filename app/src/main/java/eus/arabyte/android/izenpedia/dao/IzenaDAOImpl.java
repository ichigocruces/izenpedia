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
     * @return List<Izena>
     */
    @Override
    public List<Izena> getPopularIzenak() {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT _ID, IZENA, SEXUA, AZALPENA_EU, AZALPENA_ES, GOGOKOA
        FROM IZENAK
        WHERE EUSTAT <= '100'
        ORDER BY EUSTAT;
         */

        sb.append(SELECT)
                .append(IzenaEskema.ID).append(COMA)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.AZALPENA_EU).append(COMA)
                .append(IzenaEskema.AZALPENA_ES).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.EUSTAT);

        sb.append(FROM)
                .append(IzenaEskema.TABLE_NAME);

        sb.append(WHERE)
                .append(IzenaEskema.EUSTAT).append(MENOR_IGUAL).append(Constants.POPULAR_MAX);

        sb.append(ORDER_BY).append(IzenaEskema.EUSTAT);

        return this.getListIzenak(sb.toString());
    }

    /**
     * Returns a list of the 'Izena' by gender
     *
     * @param sex String
     *
     * @return List<Izena>
     */
    @Override
    public List<Izena> getListIzenakByGender(String sex) {
        StringBuilder sb = new StringBuilder();
        /*
        SELECT _ID, IZENA, SEXUA, AZALPENA_EU, AZALPENA_ES, GOGOKOA
        FROM IZENAK
        WHERE EUSTAT <= '100'
        ORDER BY EUSTAT;
         */

        sb.append(SELECT)
                .append(IzenaEskema.ID).append(COMA)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.AZALPENA_EU).append(COMA)
                .append(IzenaEskema.AZALPENA_ES).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.EUSTAT);

        sb.append(FROM)
                .append(IzenaEskema.TABLE_NAME);

        sb.append(WHERE)
                .append(IzenaEskema.SEXUA).append(IGUAL)
                .append(COMILLA_SIMPLE).append(sex).append(COMILLA_SIMPLE);

        sb.append(ORDER_BY).append(IzenaEskema.IZENA);

        return this.getListIzenak(sb.toString());
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
        SELECT _ID, IZENA, SEXUA, AZALPENA_EU, AZALPENA_ES, GOGOKOA
        FROM IZENAK
        WHERE GOGOKOA = '1':
         */

        sb.append(SELECT)
                .append(IzenaEskema.ID).append(COMA)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.AZALPENA_EU).append(COMA)
                .append(IzenaEskema.AZALPENA_ES).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.EUSTAT);

        sb.append(FROM)
                .append(IzenaEskema.TABLE_NAME);

        sb.append(WHERE)
                .append(IzenaEskema.GOGOKOA).append(IGUAL)
                .append(COMILLA_SIMPLE).append(FAV_SI).append(COMILLA_SIMPLE);

        sb.append(ORDER_BY).append(IzenaEskema.IZENA);

        return this.getListIzenak(sb.toString());
    }

    /**
     * Returns the object 'Izena' of the 'name' given
     *
     * @param id Integer
     *
     * @return Izena
     */
    @Override
    public Izena getIzena(Integer id) {
        StringBuilder sb = new StringBuilder();

        /*
        SELECT _ID, IZENA, SEXUA, AZALPENA_EU, AZALPENA_ES, GOGOKOA
        FROM IZENAK
        WHERE IZENA = ?:
         */

        sb.append(SELECT)
                .append(IzenaEskema.ID).append(COMA)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.SEXUA).append(COMA)
                .append(IzenaEskema.AZALPENA_EU).append(COMA)
                .append(IzenaEskema.AZALPENA_ES).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.EUSTAT);

        sb.append(FROM)
                .append(IzenaEskema.TABLE_NAME);

        sb.append(WHERE)
                .append(IzenaEskema.ID).append(IGUAL)
                .append(COMILLA_SIMPLE).append(id).append(COMILLA_SIMPLE);

        List<Izena> izenaList = this.getListIzenak(sb.toString());
        if(izenaList!=null && !izenaList.isEmpty()){
            return izenaList.get(0);
        }

        return null;
    }

    /**
     * Update de value of 'gogokoa' for the 'izena' given
     *
     * @param izena Izena
     */
    @Override
    public void updateIzenaFav(Izena izena){
        SQLiteDatabase bd = getWritableDatabase();
        StringBuilder sb = new StringBuilder();

        sb.append(UPDATE).append(IzenaEskema.TABLE_NAME).append(ESPACIO);
        sb.append(SET).append(IzenaEskema.GOGOKOA).append(IGUAL).append(izena.getGogokoa());
        sb.append(WHERE).append(IzenaEskema._ID).append(IGUAL).append(izena.getId());

        bd.execSQL(sb.toString());
    }

    private List<Izena> getListIzenak(String sql){
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setId(Integer.valueOf(cursor.getInt(0)));
                izena.setIzena(cursor.getString(1));
                izena.setSexua(cursor.getString(2));
                izena.setAzalpenaEu(cursor.getString(3));
                izena.setAzalpenaEs(cursor.getString(4));
                //Gogokoa
                if (cursor.getString(5) == null) {
                    izena.setGogokoa(0);
                }
                else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(5)));
                }

                izena.setEustat(cursor.getInt(6));

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        return izenaList;
    }

}
