package eus.arabyte.android.izenpedia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenkideaAdapter;
import eus.arabyte.android.izenpedia.dao.GogokoaDAO;
import eus.arabyte.android.izenpedia.dao.GogokoaDAOImpl;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.dao.IzenkideaDAO;
import eus.arabyte.android.izenpedia.dao.IzenkideaDAOImpl;
import eus.arabyte.android.izenpedia.dao.MetaDAO;
import eus.arabyte.android.izenpedia.dao.MetaDAOImpl;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Izenkidea;
import eus.arabyte.android.izenpedia.model.Meta;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.DecimalFormatter;
import eus.arabyte.android.izenpedia.utils.Preferences;
import eus.arabyte.android.izenpedia.utils.Utils;

public class IzenaActivity extends AppCompatActivity {

    public static final String ARG_IZENA = "izena";

    private IzenaDAO izenaDAO;
    private IzenkideaDAO izenkideaDAO;
    private GogokoaDAO gogokoaDAO;

    private Preferences preferences;
    private Izena izena;

    private IzenkideaAdapter izenkediaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izena);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = Preferences.getInstance(this);

        // cargar los datos
        Bundle args = getIntent().getExtras();
        String argIzena = null;
        if(args!=null) {
            argIzena = args.getString(ARG_IZENA);
        }

        gogokoaDAO = new GogokoaDAOImpl(this);
        izenaDAO = new IzenaDAOImpl(this);
        izena = izenaDAO.getIzena(argIzena);
        setTitle(izena.getIzena());

        String description = izena.getAzalpenaEu();
        if(Constants.ES.equals(preferences.getHizkuntza())){
            description = izena.getAzalpenaEs();
        }

        // si hay descripcion la pintamos, si no ocultamos el card
        if(!Utils.isBlank(description)){
            ((TextView)this.findViewById(R.id.izena_description))
                    .setText(Html.fromHtml(description));
        }else{
           this.findViewById(R.id.card_description).setVisibility(View.GONE);
        }

        izenkideaDAO = new IzenkideaDAOImpl(this);
        List<Izenkidea> izenkideaList = izenkideaDAO.getListIzenkidea(izena);

        // si hay izenkideak los pintamos, si no ocultamos el card
        if(izenkideaList!=null && !izenkideaList.isEmpty()){
            izenkediaAdapter = new IzenkideaAdapter(izenkideaList);
            RecyclerView listIzenkideakView = this.findViewById(R.id.list_izenkideak);
            listIzenkideakView.setAdapter(izenkediaAdapter);
            listIzenkideakView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            this.findViewById(R.id.card_languages).setVisibility(View.GONE);
        }

        // si hay datos los pintamos, si no ocultamos el card
        if(izena.getBatazbeste()== null && izena.getEustat()==null && izena.getTotal()==null){
            CardView cardDatuak = this.findViewById(R.id.card_datuak);
            cardDatuak.setVisibility(View.GONE);
        }else {
            String batazbeste = Utils.presentDoubleToDecimalFormat(izena.getBatazbeste());
            String eustat = Utils.presentIntegerToString(izena.getEustat());
            String total = Utils.presentDoubleToDecimalFormat(izena.getTotal());

            if(!Utils.isBlank(batazbeste)){
                ((TextView) this.findViewById(R.id.izena_media)).setText(batazbeste);
            }else{
                this.findViewById(R.id.row_media).setVisibility(View.GONE);
            }

            if(!Utils.isBlank(eustat)){
                ((TextView) this.findViewById(R.id.izena_eustat)).setText(eustat);
            }else{
                this.findViewById(R.id.row_eustat).setVisibility(View.GONE);
            }

            if(!Utils.isBlank(total)){
                ((TextView) this.findViewById(R.id.izena_total)).setText(total);
            }else{
                this.findViewById(R.id.row_total).setVisibility(View.GONE);
            }
            
        }

        BarChart chart = findViewById(R.id.chart);

        MetaDAO metaDAO = new MetaDAOImpl(this);
        List<Meta> metaList = metaDAO.getListMeta(izena);

        if(metaList==null || metaList.isEmpty()){
            this.findViewById(R.id.card_graphics).setVisibility(View.GONE);

        }else{

            List<BarEntry> entries = new ArrayList<>();

            for (Meta meta : metaList) {
                // turn your data into Entry objects
                entries.add(new BarEntry(meta.getUrtea(), meta.getZenbat()));
            }

            BarDataSet dataSet = new BarDataSet(entries, "BarDataSet");
//            LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//            dataSet.setColor();
//            dataSet.setValueTextColor();
            dataSet.setValueTextSize(10f);
            dataSet.setValueFormatter(new DecimalFormatter());

            BarData data = new BarData(dataSet);
//            LineData lineData = new LineData(dataSet);

            //bar
            chart.setDrawValueAboveBar(true);
            chart.setFitBars(true); // make the x-axis fit exactly all bars

            //general
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new DecimalFormatter());
            xAxis.setDrawGridLines(false);

            YAxis yAxis = chart.getAxisLeft();
            yAxis.setDrawGridLines(false);

            chart.setData(data);
            chart.invalidate(); // refresh

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_izena, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_star);
        final CheckBox starMenu = (CheckBox)menuItem.getActionView();

        if(Constants.FAV_SI == izena.getGogokoa()) {

            starMenu.setChecked(true);
        }else{
            starMenu.setChecked(false);
        }

        starMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int message;
                if(starMenu.isChecked()){
                    message=R.string.add_fav;
                    gogokoaDAO.addGogokoa(izena);
                }else{
                    message=R.string.rm_fav;
                    gogokoaDAO.removeGogokoa(izena);
                }

                Snackbar snackbar = Snackbar
                        .make(view, message, Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        });


        return super.onPrepareOptionsMenu(menu);
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                String description = izena.getAzalpenaEu();
                if(Constants.ES.equals(preferences.getHizkuntza())){
                    description = izena.getAzalpenaEs();
                }

                shareIntent.putExtra(Intent.EXTRA_TEXT, izena.getIzena() + ": " + description);
                startActivity(shareIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
