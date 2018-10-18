package eus.arabyte.android.izendegia.model;

import java.io.Serializable;

import eus.arabyte.android.izendegia.utils.LurraldeHistoriko;

/**
 * Created by illonabe on 23/08/2016.
 */
public class Meta implements Serializable{

    private String izena;
    private LurraldeHistoriko lh;
    private Integer urtea;
    private Integer zenbat;

    public Meta() {
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public LurraldeHistoriko getLh() {
        return lh;
    }

    public void setLh(LurraldeHistoriko lh) {
        this.lh = lh;
    }

    public Integer getUrtea() {
        return urtea;
    }

    public void setUrtea(Integer urtea) {
        this.urtea = urtea;
    }

    public Integer getZenbat() {
        return zenbat;
    }

    public void setZenbat(Integer zenbat) {
        this.zenbat = zenbat;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "izena='" + izena + '\'' +
                ", lh=" + lh +
                ", urtea=" + urtea +
                ", zenbat=" + zenbat +
                '}';
    }
}
