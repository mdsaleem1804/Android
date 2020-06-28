package com.nellaibill.petcollege;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    public void p_return_book(View v) {

        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/view_issue_return_books.php");
        startActivity(xIntent);

    }
    public void p_list_issue_book(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/view_issue_books.php");
        startActivity(xIntent);
    }


}
