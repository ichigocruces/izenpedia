package eus.arabyte.android.izenpedia.activity.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.activity.IzenaActivity;

/**
 * Created by ichigo on 11/02/18.
 */

public class IzenaOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {

//            IzenaFragment izenaFragment = new IzenaFragment();
//
//            Integer idIzena = (Integer)(view.findViewById(R.id.holder_izena)).getTag();
//
//            Bundle args = new Bundle();
//            args.putInt(IzenaFragment.ARG_IZENA, idIzena);
//            izenaFragment.setArguments(args);
//
//
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.content_frame, izenaFragment);
//            transaction.commit();

        String idIzena = ((TextView)view.findViewById(R.id.holder_izena)).getText().toString();

        Intent izenaIntent = new Intent(view.getContext(), IzenaActivity.class);

        izenaIntent.putExtra(IzenaActivity.ARG_IZENA, idIzena);

        view.getContext().startActivity(izenaIntent);
    }
}
