package com.nellaibill.leafidentification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    Button xBtnLeafCamera,xBtnLeafGallery,xBtnLeafMatch;
    Intent xIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        xBtnLeafCamera = (Button) findViewById(R.id.btn_leaf_camera);
        xBtnLeafGallery = (Button) findViewById(R.id.btn_leaf_gallery);
        xBtnLeafMatch = (Button) findViewById(R.id.btn_leaf_match);
        xBtnLeafCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                xIntent = new Intent(HomePage.this,
                        CaptureImage.class);
                startActivity(xIntent);

            }
        });

        xBtnLeafGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                xIntent = new Intent(HomePage.this,
                        Gallery.class);
                startActivity(xIntent);

            }
        });
        xBtnLeafMatch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                xIntent = new Intent(HomePage.this,
                        CompareImage.class);
                startActivity(xIntent);

            }
        });

    }
}
