package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class BoysFragment extends BaseFragment {

    public BoysFragment() {
        super();

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);


        _layout = R.layout.izenak_list;
        _title = R.string.nav_boys;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(izenaDAO.getListIzenakByGender(Constants.GIZONA), ListType.BOYS);

    }

}
