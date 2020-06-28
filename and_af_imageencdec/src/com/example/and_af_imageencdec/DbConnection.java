package com.example.and_af_imageencdec;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DbConnection extends SQLiteOpenHelper{
    
   public DbConnection(Context context, String dbname, CursorFactory factory, int dbversion) {
       super(context, dbname, factory, dbversion);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // db.execSQL("create table tableimage(image blob);");
       db.execSQL("CREATE TABLE `encrypted` (`imagetext`	TEXT,`otpvalue`	TEXT);");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       
   }

}