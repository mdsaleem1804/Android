package com.hellotamila.ah_and_001_security;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Grid_Set_Prompt extends Activity {
    Intent xIntent;
    String xMobileNo;
    SimpleGetterAndSetter obj;
    Button a,b,c,d,e,f,g,h,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_set_prompt);
        a = (Button) findViewById(R.id.a);
        b = (Button) findViewById(R.id.b);
        c = (Button) findViewById(R.id.c);
        d = (Button) findViewById(R.id.d);
        e = (Button) findViewById(R.id.e);
        f = (Button) findViewById(R.id.f);
        g = (Button) findViewById(R.id.g);
        h= (Button) findViewById(R.id.h);
        i = (Button) findViewById(R.id.i);
        obj = new SimpleGetterAndSetter();
        Toast.makeText(getApplicationContext(), obj.getUsername(),Toast.LENGTH_LONG).show();
    }
    public void go_conirm_prompt(View v) {
        xIntent = new Intent(getApplicationContext(), Grid_Confirm_Prompt.class);
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        obj.setImage1(buttonText);
        startActivity(xIntent);
    }
}