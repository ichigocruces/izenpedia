package eus.arabyte.android.izendegia.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void isBlank(){
        assertEquals(true, Utils.isBlank(null));
        assertEquals(true, Utils.isBlank(""));
        assertEquals(true, Utils.isBlank("  "));
        assertEquals(false, Utils.isBlank("is not blank"));
        assertEquals(false, Utils.isBlank("  is not blank"));
        assertEquals(false, Utils.isBlank("is not blank   "));
        assertEquals(false, Utils.isBlank("   is not blank   "));
    }

    @Test
    public void presentDoubleToDecimalFormat(){
        assertEquals("", Utils.presentDoubleToDecimalFormat(null));
        assertEquals("0,00", Utils.presentDoubleToDecimalFormat(0.0));
        assertEquals("10,00", Utils.presentDoubleToDecimalFormat(10.0));
        assertEquals("1.000,00", Utils.presentDoubleToDecimalFormat(1000.0));
        assertEquals("1.000,23", Utils.presentDoubleToDecimalFormat(1000.23));
        assertEquals("1.000.000.000,23", Utils.presentDoubleToDecimalFormat(1000000000.23));
        assertEquals("1.000.000.000,24", Utils.presentDoubleToDecimalFormat(1000000000.239));
        assertEquals("1.000.000.000,23", Utils.presentDoubleToDecimalFormat(1000000000.231));

    }

    @Test
    public void presentIntegerToString(){
        assertEquals("", Utils.presentIntegerToString(null));
        assertEquals("0", Utils.presentIntegerToString(0));
        assertEquals("10", Utils.presentIntegerToString(10));
        assertEquals("1.000", Utils.presentIntegerToString(1000));
        assertEquals("1.000.000.000", Utils.presentIntegerToString(1000000000));

    }


    @Test
    public void getBooleanFromInt(){
        assertEquals(false, Utils.getBooleanFromInt(null));
        assertEquals(false, Utils.getBooleanFromInt(""));
        assertEquals(false, Utils.getBooleanFromInt("  "));

        //FIXME: eso va a cascar
        assertEquals(false, Utils.getBooleanFromInt("is not blank"));
        assertEquals(true, Utils.getBooleanFromInt("1"));
        assertEquals(false, Utils.getBooleanFromInt("0"));
        assertEquals(false, Utils.getBooleanFromInt("10"));
        assertEquals(false, Utils.getBooleanFromInt("1000"));
    }


}
