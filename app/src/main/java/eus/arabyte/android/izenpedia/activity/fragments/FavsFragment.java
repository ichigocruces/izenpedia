package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class FavsFragment extends BaseFragment {

    public FavsFragment() {
        super();
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        _layout = R.layout.izenak_list;
        _title = R.string.nav_favs;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(izenaDAO.getStartedIzenak(), ListType.FAVS);
    }


}
