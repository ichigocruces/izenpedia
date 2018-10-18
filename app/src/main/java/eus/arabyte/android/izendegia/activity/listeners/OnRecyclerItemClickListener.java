package eus.arabyte.android.izendegia.activity.listeners;

import android.view.View;

/**
 * Created by ichigo on 4/03/18.
 */

public interface OnRecyclerItemClickListener<T> {

    /**
     * Called when a click occurred inside a recyclerView item view
     *
     * @param view     that was clicked
     * @param position of the clicked view
     * @param item     the concrete data that is displayed through the clicked view
     */
    void onItemClick(View view, int position, T item);
}
