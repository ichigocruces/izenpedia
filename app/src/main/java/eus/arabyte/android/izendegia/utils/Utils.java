package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.NumberFormat;

/**
 * Created by ichigo on 13/02/18.
 */

public class Utils {

    private final static String EMPTY_STRING= "";
    private final static String NULL_STRING= "null";

    private final static String RESOURCES_COLOR="color";
    private final static String RESOURCES_STRING="string";

    private Utils(){
    }

    /**
     * Returns if the str is null or empty
     *
     * @param str String
     * @return boolean
     */
    public static boolean isBlank(String str){
        if(str==null){
            return true;
        }

        if(EMPTY_STRING.equals(str.trim())) {
            return true;
        }

        if(NULL_STRING.equalsIgnoreCase(str.trim())) {
            return true;
        }

        return false;
    }

    /**
     * Transforms a Double to a decimal format
     *
     * @param dbl Double
     * @return String format #.###.###.##0,00
     */
    public static String presentDoubleToDecimalFormat(Double dbl){
        String strDbl = String.valueOf(dbl);

        if(isBlank(strDbl)){
            return EMPTY_STRING;
        }else{
            return String.format(Constants.LOCALE_ES, "%,.2f", dbl);
        }
    }

    /**
     * Transforms a Integer to a String
     *
     * @param i Integer
     * @return String
     */
    public static String presentIntegerToString(Integer i){
        String strInt = String.valueOf(i);

        if(isBlank(strInt)){
            return EMPTY_STRING;
        }else{
            return NumberFormat.getNumberInstance(Constants.LOCALE_ES).format(i);
        }
    }

    /**
     * Returns a String from the resources file by the id
     *
     * @param resources Resources
     * @param packageName String
     * @param aString String
     *
     * @return String
     */
    public static String getStringResourceByName(Resources resources, String packageName, String aString) {
        if(resources==null || packageName == null || Utils.isBlank(aString)){
            throw new IllegalArgumentException();
        }
        int resId = resources.getIdentifier(aString, RESOURCES_STRING, packageName);
        return resources.getString(resId);
    }

    /**
     * Returns a String from the resources file by the id
     *
     * @param resources Resources
     * @param packageName String
     * @param aString String
     *
     * @return int
     */
    public static int getColorResourceByName(Resources resources, String packageName, String aString, Context context) {
        if(resources==null || packageName == null || Utils.isBlank(aString)){
            throw new IllegalArgumentException();
        }
        int resId = resources.getIdentifier(aString, RESOURCES_COLOR, packageName);

        return ContextCompat.getColor(context, resId);
    }

    /**
     *
     * @param value String
     *
     * @return String
     */
    public static Boolean getBooleanFromInt(String value){
        if(isBlank(value)){
            return false;
        }else{
            try{
                int intValue = Integer.parseInt(value);
                if(intValue==Constants.NUM_1){
                    return true;
                }
                return false;
            }catch (NumberFormatException nfe){
                return false;
            }
        }

    }

}
