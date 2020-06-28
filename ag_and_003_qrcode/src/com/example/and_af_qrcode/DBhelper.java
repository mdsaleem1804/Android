package com.example.and_af_qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "and_af_qrcode.db";
	String  xLogin,xRegistration;

	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// TABLE CREATION
		
		// ---------- Category Table ------------
		xRegistration = "create table tbl_registration " + "(userno integer,"
				+ "fullname varchar(100)," + "mobileno varchar(100)," + "username varchar(100),"
				+ "password varchar(100))";


		// ------------Executing SQL------------------
		db.execSQL(xRegistration);

	}

	// --------------UPGRADING------------------
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS tbl_login");
		onCreate(db);
	}



	// ---------------LOGIN TABLE-------------------------
	public boolean insertRegistration(String xFullName, String xMobileNo,String xUserName, String xPassWord) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues xRegistration = new ContentValues();
		xRegistration.put("userno", 1);
		xRegistration.put("fullname", xFullName);
		xRegistration.put("mobileno", xMobileNo);
		xRegistration.put("username", xUserName);
		xRegistration.put("password", xPassWord);
		db.insert("tbl_registration", null, xRegistration);
		return true;
	}
	public boolean updateRegistration( String xOldPassWord,String xNewPassword) {
		SQLiteDatabase db = this.getWritableDatabase();
		//ContentValues values=new ContentValues();
		//values.put("password",xNewPassword);
		//db.update("tbl_registration",values,"password='xNewPassword' ",null);
		String xQry="UPDATE tbl_registration SET password='"+xNewPassword+
				"' WHERE password='"+xOldPassWord+"'";
		db.execSQL(xQry);
		return true;
	}
	


	// ---------------------To Retrive Maximum Id ------------------
	int fn_GetMaxID(String xQuery) {
		int xMaxValue = 1;
		try {
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(xQuery, new String[] {});
			if (cursor != null)
				if (cursor.moveToFirst()) {

					xMaxValue = cursor.getInt(0);

				}
			// cursor.close();

			return xMaxValue;
		} catch (Exception e) {

			return 1;
		}
		
	}
	//Check Users
	public boolean CheckUsers(String username,String Password)
	{
		
		boolean result = false;
		String query = "SELECT  * FROM tbl_registration where username='"+username + "' and password='"+Password +"';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.getCount()>0)
		{
			result=true;
		}
		return result;
				
	}


}