package eus.arabyte.android.izenpedia.model;

import java.io.Serializable;

import eus.arabyte.android.izenpedia.utils.Hizkuntza;

/**
 * Created by ichigo on 5/02/18.
 */

public class Izenkidea implements Serializable {

    private String izena;
    private Hizkuntza hizkuntza;
    private String izenkidea;

    public Izenkidea() {
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public Hizkuntza getHizkuntza() {
        return hizkuntza;
    }

    public void setHizkuntza(Hizkuntza hizkuntza) {
        this.hizkuntza = hizkuntza;
    }

    public String getIzenkidea() {
        return izenkidea;
    }

    public void setIzenkidea(String izenkidea) {
        this.izenkidea = izenkidea;
    }

    @Override
    public String toString() {
        return "Izenkidea{" +
                "izena='" + izena + '\'' +
                ", hizkuntza=" + hizkuntza +
                ", izenkidea='" + izenkidea + '\'' +
                '}';
    }
}
