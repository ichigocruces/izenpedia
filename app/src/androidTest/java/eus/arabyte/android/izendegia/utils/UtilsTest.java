package eus.arabyte.android.izendegia.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
@SdkSuppress(minSdkVersion = 21)
public class UtilsTest {

    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void getStringResourceByName() {
        assertEquals("test", Utils.getStringResourceByName(context.getResources(), context.getPackageName(), "test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getStringResourceByNullName() {
        assertEquals(null, Utils.getStringResourceByName(null, null, null));
    }

    @Test
    public void getColorResourceByName() {
        assertEquals("#000", Utils.getColorResourceByName(context.getResources(), context.getPackageName(), "test", context));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getColorResourceByNullName() {
        assertEquals(null, Utils.getColorResourceByName(null, null, null, null));
    }
}
