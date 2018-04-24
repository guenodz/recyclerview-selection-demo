package me.guendouz.recyclerview_item_selection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final List<String> ITEMS = Arrays.asList(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L"
    );

    private RecyclerView mRecyclerView;
    private StringItemRecyclerViewAdapter mAdapter;
    private SelectionTracker mSelectionTracker;

    private Button btnClearSelection;
    private TextView tvSelectionCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClearSelection = findViewById(R.id.btnClearSelection);
        btnClearSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectionTracker.hasSelection())
                    mSelectionTracker.clearSelection();
            }
        });

        tvSelectionCount = findViewById(R.id.tvSelectionCount);

        mRecyclerView = findViewById(R.id.itemsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new StringItemRecyclerViewAdapter(ITEMS);
        mRecyclerView.setAdapter(mAdapter);

        mSelectionTracker = new SelectionTracker.Builder<>(
                "string-items-selection",
                mRecyclerView,
                new StringItemKeyProvider(1, ITEMS),
                new StringItemDetailsLookup(mRecyclerView),
                StorageStrategy.createStringStorage()
        ).withSelectionPredicate(SelectionPredicates.<String>createSelectAnything())
                .build();

        mAdapter.setSelectionTracker(mSelectionTracker);

        mSelectionTracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);
                Timber.i("ItemStateChanged %s to %b", key, selected);
            }

            @Override
            public void onSelectionRefresh() {
                super.onSelectionRefresh();
                Timber.i("onSelectionRefresh()");
                tvSelectionCount.setText("Selection Count: 0");
            }

            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();
                Timber.i("onSelectionChanged()");

                if (mSelectionTracker.hasSelection()) {
                    tvSelectionCount.setText(String.format("Selection Count: %d", mSelectionTracker.getSelection().size()));
                } else {
                    tvSelectionCount.setText("Selection Count: 0");
                }
            }

            @Override
            public void onSelectionRestored() {
                super.onSelectionRestored();
                Timber.i("onSelectionRestored()");
                tvSelectionCount.setText("Selection Count: 0");
            }
        });

    }
}
