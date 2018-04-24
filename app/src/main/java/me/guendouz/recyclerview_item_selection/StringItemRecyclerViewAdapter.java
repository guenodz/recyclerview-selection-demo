package me.guendouz.recyclerview_item_selection;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.selection.SelectionTracker;

import static androidx.recyclerview.selection.ItemDetailsLookup.ItemDetails;

/**
 * A basic RecyclerView adapter for strings.
 */

public class StringItemRecyclerViewAdapter extends RecyclerView.Adapter<StringItemRecyclerViewAdapter.ItemViewHolder> {


    /**
     * List of strings to be shown
     */
    private List<String> mStrings;
    /**
     * The SelectionTracker used by the RecyclerView, used mainly to update item's background color
     */
    private SelectionTracker mSelectionTracker;

    public StringItemRecyclerViewAdapter(List<String> mStrings) {
        this.mStrings = mStrings;
    }

    public void setSelectionTracker(SelectionTracker mSelectionTracker) {
        this.mSelectionTracker = mSelectionTracker;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = mStrings.get(position);
        holder.bind(item, mSelectionTracker.isSelected(item));
    }

    @Override
    public int getItemCount() {
        return mStrings == null ? 0 : mStrings.size();
    }

    @Override
    public long getItemId(int position) {
        return mStrings.get(position).hashCode();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        void bind(String item, boolean isSelected) {
            textView.setText(item);
            // If the item is selected then we change its state to activated
            textView.setActivated(isSelected);
        }

        /**
         * Create a new {@link StringItemDetails} for each string item, will be used later by {@link StringItemDetailsLookup#getItemDetails(MotionEvent)}
         * @return {@link StringItemDetails} instance
         */
        ItemDetails getItemDetails() {
            return new StringItemDetails(getAdapterPosition(), mStrings.get(getAdapterPosition()));
        }
    }
}
