package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.activity.listeners.IzenaOnClickListener;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.adapter.SimpleIzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class FavsFragment extends Fragment {

    private SimpleIzenaAdapter izenaAdapter;
    private IzenaDAO izenaDAO;

    public FavsFragment() {
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
        izenaAdapter = new SimpleIzenaAdapter(izenaDAO.getStartedIzenak());

        izenaAdapter.setOnClickListener(new IzenaOnClickListener());


        listIzenakView.setAdapter(izenaAdapter);
        listIzenakView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.nav_favs);
    }



}
