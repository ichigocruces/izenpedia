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
public class PropertiesReaderTest {

    private Context context;

    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void getProperty() {
        PropertiesReader propertiesReader = PropertiesReader.getInstance(context);

        assertEquals(null, propertiesReader.getProperty(null));
        assertEquals(null, propertiesReader.getProperty(""));
        assertEquals(null, propertiesReader.getProperty("null"));
        assertEquals("test", propertiesReader.getProperty("test"));
    }
}
