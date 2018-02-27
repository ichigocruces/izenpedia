package eus.arabyte.android.izenpedia.utils;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ichigo on 15/02/18.
 */

public enum LurraldeHistoriko {
    BIZKAIA (48, "lh_bizkaia", "grapho_bizkaia"),
    ARABA (1, "lh_araba", "grapho_araba"),
    GIPUZKOA(20, "lh_gipuzkoa", "grapho_gipuzkoa"),
    NAFARROA(31, "lh_nafarroa", "grapho_navarra");

    private final int id;
    private final String label;
    private final String color;

    private static Map<Integer, LurraldeHistoriko> map = new HashMap<>();

    static{
        for (LurraldeHistoriko lhEnum : LurraldeHistoriko.values()) {
            map.put(lhEnum.id, lhEnum);
        }
    }


    LurraldeHistoriko(int id, String label, String color) {
        this.id = id;
        this.label = label;
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public String getColor() {
        return color;
    }

    public static LurraldeHistoriko valueOf(int id) {
        return map.get(id);
    }
}