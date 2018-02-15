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

import java.util.List;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenkideaAdapter;
import eus.arabyte.android.izenpedia.dao.GogokoaDAO;
import eus.arabyte.android.izenpedia.dao.GogokoaDAOImpl;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.dao.IzenkideaDAO;
import eus.arabyte.android.izenpedia.dao.IzenkideaDAOImpl;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.model.Izenkidea;
import eus.arabyte.android.izenpedia.utils.Constants;
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
            if(izena.getBatazbeste()== null){
                ((TextView) this.findViewById(R.id.izena_media)).setText(Utils.presentDoubleToDecimalFormat(izena.getBatazbeste()));
            }else{
                this.findViewById(R.id.row_media).setVisibility(View.GONE);
            }

            if(izena.getEustat()==null){
                ((TextView) this.findViewById(R.id.izena_eustat)).setText(Utils.presentIntegerToString(izena.getEustat()));
            }else{
                this.findViewById(R.id.row_eustat).setVisibility(View.GONE);
            }

            if(izena.getTotal()==null){
                ((TextView) this.findViewById(R.id.izena_total)).setText(Utils.presentDoubleToDecimalFormat(izena.getTotal()));
            }else{
                this.findViewById(R.id.row_total).setVisibility(View.GONE);
            }
            
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
