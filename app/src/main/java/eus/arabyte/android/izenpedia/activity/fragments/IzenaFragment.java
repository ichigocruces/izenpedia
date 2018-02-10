package eus.arabyte.android.izenpedia.activity.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class IzenaFragment extends Fragment {

    public static final String ARG_IZENA = "izena";

    private IzenaDAO izenaDAO;
    private IzenkideaDAO izenkideaDAO;
    private GogokoaDAO gogokoaDAO;

    private Preferences preferences;
    private Izena izena;

    private IzenkideaAdapter izenkediaAdapter;


    public IzenaFragment() {
        // Required empty public constructor
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_izena, container, false);

        preferences = Preferences.getInstance(rootView.getContext());


        // cargar los datos
//        Bundle args = getIntent().getExtras();
        Bundle args = getArguments();
        String argIzena = null;
        if (args != null) {
            argIzena = args.getString(ARG_IZENA);
        }

        izenaDAO = new IzenaDAOImpl(rootView.getContext());
        izena = izenaDAO.getIzena(argIzena);
        getActivity().setTitle(izena.getIzena());

        String description = izena.getAzalpenaEu();
        if (Constants.ES.equals(preferences.getHizkuntza())) {
            description = izena.getAzalpenaEs();
        }

        ((TextView) rootView.findViewById(R.id.izena_description))
                .setText(Html.fromHtml(description));

        izenkideaDAO = new IzenkideaDAOImpl(rootView.getContext());
        List<Izenkidea> izenkideaList = izenkideaDAO.getListIzenkidea(izena);

        izenkediaAdapter = new IzenkideaAdapter(izenkideaList);
        RecyclerView listIzenkideakView = rootView.findViewById(R.id.list_izenkideak);
        listIzenkideakView.setAdapter(izenkediaAdapter);
        listIzenkideakView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        gogokoaDAO = new GogokoaDAOImpl(rootView.getContext());

        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.menu_izena, menu);
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
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
