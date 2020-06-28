package com.hellotamila.ah_and_008_product_rating;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class AdminMainPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mainpage);

    }
    public void list_vendors(View v) {
        Intent xIntent = new Intent(getApplicationContext(), ListVendors.class);
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
}
