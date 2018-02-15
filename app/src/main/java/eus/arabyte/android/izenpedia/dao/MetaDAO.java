package eus.arabyte.android.izenpedia.dao;

import java.util.List;

import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Meta;

/**
 * Created by ichigo on 14/01/18.
 */

public interface MetaDAO {

    /**
     * Returns a list of 'Meta'
     *
     * @param izena Izena
     *
     * @return List<Izenkidea>
     */
    List<Meta> getListMeta(Izena izena);

}
