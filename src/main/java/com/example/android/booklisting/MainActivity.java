package com.example.android.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String GOOGLEBOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button search = findViewById(R.id.search);

        // Set a click listener on the search button to send an intent to search the google database for query

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText findBook = findViewById(R.id.book_search);

                String searchTerm = findBook.getText().toString();

                String searchUrl = GOOGLEBOOK_REQUEST_URL + searchTerm + "&maxResults=5";

                Intent searchIntent = new Intent(MainActivity.this, ResultActivity.class);

                searchIntent.putExtra("searchUrl", searchUrl);

                startActivity(searchIntent);

            }
        });


    }
}