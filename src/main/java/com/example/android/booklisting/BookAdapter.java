package com.example.android.booklisting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class BookAdapter extends ArrayAdapter<BookInfo> {

    private Context context;

    public BookAdapter(Activity context, ArrayList<BookInfo> book) {

        super(context, 0, book);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }
        final BookInfo currentBook = getItem(position);

        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(currentBook.getTitle());

        TextView authorView = listItemView.findViewById(R.id.author);
        authorView.setText(currentBook.getAuthor());

        TextView descriptView = listItemView.findViewById(R.id.descript);
        descriptView.setText(currentBook.getDescription());

        return listItemView;
    }
}