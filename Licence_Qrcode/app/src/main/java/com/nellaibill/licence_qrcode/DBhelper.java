package com.nellaibill.licence_qrcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "licence_qrcode.db";
	String  xLogin,xLicenceDetails;

	public DBhelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// TABLE CREATION
		
		// ---------- Category Table ------------
		xLicenceDetails = "create table tbl_licenecdetails " + "(id integer,"
				+ "qrcodetext varchar(100))";


		// ------------Executing SQL------------------
		db.execSQL(xLicenceDetails);

	}

	// --------------UPGRADING------------------
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS tbl_login");
		onCreate(db);
	}



	// ---------------LOGIN TABLE-------------------------
	public boolean insertRegistration(int xMaxId,String xQrCodeDetails) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues xContentValues = new ContentValues();
		xContentValues.put("id", xMaxId);
		xContentValues.put("qrcodetext", xQrCodeDetails);
		db.insert("tbl_licenecdetails", null, xContentValues);
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
	public boolean CheckUsers(String xQrText)
	{
		
		boolean result = false;
		String query = "SELECT  * FROM tbl_licenecdetails where qrcodetext='"+xQrText + "';";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.getCount()>0)
		{
			result=true;
		}
		return result;
				
	}


}