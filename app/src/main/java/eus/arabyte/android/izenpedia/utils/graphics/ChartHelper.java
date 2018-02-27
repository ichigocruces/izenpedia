package eus.arabyte.android.izenpedia.utils.graphics;

import android.content.Context;
import android.content.res.Resources;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eus.arabyte.android.izenpedia.model.Meta;
import eus.arabyte.android.izenpedia.utils.LurraldeHistoriko;
import eus.arabyte.android.izenpedia.utils.Utils;
import eus.arabyte.android.izenpedia.utils.formatter.DecimalFormatter;
import eus.arabyte.android.izenpedia.utils.formatter.IntegerFormatter;

/**
 * Created by ichigo on 20/02/18.
 */

public class ChartHelper {

    private static final float TEXT_SIZE_LEGEND = 14f;
    private static final float TEXT_SIZE = 12f;

    private ChartHelper(){}

    public static void createTotalBarChart(BarChart chart, List<Meta> metaList){
        List<BarEntry> entries = new ArrayList<>();

        for (Meta meta : metaList) {
            // turn your data into Entry objects
            entries.add(new BarEntry(meta.getUrtea(), meta.getZenbat()));
        }

        //FIXME: el label esta a pinon
        BarDataSet dataSet = new BarDataSet(entries, null);
        dataSet.setValueTextSize(TEXT_SIZE);
        dataSet.setValueFormatter(new DecimalFormatter());

        BarData data = new BarData(dataSet);

        //bar
        chart.setDrawValueAboveBar(true);
        chart.setFitBars(true); // make the x-axis fit exactly all bars

        //general
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IntegerFormatter());
        //arregla los valores duplicados de la x
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(TEXT_SIZE);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawGridLines(false);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setTextSize(TEXT_SIZE);


        // quitamos el label del chart
        Description label = new Description();
        label.setEnabled(false);
        chart.setDescription(label);

        // quitamos la leyenda (en este caso no tiene sentido)
        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.setData(data);
        chart.invalidate(); // refresh

    }

    public static void createLHLineChart(LineChart chart,
                                         Map<LurraldeHistoriko,List<Meta>> mapListLH,
                                         Resources resources,
                                         String packageName,
                                         Context context){

        if(mapListLH==null || mapListLH.isEmpty() || chart==null){
            return;
        }

        List<ILineDataSet> dataSets = new ArrayList<>();
        //por cada territorio, recuperamos los datos
        for(LurraldeHistoriko lh: mapListLH.keySet()){

            List<Entry> entries = new ArrayList<>();
            List<Meta> metaList = mapListLH.get(lh);

            for (Meta meta: metaList) {
                entries.add(new Entry(meta.getUrtea(), meta.getZenbat()));
            }

            String label = Utils.getStringResourceByName(resources, packageName, lh.getLabel());
            LineDataSet setComp = new LineDataSet(entries, label);
            setComp.setAxisDependency(YAxis.AxisDependency.LEFT);

            int color = Utils.getColorResourceByName(resources, packageName, lh.getColor(), context);
            setComp.setColor(color);

            setComp.setValueTextSize(TEXT_SIZE);
            setComp.setValueFormatter(new IntegerFormatter());

            dataSets.add(setComp);

        }

        //general
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IntegerFormatter());
        xAxis.setTextSize(TEXT_SIZE);
        //arregla los valores duplicados de la x
        xAxis.setGranularity(1f);
        xAxis.setAvoidFirstLastClipping(true);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawGridLines(false);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setTextSize(TEXT_SIZE);

        LineData data = new LineData(dataSets);

        Description label = new Description();
        label.setEnabled(false);
        chart.setDescription(label);

        Legend legend = chart.getLegend();
        legend.setTextSize(TEXT_SIZE_LEGEND);

        chart.setData(data);
        chart.invalidate(); // refresh

    }

}
