package com.example.ag_and_015_handwaeving;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NameEdit extends Activity{

	private SQLiteDatabase db;
	DataBaseConnection mCon;
	EditText xEdtName,xEdtPassword;
	Button xBtnNameGeneration,xBtnSetPassword;
	String xSelectedPasswordName;
	Cursor xCursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.namecreation);
		mCon=new DataBaseConnection(this);
		xEdtName=(EditText)findViewById(R.id.fedtName);
		xEdtPassword=(EditText)findViewById(R.id.fedtPassword);
		xBtnSetPassword=(Button)findViewById(R.id.fBtnSetPassword);
		xBtnNameGeneration=(Button)findViewById(R.id.fBtnNameGeneration);
	
		Bundle b = new Bundle();
		b = getIntent().getExtras();
		openDatabase();
		xSelectedPasswordName = b.getString("passwordname");
		xEdtName.setText(xSelectedPasswordName);
		String xQry = "Select * from passwordgeneration where name='"+xSelectedPasswordName+"'";
		xCursor = db.rawQuery(xQry, null);
		xCursor.moveToFirst();
		showRecords();
		xBtnNameGeneration.setVisibility(View.GONE);
		xBtnSetPassword.setVisibility(View.VISIBLE);
		
	}
	
	protected void openDatabase() {
		db = openOrCreateDatabase("ag_and_015_handwaving",
				Context.MODE_PRIVATE, null);
	}
	
	protected void showRecords() {
		xEdtName.setText(xCursor.getString(0));
		xEdtPassword.setText(xCursor.getString(1));
	}
public void setpassword(View v)
{
	if(mCon.fn_SetPassword(xEdtName.getText().toString()))
	{
		Toast.makeText(getApplicationContext(),"Data Modified", 1000).show();
		fn_ClearNmeCreatoinValues();
	}
	else
	{
		Toast.makeText(getApplicationContext(),"Error", 1000).show();
	}
	
}
public void fn_ClearNmeCreatoinValues()
{
	xEdtName.setText("");
	xEdtPassword.setText("");
}
}
