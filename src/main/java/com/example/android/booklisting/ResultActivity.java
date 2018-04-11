package com.example.android.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<BookInfo>> {
    private static final int BOOK_LOADER_ID = 1;

    private BookAdapter bookAdapter;

    private TextView emptyStateView;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_search_result);


        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = findViewById(R.id.list);

        // Create a new adapter that takes an empty list of books as input
        bookAdapter = new BookAdapter(this, new ArrayList<BookInfo>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(bookAdapter);

        emptyStateView = findViewById(R.id.empty_view);
        bookListView.setEmptyView(emptyStateView);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            emptyStateView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<BookInfo>> onCreateLoader(int i, Bundle bundle) {

        Bundle extras = getIntent().getExtras();
        String searchUrl = extras.getString("searchUrl");

        // Create a new loader for the given URL
        return new BookLoader(this, searchUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<BookInfo>> loader, List<BookInfo> books) {
        emptyStateView.setText(R.string.no_books);

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        // Clear the adapter of previous book data
        bookAdapter.clear();

        // If there is a valid list of {@link books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<BookInfo>> loader) {
        // Loader reset, so we can clear out our existing data.
        bookAdapter.clear();


    }
}
