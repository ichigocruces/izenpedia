package eus.arabyte.android.izendegia.dao;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import eus.arabyte.android.izendegia.model.Izena;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
@SdkSuppress(minSdkVersion = 21)
public class IzenaDAOTest {

    private Context context;
    private IzenaDAO izenaDAO;
    private Izena izena;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        izenaDAO = new IzenaDAOImpl(context);
        //TODO: anyadir un nombre de prueba en la BBDD

    }

    @Test
    public void getPopularIzenakTest(){
        assertEquals(null, izenaDAO.getPopularIzenak(null));
        assertEquals(null, izenaDAO.getPopularIzenak(""));
        assertEquals(null, izenaDAO.getPopularIzenak("NULL"));

        //TODO: faltan de hacer los test para cuando se busca hombre y mujer

    }

    @Test
    public void getListIzenakByGenderTest(){
        assertEquals(null, izenaDAO.getListIzenakByGender(null));
        assertEquals(null, izenaDAO.getListIzenakByGender(""));
        assertEquals(null, izenaDAO.getListIzenakByGender("NULL"));

        //TODO: faltan de hacer los test para cuando se busca hombre y mujer
    }

    @Test
    public void getStartedIzenakTest(){
        //TODO: falta por anyadir el izena con el check de favorito y verificar que esta en la lista
    }


    @Test
    public void getIzenaTest(){
        assertEquals(null, izenaDAO.getIzena(null));
        assertEquals(null, izenaDAO.getIzena(""));
        assertEquals(null, izenaDAO.getIzena(" nombre no existe "));

        //TODO: falta por anyadir el izena con el check de favorito y verificar que se recupera
        assertEquals(null, izenaDAO.getIzena("Iker"));

    }

    @After
    public void beforeExit(){
        //TODO: eliminar el nombre anyadido para las pruebas
    }

}
