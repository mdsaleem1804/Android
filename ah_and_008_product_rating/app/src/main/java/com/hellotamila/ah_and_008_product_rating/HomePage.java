package com.hellotamila.ah_and_008_product_rating;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class HomePage extends AppCompatActivity {
    DataBaseConnection mCon;
    private SQLiteDatabase db;
    private Cursor xCursor;
    EditText a, b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_homepage);
        mCon = new DataBaseConnection(this);

        openDatabase();

    }
    public void new_user(View v) {
        Intent xIntent = new Intent(getApplicationContext(), NewUser.class);
        startActivity(xIntent);
    }
    protected void openDatabase() {
        db = openOrCreateDatabase("ah_and_008_product_rating.db", Context.MODE_PRIVATE, null);
    }
    public void login(View v) {
                Intent xIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(xIntent);

    }
    public void list_products(View v) {
        Intent xIntent = new Intent(getApplicationContext(), ListProducts.class);
        startActivity(xIntent);
    }
    public void list_products_old_new_rate(View v) {
        Intent xIntent = new Intent(getApplicationContext(), ListProducts_Old_New_Rate.class);
        startActivity(xIntent);
    }
    public void admin(View v) {
        Intent   xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/spiro/shopping/admin");
        startActivity(xIntent);
    }
    public void user(View v) {
        Intent   xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/spiro/shopping");
        startActivity(xIntent);

    }
}
