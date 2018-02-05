package eus.arabyte.android.izenpedia.dao;

import java.util.List;

import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Izenkidea;

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
