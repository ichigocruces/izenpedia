package eus.arabyte.android.izenpedia.dao;

import java.util.List;

import eus.arabyte.android.izenpedia.model.Izena;

/**
 * Created by ichigo on 14/01/18.
 */

public interface IzenaDAO {

    /**
     * Returns a list of the popular 'Izena'
     *
     * @return List<Izena>
     */
    List<Izena> getPopularIzenak();

    /**
     * Returns a list of the 'Izena' by gender
     *
     * @param sex String
     *
     * @return List<Izena>
     */
    List<Izena> getListIzenakByGender(String sex);

    /**
     * Returns a list of the favorites 'Izena'
     *
     * @return List<Izena>
     */
    List<Izena> getStartedIzenak();

    /**
     * Returns the object 'Izena' of the 'name' given
     *
     * @param name String
     *
     * @return Izena
     */
    Izena getIzena(String name);

}
