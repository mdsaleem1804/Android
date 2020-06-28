package com.nellaibill.licence_qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage  extends Activity {
    Button xScanandSave,xScanandMatch;
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        xScanandSave = (Button) findViewById(R.id.btn_scanandsave);
        xScanandMatch = (Button) findViewById(R.id.btn_scanandmatch);
        xScanandSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                xIntent = new Intent(HomePage.this,
                        ScanandSave.class);
                startActivity(xIntent);

            }
        });


        xScanandMatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                xIntent = new Intent(HomePage.this,
                        ScanandMatch.class);
                startActivity(xIntent);

            }
        });

    }


}