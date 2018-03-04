package eus.arabyte.android.izenpedia.utils;

/**
 * Created by ichigo on 5/02/18.
 */

public enum Hizkuntza {
    ES("es"), EU("eu"), FR("fr");

    private String hizkuntza;

    Hizkuntza(String hizkuntza){
        this.hizkuntza = hizkuntza;
    }

    public String getHizkuntza (){
        return this.hizkuntza;
    }
}
