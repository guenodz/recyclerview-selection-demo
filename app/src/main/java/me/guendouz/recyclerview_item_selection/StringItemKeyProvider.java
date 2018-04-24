package me.guendouz.recyclerview_item_selection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import androidx.recyclerview.selection.ItemKeyProvider;

/**
 * A basic implementation of {@link ItemKeyProvider} for String items.
 */

public class StringItemKeyProvider extends ItemKeyProvider<String> {

    private List<String> items;

    public StringItemKeyProvider(int scope, List<String> items) {
        super(scope);
        this.items = items;
    }

    @Nullable
    @Override
    public String getKey(int position) {
        return items.get(position);
    }

    @Override
    public int getPosition(@NonNull String key) {
        return items.indexOf(key);
    }
}
