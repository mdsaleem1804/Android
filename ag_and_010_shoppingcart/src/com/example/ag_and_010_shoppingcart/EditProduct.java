package com.example.ag_and_010_shoppingcart;

import java.io.ByteArrayInputStream;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditProduct extends Activity {

	EditText xEdtAddProductName, xEdtAddProductPrice,xEdtAddProductQty,xEdtAddProductDescription;
	Button xBtnAddProduct, xBtnAddCart;
	String xGetProductName, xGetUserName, xGetVenderName;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
	ImageView xProductImg;
	String xProductPrice = "";
	int xCurrentQty=0;
	int xGetQty=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproduct);
		mCon = new DataBaseConnection(this);
		openDatabase();
		try {
			xEdtAddProductName = (EditText) findViewById(R.id.fAddEdtProductName);
			xEdtAddProductPrice = (EditText) findViewById(R.id.fAddEdtProductPrice);
			xEdtAddProductQty = (EditText) findViewById(R.id.fAddEdtProductQty);
			xEdtAddProductDescription = (EditText) findViewById(R.id.fEdtDescription);
			xBtnAddProduct = (Button) findViewById(R.id.fBtnAddtoProduct);
			xBtnAddCart = (Button) findViewById(R.id.fBtnAddtoCart);
			xProductImg = (ImageView) findViewById(R.id.imageView1);
			xBtnAddProduct.setVisibility(View.GONE);
			xBtnAddCart.setVisibility(View.VISIBLE);

			Bundle xBundle = new Bundle();
			xBundle = getIntent().getExtras();
			xGetUserName = xBundle.getString("username");
			xGetVenderName = xBundle.getString("vendername");
			xGetProductName = xBundle.getString("productname");

			xProductPrice = "";
			xEdtAddProductName.setFocusable(false);
			xEdtAddProductPrice.setFocusable(false);
			xEdtAddProductDescription.setFocusable(false);
			xEdtAddProductName.setText(xGetProductName);
			xEdtAddProductPrice.setText(xProductPrice);
			String xQry = "Select * from product where pname='"
					+ xGetProductName + "' and pvendorname='" + xGetVenderName
					+ "'";
			xCursor = db.rawQuery(xQry, null);
			xCursor.moveToFirst();
			showRecords();

		} catch (Exception e) {
			String xErroe = e.toString();
		}
	}

	public void addcart(View v) {
		xGetQty=Integer.valueOf(xEdtAddProductQty.getText().toString());
		int xQty=xCurrentQty-xGetQty;
		if(mCon.fn_InsertCart(xEdtAddProductName.getText().toString(),
				xEdtAddProductPrice.getText().toString(), xGetVenderName,
				xGetUserName)){
		Toast.makeText(getApplicationContext(), "Inserted Succesfully", 1000)
				.show();
		mCon.fn_UpdateProduct(xEdtAddProductName.getText().toString(), xQty);
		}
		
		xEdtAddProductName.setText("");
		xEdtAddProductPrice.setText("");
		xEdtAddProductQty.setText("");
		xEdtAddProductDescription.setText("");
	}

	protected void showRecords() {

		byte[] photo = xCursor.getBlob(xCursor.getColumnIndex("image"));
		ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		xProductImg.setImageBitmap(theImage);
		xEdtAddProductPrice.setText(xCursor.getString(1));
		xCurrentQty=Integer.valueOf(xCursor.getString(2));
		xEdtAddProductDescription.setText(xCursor.getString(5));
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_010_shoppingcart",
				Context.MODE_PRIVATE, null);
	}

}
