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

public class PopularGirlFragment extends BaseFragment {

    public PopularGirlFragment() {
        super();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);


        _layout = R.layout.simple_izenak_list;
        _title = R.string.nav_popular;
        _listType = ListType.POPULAR;
        _params = Constants.EMAKUME;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(this, _listType, new GogokoaDAOImpl(context));

    }

}
