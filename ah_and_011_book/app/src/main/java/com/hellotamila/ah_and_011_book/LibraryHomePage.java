package com.hellotamila.ah_and_011_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LibraryHomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_library_homepage);

    }

    public void p_add_books(View v) {
        xIntent = new Intent(getApplicationContext(), AddBooks.class);
        startActivity(xIntent);

    }

    public void p_issue_books(View v) {
        xIntent = new Intent(getApplicationContext(), IssueBooks.class);
        startActivity(xIntent);

    }

}
