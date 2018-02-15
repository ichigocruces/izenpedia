package eus.arabyte.android.izenpedia.utils;

/**
 * Created by ichigo on 15/02/18.
 */

public enum LurraldeHistoriko {
    //FIXME: use real IDs
    BIZKAIA (48, "lh_bizkaia"), ARABA (1, "lh_gipuzkoa"), GIPUZKOA(20, "lh_araba"), NAFARROA(0, "lh_nafarroa");

    private int id;
    private String label;

    LurraldeHistoriko(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}