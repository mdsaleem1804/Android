package com.example.tut_imageeffects;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView xImgView;

	private Bitmap xBtmp;
	private Bitmap xBtmpOperation;
	Button button;
	private Integer images[] = { R.drawable.changes1, R.drawable.changes2,
			R.drawable.changes3 };
	private int currImage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		xImgView = (ImageView) findViewById(R.id.imageView1);
		BitmapDrawable abmp = (BitmapDrawable) xImgView.getDrawable();
		xBtmp = abmp.getBitmap();
		addListenerOnButton();
		setInitialImage();
	}


	public void addListenerOnButton() {
		button = (Button) findViewById(R.id.btnChangeImageView);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				for (int i = 0; i < 2; i++) {
					xImgView.setImageResource(R.drawable.changes1);
				}
			}
		});
	}

	private void setInitialImage() {
		setCurrentImage();
	}

	private void setCurrentImage() {

		xImgView.setImageResource(images[currImage]);

	}

	public void prev(View view) {
		try
		{
		currImage--;
		if (currImage == 3) {
			currImage = 0;
		}
		setCurrentImage();
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}
	}

	public void next(View view) {
		try
		{
		currImage++;
		if (currImage == 3) {
			currImage = 0;
		}
		setCurrentImage();
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}
	}
	public void green(View view) {
		xBtmpOperation = Bitmap.createBitmap(xBtmp.getWidth(),
				xBtmp.getHeight(), xBtmp.getConfig());

		for (int i = 0; i < xBtmp.getWidth(); i++) {
			for (int j = 0; j < xBtmp.getHeight(); j++) {
				int r = 0;
				int g = 150;
				int b = 0;
				xBtmpOperation.setPixel(i, j, Color.rgb(r, g, b));
			}
		}
		xImgView.setImageBitmap(xBtmpOperation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
