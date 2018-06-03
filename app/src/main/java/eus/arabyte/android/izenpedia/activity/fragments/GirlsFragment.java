package eus.arabyte.android.izenpedia.activity.fragments;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.adapter.IzenaAdapter;
import eus.arabyte.android.izenpedia.dao.GogokoaDAOImpl;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;

/**
 * Created by ichigo on 5/11/17.
 */

public class GirlsFragment extends BaseFragment {

    public GirlsFragment() {
        super();

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        _layout = R.layout.izenak_list;
        _title = R.string.nav_girls;
        _listType = ListType.GIRLS;
        _params = Constants.EMAKUME;

        izenaDAO = new IzenaDAOImpl(getActivity());
        izenaAdapter = new IzenaAdapter(this, _listType, new GogokoaDAOImpl(context));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
