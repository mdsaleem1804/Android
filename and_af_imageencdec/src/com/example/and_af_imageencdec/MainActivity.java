package com.example.and_af_imageencdec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String DATABASE_NAME = "and_af_imageencdec.db";
	public static final int DATABASE_VERSION = 1;
	private int PICK_IMAGE_REQUEST = 1;
	private DbConnection mdb = null;
	private SQLiteDatabase db = null;
	private byte[] img = null;
	private ImageView imageview = null;
	private Cursor c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mdb = new DbConnection(getApplicationContext(), DATABASE_NAME, null,
				DATABASE_VERSION);
		db = mdb.getWritableDatabase();
		imageview = (ImageView) findViewById(R.id.imageView_image);

	}

	public void insert(View v) {
		/*
		 * Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
		 * R.drawable.ic_launcher); // Bitmap bitmap =
		 * MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
		 * ByteArrayOutputStream bos=new ByteArrayOutputStream();
		 * bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		 * img=bos.toByteArray();
		 */

		
		Bitmap bmap = BitmapFactory.decodeResource(getResources(), R.id.imageView_image);
        String encodedImageData =getEncoded64ImageStringFromBitmap(bmap);
		ContentValues cv = new ContentValues();
		cv.put("imagetext", encodedImageData);
		cv.put("otpvalue", "123456");
		db.insert("encrypted", null, cv);
		Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT)
				.show();
	}
	public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG,100, stream);
		byte[] byteFormat = stream.toByteArray();
		// get the base 64 string
		String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
		return imgString;
			}


	public void galleryview(View v) {
		Intent intent = new Intent(MainActivity.this, Gallery_View.class);
		startActivityForResult(intent, 100);
	}

	public void select(View v) {
		Intent intent = new Intent();
		// Show only images, no videos or anything else
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		// Always show the chooser (if there are multiple options available)
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),
				PICK_IMAGE_REQUEST);
	}

	public void camera(View v) {
		Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(takePicture, 0);// zero can be replaced with any
												// action code

		/*
		 * Intent pickPhoto = new Intent(Intent.ACTION_PICK,
		 * android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		 * startActivityForResult(pickPhoto , 1);//one can be replaced with any
		 * action code
		 */}

	// From Camera
	@Override
	/*
	 * protected void onActivityResult(int requestCode, int resultCode, Intent
	 * data) { super.onActivityResult(requestCode, resultCode, data); if
	 * (resultCode == RESULT_OK) { Bundle extras = data.getExtras(); Bitmap
	 * bitmap = (Bitmap) data.getExtras().get("data");
	 * 
	 * imageview.setImageBitmap(bitmap); ByteArrayOutputStream stream = new
	 * ByteArrayOutputStream(); bitmap.compress(Bitmap.CompressFormat.PNG, 100,
	 * stream); byte[] byteArray = stream.toByteArray(); } }
	 */
	// From Gallery
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
				&& data != null && data.getData() != null) {

			Uri uri = data.getData();

			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						getContentResolver(), uri);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
				img = bos.toByteArray();
				imageview.setImageBitmap(bitmap);
				// Bitmap bmap = BitmapFactory.decodeResource(getResources(),
				// R.drawable.a);
				// String encodedImageData
				// =getEncoded64ImageStringFromBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void retrieve(View v) {

		try {

			String[] col = { "image" };
			c = db.query("tableimage", col, null, null, null, null, null);

			if (c != null) {
				c.moveToLast();
				do {
					img = c.getBlob(c.getColumnIndex("image"));
				} while (c.moveToNext());
			}
			Bitmap b1 = BitmapFactory.decodeByteArray(img, 0, img.length);

			imageview.setImageBitmap(b1);
			Toast.makeText(this, "Retrive successfully", Toast.LENGTH_SHORT)
					.show();

		} catch (Exception e) {
			String xError = e.toString();
			Toast.makeText(this, xError, Toast.LENGTH_SHORT).show();
		}
	}

	public void clear(View v) {
		db.delete("tableimage", null, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
