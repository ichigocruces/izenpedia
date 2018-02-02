package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class PopularFragment extends BaseFragment {

    public PopularFragment() {
        super();
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        _layout = R.layout.izenak_list;
        _title = R.string.nav_popular;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(izenaDAO.getPopularIzenak(), ListType.POPULAR);
    }


}