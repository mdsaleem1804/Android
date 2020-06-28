package com.example.ag_and_015_handwaeving;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_015_handwaving";
	String xExpensesTable,xReminderTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {


			String xPasswordGeneration = "create table passwordgeneration " + "(name varchar(50),"
					+ "password varchar (10)," + "status varchar(10))";

			db.execSQL(xPasswordGeneration);
			
			
		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP TABLE IF EXISTS " + xExpensesTable);
		onCreate(db);
	}
	
	public boolean fn_PasswordCreation(String xName, String xPassword, String xStatus) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("name", xName);
			contentValues.put("password", xPassword);
			contentValues.put("status", xStatus);

			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("passwordgeneration", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}
	
	
	public boolean fn_SetPassword(String xName) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("status", "1");
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues contentValues1 = new ContentValues();
			contentValues1.put("status", "0");
			
			db.update("passwordgeneration", contentValues1,null, null);
			
			db.update("passwordgeneration", contentValues, "name='"
					+ xName + "'", null);
			
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}
	
public boolean fn_LoginValidation(String xName,String xPassword) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from passwordgeneration where name='"+xName+"' and password='"+xPassword+"' and status='1'"; 
		try{
		Cursor cursor = db.rawQuery(xQry,null);

		 if(cursor.getCount()==1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return true;
	        }
		
		// closing connection
		cursor.close();
		db.close();
		}
		catch(Exception e)
		{
			//String xError=e.toString();
		}

		// returning lables
		return false;
	}

	public List<String> fn_GetPasswords() {
		List<String> xPasswords = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from passwordgeneration"; 
		try{
		Cursor cursor = db.rawQuery(xQry,null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				xPasswords.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		
		// closing connection
		cursor.close();
		db.close();
		
		}
		catch(Exception e)
		{
			String xError=e.toString();
		}

		// returning lables
		return xPasswords;
	}


}

/*


*/