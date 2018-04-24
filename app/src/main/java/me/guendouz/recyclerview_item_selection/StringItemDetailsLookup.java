package me.guendouz.recyclerview_item_selection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 *  An implementation of a {@link ItemDetailsLookup} to be used to get details when a user make a selection of an item.
 */

public class StringItemDetailsLookup extends ItemDetailsLookup {

    private final RecyclerView mRecyclerView;

    StringItemDetailsLookup(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails getItemDetails(@NonNull MotionEvent e) {
        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
            if (holder instanceof StringItemRecyclerViewAdapter.ItemViewHolder) {
                return ((StringItemRecyclerViewAdapter.ItemViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}
