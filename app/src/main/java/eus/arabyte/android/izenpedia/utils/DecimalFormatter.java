package eus.arabyte.android.izenpedia.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by ichigo on 18/02/18.
 */

public class DecimalFormatter implements IValueFormatter, IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return Utils.presentDoubleToDecimalFormat(Double.valueOf(value));
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return Utils.presentIntegerToString(Math.round(value));
    }
}
