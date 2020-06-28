package com.example.ag_and_008_smallscale;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddNewProduct extends Activity {

	DataBaseConnection mCon;
	EditText xEdtAddProductName, xEdtAddProductPrice;
	String xVendorName = "";
	Button xBtnAddProduct, xBtnAddCart;
	ImageView xProductImg;
	private static final int CAMERA_REQUEST = 1888;
	private byte[] xByteImage = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproduct);
		mCon = new DataBaseConnection(this);
		xEdtAddProductName = (EditText) findViewById(R.id.fAddEdtProductName);
		xEdtAddProductPrice = (EditText) findViewById(R.id.fAddEdtProductPrice);
		xBtnAddProduct = (Button) findViewById(R.id.fBtnAddtoProduct);
		xBtnAddCart = (Button) findViewById(R.id.fBtnAddtoCart);
		xProductImg = (ImageView) findViewById(R.id.imageView1);
		Bundle xBundle = new Bundle();
		xBundle = getIntent().getExtras();
		xVendorName = xBundle.getString("vendorname");
		xBtnAddProduct.setVisibility(View.VISIBLE);
		xBtnAddCart.setVisibility(View.GONE);
	}

	public void addproduct(View v) {
		mCon.fn_InsertProductDetails(xEdtAddProductName.getText().toString(),
				xEdtAddProductPrice.getText().toString(), xVendorName,
				xByteImage);
		Toast.makeText(getApplicationContext(), "Inserted Succesfully", 1000)
				.show();
		xEdtAddProductName.setText("");
		xEdtAddProductPrice.setText("");
	}

	public void chooseimage(View v) {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);

		/*
		 * Intent in = new Intent(Intent.ACTION_PICK,
		 * android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		 * in.putExtra("crop", "true"); in.putExtra("outputX", 100);
		 * in.putExtra("outputY", 100); in.putExtra("scale", true);
		 * in.putExtra("return-data", true);
		 * 
		 * startActivityForResult(in, 1);
		 */
	}

	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { // TODO Auto-generated method stub
	 * super.onActivityResult(requestCode, resultCode, data);
	 * 
	 * if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
	 * 
	 * Bitmap bmp = (Bitmap) data.getExtras().get("data");
	 * 
	 * xProductImg.setImageBitmap(bmp); // btnadd.requestFocus();
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos); byte[] b =
	 * baos.toByteArray(); String encodedImageString = Base64 .encodeToString(b,
	 * Base64.DEFAULT);
	 * 
	 * byte[] bytarray = Base64.decode(encodedImageString, Base64.DEFAULT);
	 * Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
	 * bytarray.length);
	 * 
	 * }
	 * 
	 * }
	 */

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			// Drawable d = new BitmapDrawable(getResources(), photo);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
			xByteImage = bos.toByteArray();
			xProductImg.setImageBitmap(photo);

		}
	}
}
