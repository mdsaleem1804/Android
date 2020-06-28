package com.example.busapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DbConnection extends SQLiteOpenHelper{
    
	   public DbConnection(Context context, String dbname, CursorFactory factory, int dbversion) {
	       super(context, dbname, factory, dbversion);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	       db.execSQL("create table frommsu(fromarea text,toarea text,busnumber text,time text,delaytime text);");
	   db.execSQL("create table tomsu(fromarea text,toarea text,busnumber text,time text,delaytime text);");
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	       
	   }
	   
	   public boolean  insertfrom(String xbustime,String xbusdelaytime, String xbusnumber) {
			
			SQLiteDatabase db = this.getWritableDatabase();
			String sql1 = "insert into frommsu  values('" + xbustime
					+ "','" + xbusdelaytime +"','" + xbusnumber +"');";
			db.execSQL(sql1);
			return true;
		}
}