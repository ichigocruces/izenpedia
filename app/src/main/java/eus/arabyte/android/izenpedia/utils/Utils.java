package eus.arabyte.android.izenpedia.utils;

/**
 * Created by ichigo on 13/02/18.
 */

public class Utils {

    private final static String EMPTY_STRING= "";
    private final static String NULL_STRING= "null";


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
            return strInt;
        }
    }

}
