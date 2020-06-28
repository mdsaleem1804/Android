package com.example.tut_checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {
	CheckBox xAndroid, xPhp;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xAndroid = (CheckBox) findViewById(R.id.checkBox1);
		xPhp = (CheckBox) findViewById(R.id.checkBox2);
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuffer result = new StringBuffer();
				result.append("Thanks : ").append(xAndroid.isChecked());
				result.append("\nThanks: ").append(xPhp.isChecked());
				Toast.makeText(MainActivity.this, result.toString(),
						Toast.LENGTH_LONG).show();
				
				if(xAndroid.isChecked())
				 {
				 Toast.makeText(MainActivity.this,"Android Checked", Toast.LENGTH_SHORT).show();
				 }
				 else
				 {
				 Toast.makeText(MainActivity.this,"Php Checked", Toast.LENGTH_SHORT).show();
				 }
				 
				 
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
