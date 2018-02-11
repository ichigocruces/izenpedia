package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;
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
import eus.arabyte.android.izenpedia.activity.listeners.IzenaOnClickListener;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.adapter.SimpleIzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class PopularFragment extends Fragment {

    private SimpleIzenaAdapter izenaAdapter;
    private IzenaDAO izenaDAO;

    public PopularFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.simple_izenak_list, container, false);
        RecyclerView listIzenakView = rootView.findViewById(R.id.list_izenak);

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new SimpleIzenaAdapter(izenaDAO.getPopularIzenak());

        izenaAdapter.setOnClickListener(new IzenaOnClickListener());


        listIzenakView.setAdapter(izenaAdapter);
        listIzenakView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_popular);
    }

}
