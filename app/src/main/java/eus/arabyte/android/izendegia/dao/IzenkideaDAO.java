package eus.arabyte.android.izendegia.dao;

import java.util.List;

import eus.arabyte.android.izendegia.model.Izena;
import eus.arabyte.android.izendegia.model.Izenkidea;

/**
 * Created by ichigo on 14/01/18.
 */

public interface IzenkideaDAO {

    /**
     * Returns a list of 'Izenkidea'
     *
     * @param izena Izena
     *
     * @return List<Izenkidea>
     */
    List<Izenkidea> getListIzenkidea(Izena izena);

}
