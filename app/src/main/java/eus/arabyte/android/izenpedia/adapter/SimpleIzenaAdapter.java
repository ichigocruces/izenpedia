package eus.arabyte.android.izenpedia.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.dao.GogokoaDAO;
import eus.arabyte.android.izenpedia.dao.GogokoaDAOImpl;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

/**
 * Created by ichigo on 14/01/18.
 */

public class SimpleIzenaAdapter extends RecyclerView.Adapter<SimpleIzenaAdapter.IzenaViewHolder> {
    private GogokoaDAO gogokoaDAO;

    private View.OnClickListener onClickListener;

    private List<Izena> mDataSet= new ArrayList<>();

    public SimpleIzenaAdapter(List<Izena> data) {
        super();
        this.mDataSet.addAll(data);
    }

    @Override
    public IzenaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_izenak_item, parent, false);
        IzenaViewHolder izenaViewHolder = new IzenaViewHolder(v);
        v.setOnClickListener(onClickListener);

        gogokoaDAO = new GogokoaDAOImpl(parent.getContext());

        return izenaViewHolder;
    }

    @Override
    public void onBindViewHolder(IzenaViewHolder holder, int position) {
        final Izena izena = mDataSet.get(position);

        //izena
        holder.holderIzena.setText(izena.getIzena());

        //Gogokoa
        if (izena.getGogokoa() == Constants.FAV_SI) {
            holder.img_Gogokoa.setChecked(true);
        }else {
            holder.img_Gogokoa.setChecked(false);
        }

        // cuando se pincha
        holder.img_Gogokoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int message;
                if(((CheckBox)view).isChecked()){
                    izena.setGogokoa(Constants.FAV_SI);
                    message=R.string.add_fav;
                    gogokoaDAO.addGogokoa(izena);
                }else{
                    izena.setGogokoa(Constants.FAV_NO);
                    message=R.string.rm_fav;
                    gogokoaDAO.removeGogokoa(izena);
                }

                Snackbar snackbar = Snackbar
                        .make(view, message, Snackbar.LENGTH_LONG)
//                        .setAction("ACTIVAR", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                startActivity( new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
//                            }
//                        })
                        ;

                snackbar.show();

                notifyDataSetChanged();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }


    /**
     * Implementation of a holder for the adapter
     */
    public static class IzenaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView holderIzena;
        CheckBox img_Gogokoa;

        IzenaViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            holderIzena = itemView.findViewById(R.id.holder_izena);
            img_Gogokoa = itemView.findViewById(R.id.holder_gogokoa);
        }
    }



}
