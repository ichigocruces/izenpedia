package eus.arabyte.android.izendegia.activity.fragments;

import android.content.Context;

import eus.arabyte.android.izendegia.R;
import eus.arabyte.android.izendegia.adapter.IzenaAdapter;
import eus.arabyte.android.izendegia.dao.GogokoaDAOImpl;
import eus.arabyte.android.izendegia.dao.IzenaDAOImpl;
import eus.arabyte.android.izendegia.utils.Constants;
import eus.arabyte.android.izendegia.utils.ListType;

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
        _listType = ListType.BOYS;
        _params = Constants.GIZONA;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(this, _listType, new GogokoaDAOImpl(context));

    }

}
