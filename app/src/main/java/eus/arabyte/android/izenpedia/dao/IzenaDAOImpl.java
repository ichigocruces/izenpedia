package eus.arabyte.android.izenpedia.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.bd.contracts.IzenaEskema;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.Utils;

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
        SELECT IZENA, GOGOKOA, META, EUSTAT
        FROM V_EUSTAT_EMAKUMEAK/V_EUSTAT_GIZONAK;
         */
        sb.append(SELECT).append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.META).append(COMA)
                .append(IzenaEskema.EUSTAT).append(COMA)
                .append(IzenaEskema.AZALPENA);

        String view = IzenaEskema.VIEW_EUSTAT_EMAKUMEAK;
        if (Constants.GIZONA.equals(gender)) {
            view = IzenaEskema.VIEW_EUSTAT_GIZONAK;
        }
        sb.append(FROM)
                .append(view);

        return this.selecetListIzenak(sb.toString(), gender);
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
        SELECT IZENA, GOGOKOA, META, EUSTAT
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
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.META).append(COMA)
                .append(IzenaEskema.EUSTAT).append(COMA)
                .append(IzenaEskema.AZALPENA);

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
        SELECT IZENA, GOGOKOA, META, EUSTAT, SEXUA
        FROM IZENAK
        WHERE GOGOKOA = '1'
        ORDER BY IZENA:
         */

        sb.append(SELECT)
                .append(IzenaEskema.IZENA).append(COMA)
                .append(IzenaEskema.GOGOKOA).append(COMA)
                .append(IzenaEskema.META).append(COMA)
                .append(IzenaEskema.EUSTAT).append(COMA)
                .append(IzenaEskema.AZALPENA).append(COMA)
                .append(IzenaEskema.SEXUA);

        sb.append(FROM)
                .append(IzenaEskema.VIEW_GOGOKOAK);

        sb.append(WHERE)
                .append(IzenaEskema.GOGOKOA).append(EQUAL)
                .append(SIMPLE_QUOTA).append(Constants.FAV_SI).append(SIMPLE_QUOTA);

        sb.append(ORDER_BY).append(IzenaEskema.IZENA);

        return this.selecetListIzenak(sb.toString(), null);
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
                .append(IzenaEskema.META).append(COMA)
                .append(IzenaEskema.AZALPENA).append(COMA)
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

                // eustat
                String eustat = cursor.getString(6);
                izena.setEustat(Utils.isBlank(eustat) ? null:Integer.parseInt(eustat));

                // meta
                String meta= cursor.getString(7);
                izena.setMeta(Utils.isBlank(meta) ? null: Utils.getBooleanFromInt(meta));

                // azalpena
                String azalpena= cursor.getString(8);
                izena.setAzalpena(Utils.isBlank(azalpena) ? null: Utils.getBooleanFromInt(azalpena));

                izena.setTotal(cursor.getString(9) != null ? Integer.valueOf(cursor.getString(9)) : null);

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        if (izenaList != null && !izenaList.isEmpty()) {
            return izenaList.get(0);
        }

        return null;
    }


    private List<Izena> selecetListIzenak(String sql, String sexua) {
        List<Izena> izenaList = new ArrayList<Izena>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        //SELECT IZENA, GOGOKOA, META, EUSTAT, SEXUA

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Izena izena = new Izena();
                izena.setIzena(cursor.getString(0));

                // gogokoa
                if (cursor.getString(1) == null) {
                    izena.setGogokoa(Constants.FAV_NO);
                } else {
                    izena.setGogokoa(Integer.valueOf(cursor.getString(1)));
                }

                // meta
                String meta= cursor.getString(2);
                izena.setMeta(Utils.isBlank(meta) ? null: Utils.getBooleanFromInt(meta));

                // eustat
                String eustat = cursor.getString(3);
                izena.setEustat(Utils.isBlank(eustat) ? null:Integer.parseInt(eustat));

                // meta
                String azalpena= cursor.getString(4);
                izena.setAzalpena(Utils.isBlank(azalpena) ? null: Utils.getBooleanFromInt(azalpena));

                // sexua
                if(Utils.isBlank(sexua)){
                    izena.setSexua(cursor.getString(5));
                }else{
                    izena.setSexua(sexua);
                }

                izenaList.add(izena);
            } while (cursor.moveToNext());
        }

        return izenaList;
    }

}
