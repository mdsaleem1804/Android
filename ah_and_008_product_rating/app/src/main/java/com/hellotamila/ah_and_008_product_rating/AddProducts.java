package com.hellotamila.ah_and_008_product_rating;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddProducts extends AppCompatActivity {
    EditText a, b,c,d,f,g,h,i;
    DataBaseConnection mCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_products);
        mCon = new DataBaseConnection(this);
        a = (EditText) findViewById(R.id.edt_name);
        b = (EditText) findViewById(R.id.edt_old_rate);
        c = (EditText) findViewById(R.id.edt_new_rate);
        d = (EditText) findViewById(R.id.edt_state);

        f = (EditText) findViewById(R.id.edt_description);
        g = (EditText) findViewById(R.id.edt_amount);
        h = (EditText) findViewById(R.id.edt_url);

    }

    public void add_products(View v) {
        mCon.insertAddProducts(
                a.getText().toString(),
                b.getText().toString(),
                c.getText().toString(),
                d.getText().toString(),
                f.getText().toString(),
                g.getText().toString(),
                h.getText().toString(),
                h.getText().toString());
        Toast.makeText(getApplicationContext(),"New Products Inserted  Succesfully",Toast.LENGTH_LONG).show();
        Intent xIntent = new Intent(getApplicationContext(), AddProducts.class);
        startActivity(xIntent);
    }
}
