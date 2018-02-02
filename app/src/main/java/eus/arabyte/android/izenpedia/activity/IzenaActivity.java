package eus.arabyte.android.izenpedia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.Preferences;

public class IzenaActivity extends AppCompatActivity {

    public static final String ARG_IZENA = "izena";

    private IzenaDAO izenaDAO;
    private Preferences preferences;
    private Izena izena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izena);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = Preferences.getInstance(this);

        // cargar los datos
        Bundle args = getIntent().getExtras();
        String argIzena = null;
        if(args!=null) {
            argIzena = args.getString(ARG_IZENA);
        }

        izenaDAO = new IzenaDAOImpl(this);
        izena = izenaDAO.getIzena(argIzena);
        setTitle(izena.getIzena());

        String description = izena.getAzalpenaEu();
        if(Constants.ES.equals(preferences.getHizkuntza())){
            description = izena.getAzalpenaEs();
        }

        ((TextView)this.findViewById(R.id.izena_description))
                .setText(Html.fromHtml(description));


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

        if(eus.arabyte.android.izenpedia.bd.Constants.FAV_SI == izena.getGogokoa()) {

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
                    izena.setGogokoa(eus.arabyte.android.izenpedia.bd.Constants.FAV_SI);
                }else{
                    message=R.string.rm_fav;
                    izena.setGogokoa(eus.arabyte.android.izenpedia.bd.Constants.FAV_NO);
                }

                izenaDAO.updateIzenaFav(izena);

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
