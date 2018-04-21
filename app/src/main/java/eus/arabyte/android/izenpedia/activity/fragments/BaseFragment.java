package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import eus.arabyte.android.izenpedia.activity.listeners.OnRecyclerItemClickListener;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.ListType;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ichigo on 23/01/18.
 */

public abstract class BaseFragment extends Fragment implements OnRecyclerItemClickListener<Izena> {

    protected IzenaAdapter izenaAdapter;
    protected IzenaDAO izenaDAO;

    /**
     * set layout
     */
    protected int _layout;

    /**
     * set title
     */
    protected int _title;

    /**
     * set the list type
     */
    protected ListType _listType;

    protected RecyclerView listIzenakView;

    public BaseFragment() {
        super();
    }

    /**
     * OnCreate method
     *
     * @param savedInstanceState Bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     * Método que se invoca cuando se crea la vista princial.
     * Asignamos el adapter, el layout.
     * Dependiendo del listado creamos el IndexFastScrollRecyclerView
     *
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     *
     * @return View
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(this._layout, container, false);
        listIzenakView = rootView.findViewById(R.id.list_izenak);


        listIzenakView.setAdapter(izenaAdapter);
        listIzenakView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //dependiendo del listado creamos el IndexFastScrollRecyclerView
        switch (_listType){
            case BOYS:case GIRLS:
                IndexFastScrollRecyclerView mScrollRecyclerView = (IndexFastScrollRecyclerView)listIzenakView;

                mScrollRecyclerView.setIndexBarCornerRadius(0);
                mScrollRecyclerView.setIndexBarTransparentValue((float) 0.4);
                mScrollRecyclerView.setIndexbarMargin(0);

                break;
            default:

                break;
        }


        return rootView;
    }

    /**
     * Método al que se invoca cuando se ha creado la vista
     *
     * @param view View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(_title);
    }

    /**
     * Tratamos la respuesta del IzenaActivity
     *
     * @param requestCode int
     * @param resultCode int
     * @param data Intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // La lista debe refrescarse en el caso de que desmarque algun favorito

        if (resultCode == RESULT_OK && requestCode == IzenaActivity.REQUEST) {
            if (data.hasExtra(IzenaActivity.ARG_IZENA_POSITION)) {
                Izena izena = (Izena)data.getSerializableExtra(IzenaActivity.ARG_IZENA_OBJECT);
                int pos = data.getIntExtra(IzenaActivity.ARG_IZENA_POSITION, 0);
                izenaAdapter.setItem(pos, izena);
            }
        }


    }

    /**
     * Iniciar IzenaActivity esperando un resultado
     *
     * @param view     that was clicked
     * @param position of the clicked view
     * @param item     the concrete data that is displayed through the clicked view
     */
    @Override
    public void onItemClick(View view, int position, Izena item) {


        String idIzena = ((TextView)view.findViewById(R.id.holder_izena)).getText().toString();

        Intent izenaIntent = new Intent(view.getContext(), IzenaActivity.class);

        izenaIntent.putExtra(IzenaActivity.ARG_IZENA, idIzena);
        izenaIntent.putExtra(IzenaActivity.ARG_IZENA_POSITION, position);
        izenaIntent.putExtra(IzenaActivity.ARG_IZENA_OBJECT, item);

        startActivityForResult(izenaIntent, IzenaActivity.REQUEST);
    }

    /**
     * Genera el menu dependiendo del tipo de listado
     *
     * @param menu Menu
     * @param inflater MenuInflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        switch (_listType){
            case GIRLS:case BOYS:
                this.createSearchOptionMenu(menu, inflater);
                break;
        }


    }

    /**
     * Create the search option menu
     *
     * @param menu Menu
     * @param inflater MenuInflater
     */
    private void createSearchOptionMenu(Menu menu, MenuInflater inflater){
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
