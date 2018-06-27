package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
@SdkSuppress(minSdkVersion = 21)
public class UtilsTest {

    @Test
    public void getStringResourceByName() {
//        assertEquals(null, Utils.getStringResourceByName(null, null, null));

        Context context = InstrumentationRegistry.getContext();

        assertEquals("test", Utils.getStringResourceByName(context.getResources(), context.getPackageName(), "test"));

    }


    @Test
    public void getColorResourceByName() {
//        assertEquals(null, Utils.getColorResourceByName(null, null, null, null));

        Context context = InstrumentationRegistry.getContext();

        assertEquals("#000", Utils.getColorResourceByName(context.getResources(), context.getPackageName(), "test", context));

    }
}
