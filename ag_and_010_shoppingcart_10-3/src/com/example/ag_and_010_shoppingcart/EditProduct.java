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

	EditText xEdtAddProductName, xEdtAddProductPrice;
	Button xBtnAddProduct, xBtnAddCart;
	String xGetProductName,xGetUserName,xGetVendorName;
	DataBaseConnection mCon;
	private SQLiteDatabase db;
	private Cursor xCursor;
	ImageView xProductImg;
	String xProductPrice="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproduct);
		mCon = new DataBaseConnection(this);
		openDatabase();
		try{
		xEdtAddProductName = (EditText) findViewById(R.id.fAddEdtProductName);
		xEdtAddProductPrice = (EditText) findViewById(R.id.fAddEdtProductPrice);

		xBtnAddProduct = (Button) findViewById(R.id.fBtnAddtoProduct);
		xBtnAddCart = (Button) findViewById(R.id.fBtnAddtoCart);
		xProductImg = (ImageView) findViewById(R.id.imageView1);
		xBtnAddProduct.setVisibility(View.GONE);
		xBtnAddCart.setVisibility(View.VISIBLE);

		Bundle xBundle = new Bundle();
		xBundle = getIntent().getExtras();
		xGetUserName = xBundle.getString("username");
		xGetVendorName = xBundle.getString("vendorname");
		xGetProductName = xBundle.getString("productname");
		
/*		String[] separated = xProductName.split("-");
		xProductName = separated[0];
		xProductPrice = separated[1];
		xVendorName = separated[2];*/
		
		xProductPrice="";
		xEdtAddProductName.setFocusable(false);
		xEdtAddProductPrice.setFocusable(false);
		xEdtAddProductName.setText(xGetProductName);
		xEdtAddProductPrice.setText(xProductPrice);
		String xQry = "Select * from product where pname='"+ xGetProductName +"' and pvendorname='"+xGetVendorName+"'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();
	
		}
		catch(Exception e){
			String xErroe=e.toString();
		}
	}

	public void addcart(View v) {
		mCon.fn_InsertCart(xEdtAddProductName.getText().toString(),xEdtAddProductPrice.getText().toString(), xGetVendorName,xGetUserName);
		Toast.makeText(getApplicationContext(), "Inserted Succesfully", 1000)
				.show();
		xEdtAddProductName.setText("");
		xEdtAddProductPrice.setText("");
	}

	protected void showRecords() {
	
		byte[] photo = xCursor.getBlob(xCursor.getColumnIndex("image"));
		ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
		Bitmap theImage= BitmapFactory.decodeStream(imageStream);
		xProductImg.setImageBitmap(theImage);
		xEdtAddProductPrice.setText(xCursor.getString(1));
	}

	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_010_shoppingcart",
				Context.MODE_PRIVATE, null);
	}

}
