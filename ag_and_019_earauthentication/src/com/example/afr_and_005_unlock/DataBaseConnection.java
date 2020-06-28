package com.example.afr_and_005_unlock;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "unlock.db";
	String xRegistrationTable,xUserImageDetailsTable,xColorDetailsTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try
		{
		xRegistrationTable = "create table registration "
				+ "(name varchar(20)," + "gender varchar (10),"
				+ "dmy varchar(10)," + "age int(11),"
				+ "username varchar(250)," + "password varchar(25) )";
		db.execSQL(xRegistrationTable);
		xUserImageDetailsTable="create table userimagedetails(image blob,groupname varchar(10),username varchar(25),patientremarks varchar(100),doctorremarks varchar(10))";
		db.execSQL(xUserImageDetailsTable);
		xColorDetailsTable="create table colordetails(topleft varchar(100),topright varchar(100),bottomleft varchar(100),bottomright varchar(100))";
		db.execSQL(xColorDetailsTable);
		
		}
		catch(Exception e)
		{
			Log.e("Error", e.toString());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS " + xRegistrationTable);
		 db.execSQL("DROP TABLE IF EXISTS userimagedetails" );
		 db.execSQL("DROP TABLE IF EXISTS colordetails" );
		 onCreate(db);
	}


	public boolean  InsertUsers(product prod) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		String sql1 = "insert into registration  values('" + prod.getName()
				+ "','" + prod.getGender() + "','" + prod.getDmy() + "',"
				+ prod.getAge() + ",'" + prod.getUsername() + "','"
				+ prod.getPassword() + "');";
		db.execSQL(sql1);
		return true;
	}


	public boolean CheckUsers(String username,String Password)
	{
		
		boolean result = false;
		String query = "SELECT  * FROM registration where username='"+username + "' and password='"+Password +"';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.getCount()>0)
		{
			result=true;
		}
		return result;
				
	}
	
	  public List<String> getAllLabels(){
	    	List<String> labels = new ArrayList<String>();
	    	
	        // Select All Query
	        String selectQuery = "SELECT  username FROM registration";
	     
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	labels.add(cursor.getString(0));
	            } while (cursor.moveToNext());
	        }
	        
	        // closing connection
	        cursor.close();
	        db.close();
	    	
	    	// returning lables
	    	return labels;
	    }
	  public Cursor fetchProfileImageFromDatabase()
	    {
		     SQLiteDatabase db = this.getReadableDatabase();
	        return  db.rawQuery("SELECT image FROM userimagedetails " , null);
	    }
	  public Cursor fetchTopLeft()
	    {
		     SQLiteDatabase db = this.getReadableDatabase();
	        return  db.rawQuery("SELECT topleft FROM colordetails " , null);
	    }
	
	  public List<String> getPatientRemarks(String username){
	    	List<String> labels = new ArrayList<String>();
	    	
	        // Select All Query
	        String selectQuery = "SELECT  patientremarks FROM userimagedetails where username='"+username+"'";
	     
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	            	labels.add(cursor.getString(0));
	            } while (cursor.moveToNext());
	        }
	        
	        // closing connection
	        cursor.close();
	        db.close();
	    	
	    	// returning lables
	    	return labels;
	    }
//	public boolean CheckMobileNo(String mobileno)
//	{
//		Long  xMobileno;
//		xMobileno=Long.valueOf(mobileno);
//		boolean result = false;
//		String query = "SELECT  * FROM registration where mobileno="+xMobileno + " ;";
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(query, null);
//		if(cursor.getCount()>0)
//		{
//			result=true;
//		}
//		return result;
//				
//	}
}