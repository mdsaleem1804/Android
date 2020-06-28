package com.example.ag_and_010_shoppingcart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VendorMainPage extends Activity {
	String xVendorName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vendormainpage);
		Bundle xBundle = new Bundle();
		xBundle = getIntent().getExtras();
		xVendorName = xBundle.getString("vendorname");
	}

	public void addnewproduct(View v) {
		Intent intent = new Intent(VendorMainPage.this, AddNewProduct.class);
		intent.putExtra("vendorname", xVendorName);
		startActivity(intent);
	}
	public void listcart(View v) {
		Intent intent = new Intent(VendorMainPage.this, ListCart.class);
		intent.putExtra("vendorname", xVendorName);
		startActivity(intent);
	}
	public void stock(View v) {
		Intent intent = new Intent(VendorMainPage.this, ListStock.class);
		intent.putExtra("vendorname", xVendorName);
		startActivity(intent);
	}
}