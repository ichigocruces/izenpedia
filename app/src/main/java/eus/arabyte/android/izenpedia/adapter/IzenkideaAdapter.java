package eus.arabyte.android.izenpedia.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eus.arabyte.android.izenpedia.R;
import eus.arabyte.android.izenpedia.model.Izenkidea;

/**
 * Created by ichigo on 14/01/18.
 */

public class IzenkideaAdapter extends RecyclerView.Adapter<IzenkideaAdapter.IzenkideaViewHolder>{
    private List<Izenkidea> mDataSet;

    public IzenkideaAdapter(List<Izenkidea> data) {
        super();
        this.mDataSet = data;

    }

    @Override
    public IzenkideaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.izenkideak_item, parent, false);
        IzenkideaViewHolder izenkideaViewHolder = new IzenkideaViewHolder(v);
        return izenkideaViewHolder;
    }

    @Override
    public void onBindViewHolder(IzenkideaViewHolder holder, int position) {
        final Izenkidea izenkidea = mDataSet.get(position);

        //dependiendo del idioma, mostramos un icono
        switch (izenkidea.getHizkuntza()){
            case ES:
                holder.holderHizkuntza.setImageResource(R.drawable.es_icon);
                break;

            case FR:
                holder.holderHizkuntza.setImageResource(R.drawable.fr_icon);
                break;

        }

        //izenkidea
        holder.holderIzenkidea.setText(izenkidea.getIzenkidea());
        System.out.println(izenkidea.getHizkuntza().getHizkuntza() + ": " + izenkidea.getIzenkidea());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    /**
     * Implementation of a holder for the adapter
     */
    public static class IzenkideaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView holderIzenkidea;
        ImageView holderHizkuntza;

        IzenkideaViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_izenkidea);
            holderIzenkidea = itemView.findViewById(R.id.holder_izenkidea);
            holderHizkuntza = itemView.findViewById(R.id.holder_hizkuntza);
        }
    }


}
