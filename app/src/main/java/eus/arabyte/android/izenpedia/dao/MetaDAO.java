package eus.arabyte.android.izenpedia.dao;

import java.util.List;
import java.util.Map;

import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Meta;
import eus.arabyte.android.izenpedia.utils.LurraldeHistoriko;

/**
 * Created by ichigo on 14/01/18.
 */

public interface MetaDAO {

    /**
     * Returns a list of 'Meta'
     *
     * @param izena Izena
     *
     * @return List<Meta>
     */
    List<Meta> getListMeta(Izena izena);

    /**
     * Returns a map of list of 'Meta'
     *
     * @param izena Izena
     *
     * @return Map<LurraldeHistoriko, List<Meta>>
     */
    Map<LurraldeHistoriko,List<Meta>> getMapListLHMeta(Izena izena);
}
