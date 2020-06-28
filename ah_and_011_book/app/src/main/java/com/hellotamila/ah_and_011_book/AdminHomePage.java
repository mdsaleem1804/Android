package com.hellotamila.ah_and_011_book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AdminHomePage extends AppCompatActivity {
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_admin_home_page);

    }
    public void add_stud(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/studreg.php");
        startActivity(xIntent);
    }
    public void view_stud(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/viewstudents.php");
        startActivity(xIntent);

    }
    public void news_feed(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/news_feed.php");
        startActivity(xIntent);
    }
    public void logout(View v) {
        xIntent = new Intent(getApplicationContext(), HomePage.class);
        this.finish();
        startActivity(xIntent);
    }
}
