package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.activity.IzenaActivity;
import eus.arabyte.android.izenpedia.activity.listeners.IzenaOnClickListener;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

/**
 * Created by ichigo on 23/01/18.
 */

public abstract class BaseFragment extends Fragment {

    protected IzenaAdapter izenaAdapter;
    protected IzenaDAO izenaDAO;
    protected int _layout;
    protected int _title;

    protected IndexFastScrollRecyclerView listIzenakView;


    public BaseFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(this._layout, container, false);
        listIzenakView = rootView.findViewById(R.id.list_izenak);

        izenaAdapter.setOnClickListener(new IzenaOnClickListener());


        listIzenakView.setAdapter(izenaAdapter);
        listIzenakView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        listIzenakView.setIndexTextSize(12);
//        listIzenakView.setIndexBarColor("#33334c");
        listIzenakView.setIndexBarCornerRadius(0);
        listIzenakView.setIndexBarTransparentValue((float) 0.4);
        listIzenakView.setIndexbarMargin(0);
//        listIzenakView.setIndexbarWidth(40);
//        listIzenakView.setPreviewPadding(0);
//        listIzenakView.setIndexBarTextColor("#FFFFFF");

//        listIzenakView.setIndexBarVisibility(true);
//        listIzenakView.setIndexbarHighLateTextColor("#33334c");
//        listIzenakView.setIndexBarHighLateTextVisibility(true);

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(_title);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // La lista debe refrescarse en el caso de que desmarque algun favorito

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        //limpiamos el menu
        menu.clear();

        // creamos el menu con el icono de buscar y le asignamos las acciones
        inflater.inflate(R.menu.menu_list, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final  SearchView searchView = (SearchView) item.getActionView();

        item.setActionView(searchView);

        // to hide or show the bar depending if the focus is in the searchview or not
//        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if(hasFocus){
//                    listIzenakView.setIndexBarVisibility(false);
//                }else{
//                    listIzenakView.setIndexBarVisibility(true);
//                }
//            }
//        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //si el texto es vacio limpiamos el filtro
                if (newText==null || newText.trim().isEmpty()){
                    izenaAdapter.clearFilter();
                    return false;
                }

                //filtramos resultados
                izenaAdapter.getFilter().filter(newText);

                return false;
            }
        });

        // al pulsar x, reiniciamos el filtro
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                izenaAdapter.clearFilter();
                return false;
            }
        });

    }
}
