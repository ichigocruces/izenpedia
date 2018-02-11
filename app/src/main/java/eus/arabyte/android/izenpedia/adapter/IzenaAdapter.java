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
import eus.arabyte.android.izenpedia.dao.IzenaDAO;
import eus.arabyte.android.izenpedia.dao.IzenaDAOImpl;
import eus.arabyte.android.izenpedia.model.Izena;
import eus.arabyte.android.izenpedia.utils.Constants;
import eus.arabyte.android.izenpedia.utils.ListType;
import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

/**
 * Created by ichigo on 14/01/18.
 */

public class IzenaAdapter extends RecyclerView.Adapter<IzenaAdapter.IzenaViewHolder> implements Filterable, SectionIndexer {
    private IzenaDAO izenaDAO;
    private GogokoaDAO gogokoaDAO;

    private List<Izena> mDataSet;
    private List<Izena> mFileterdDataSet;
    private ArrayList<Integer> mSectionPositions;

    private View.OnClickListener onClickListener;
    private IzenaFilter mFilter = new IzenaFilter();

    private ListType listType;

    private IndexFastScrollRecyclerView indexFastScrollRecyclerView;

    public IzenaAdapter(List<Izena> data) {
        super();
        this.mDataSet = data;
        this.mFileterdDataSet = data;

    }

    public IzenaAdapter(List<Izena> data, ListType listType) {
        this(data);
        this.listType = listType;
    }

    public IzenaAdapter(List<Izena> data, View.OnClickListener onClickListener) {
        this(data);
        this.onClickListener = onClickListener;
    }

    @Override
    public IzenaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.izenak_item, parent, false);
        IzenaViewHolder izenaViewHolder = new IzenaViewHolder(v);
        v.setOnClickListener(onClickListener);

        izenaDAO = new IzenaDAOImpl(parent.getContext());
        gogokoaDAO = new GogokoaDAOImpl(parent.getContext());

        return izenaViewHolder;
    }

    @Override
    public void onBindViewHolder(IzenaViewHolder holder, int position) {
        final Izena izena = mFileterdDataSet.get(position);


        //dependiendo del tipo de fragmento mostraremos la primera letra o el ranking
        switch (this.listType){
            case POPULAR:
                //FIXME: poner el valor del EUSTAT
                holder.holderIcon.setText(String.valueOf(position + 1));
                break;
            default:
                holder.holderIcon.setText(String.valueOf(izena.getIzena().charAt(0)).toUpperCase());
                break;
        }

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
        return mFileterdDataSet.size();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        this.indexFastScrollRecyclerView = (IndexFastScrollRecyclerView) recyclerView;

    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void clearFilter(){
        mFileterdDataSet.clear();
        mFileterdDataSet.addAll(mDataSet);

        this.indexFastScrollRecyclerView.setIndexBarVisibility(true);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        this.indexFastScrollRecyclerView.setIndexBarVisibility(false);
        return mFilter;
    }

    /**
     * Section Index
     */

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>(26);
        mSectionPositions = new ArrayList<>(26);
        for (int i = 0, size = mFileterdDataSet.size(); i < size; i++) {
            String izena = mFileterdDataSet.get(i).getIzena();
            String section = String.valueOf(izena.charAt(0)).toUpperCase();
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        return sections.toArray(new String[0]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSectionPositions.get(sectionIndex);
    }


    /**
     * Implementation of a holder for the adapter
     */
    public static class IzenaViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView holderIzena, holderIcon;
        CheckBox img_Gogokoa;

        IzenaViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            holderIcon = itemView.findViewById(R.id.holder_icon);
            holderIzena = itemView.findViewById(R.id.holder_izena);
            img_Gogokoa = itemView.findViewById(R.id.holder_gogokoa);
        }
    }


    /**
     * Implementation of a filter for the adapter
     */
    private class IzenaFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Izena> list = mDataSet;

            int count = list.size();
            final ArrayList<Izena> nlist = new ArrayList<>(count);

            Izena filterableIzena ;

            for (int i = 0; i < count; i++) {
                filterableIzena = list.get(i);
                if (filterableIzena.getIzena().toLowerCase().startsWith(filterString)) {
                    nlist.add(filterableIzena);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mFileterdDataSet = (ArrayList<Izena>) results.values;
            notifyDataSetChanged();
        }
    }

}
