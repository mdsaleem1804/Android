package com.example.ag_and_017_banking;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class AdminMainPage extends Activity {
	ListView listAccounts;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminmainpage);
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return Utils.inflateMenu(this,menu);
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
		return  Utils.handleMenuOption(this,item);
	}
	
	public void addAccount(View v)
	{
		Intent intent = new Intent(this,AddAccount.class);
    	startActivity(intent);
	}
	
	public void addTransaction(View v)
	{
		Intent intent = new Intent(this,AddTransaction.class);
    	startActivity(intent);
	}
	
	public void listAccount(View v)
	{
		Intent intent = new Intent(this,ListAccounts.class);
    	startActivity(intent);
	}
	
	
	public void recentTransactions(View v)
	{
		Intent intent = new Intent(this,ListRecentTransactions.class);
    	startActivity(intent);
	}
	public void searchTransactions(View v)
	{
		Intent intent = new Intent(this,SearchTransactions.class);
    	startActivity(intent);
	}
}

