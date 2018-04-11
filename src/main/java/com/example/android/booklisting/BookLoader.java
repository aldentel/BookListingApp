package com.example.android.booklisting;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<BookInfo>> {

    /**
     * Query URL
     */
    private String mUrl;


    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<BookInfo> loadInBackground() {
        if (mUrl == null)

        {
            return null;
        }

        List<BookInfo> books = QueryUtils.fetchBookInfo(mUrl);
        return books;
    }
}
