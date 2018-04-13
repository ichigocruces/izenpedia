package eus.arabyte.android.izenpedia.model;

import java.io.Serializable;

/**
 * Created by illonabe on 23/08/2016.
 */
public class Izena implements Serializable{

    public Izena() {
    }

    private Integer id;
    private String izena, sexua, azalpenaEs, azalpenaEu;
    private Integer gogokoa;
    private Double batazbeste;
    private Integer eustat;
    private Double total;
    private Boolean meta;
    private Boolean azalpena;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setSexua(String sexua) {
        this.sexua = sexua;
    }

    public String getIzena() {
        return izena;
    }

    public String getSexua() {
        return sexua;
    }

    public String getAzalpenaEs() {
        return azalpenaEs;
    }

    public void setAzalpenaEs(String azalpenaEs) {
        this.azalpenaEs = azalpenaEs;
    }

    public String getAzalpenaEu() {
        return azalpenaEu;
    }

    public void setAzalpenaEu(String azalpenaEu) {
        this.azalpenaEu = azalpenaEu;
    }

    public Integer getGogokoa() {
        return gogokoa;
    }

    public void setGogokoa(Integer gogokoa) {
        this.gogokoa = gogokoa;
    }

    public Double getBatazbeste() {
        return batazbeste;
    }

    public void setBatazbeste(Double batazbeste) {
        this.batazbeste = batazbeste;
    }

    public Integer getEustat() {
        return eustat;
    }

    public void setEustat(Integer eustat) {
        this.eustat = eustat;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getMeta() {
        return meta;
    }

    public void setMeta(Boolean meta) {
        this.meta = meta;
    }

    public Boolean getAzalpena() {
        return azalpena;
    }

    public void setAzalpena(Boolean azalpena) {
        this.azalpena = azalpena;
    }

    @Override
    public String toString() {
        return "Izena{" +
                "id=" + id +
                ", izena='" + izena + '\'' +
                ", sexua='" + sexua + '\'' +
                ", azalpenaEs='" + azalpenaEs + '\'' +
                ", azalpenaEu='" + azalpenaEu + '\'' +
                ", gogokoa=" + gogokoa +
                ", batazbeste=" + batazbeste +
                ", eustat=" + eustat +
                ", total=" + total +
                ", meta=" + meta +
                ", azalpena=" + azalpena +
                '}';
    }
}
