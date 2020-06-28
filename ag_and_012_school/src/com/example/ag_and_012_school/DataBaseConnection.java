package com.example.ag_and_012_school;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseConnection extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ag_and_012_school.db";
	String xStudentRegistrationTable, xAttendenceTable, xMarkTable;

	public DataBaseConnection(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {

			xStudentRegistrationTable = "create table studentregistration "
					+ "(studentname varchar(50),"
					+ "department varchar(50),"
					+ "year varchar(50),"
					+ "mobileno varchar(50),gender varchar(50),rollno varchar(50),regno varchar(50),username varchar(50) ,"
					+ "password varchar(50))";
			db.execSQL(xStudentRegistrationTable);

			xAttendenceTable = "create table attendence "
					+ "(studentname varchar(50)," + "date varchar(50),"
					+ "status varchar(50))";
			db.execSQL(xAttendenceTable);

			xMarkTable = "create table mark " + "(studentname varchar(50),"
					+ "subject1 varchar(50)," + "subject2 varchar(50),"
					+ "subject3 varchar(50) ," + "subject4 varchar(50),"
					+ "subject5 varchar(50))";
			db.execSQL(xMarkTable);

		} catch (Exception e) {

		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + xStudentRegistrationTable);
		db.execSQL("DROP TABLE IF EXISTS " + xAttendenceTable);
		db.execSQL("DROP TABLE IF EXISTS " + xMarkTable);
		onCreate(db);
	}

	public boolean fn_GetUsers(String xUserName, String xPassword) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM studentregistration where username='"
					+ xUserName + "' and password='" + xPassword + "';";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(query, null);
			if (cursor.getCount() > 0) {
				result = true;
			}
		} catch (Exception e) {
			String xError = e.toString();
		}

		return result;
	}

	public boolean fn_GetStudentAttenWithDate(String xStudentName, String xDate) {

		boolean result = false;
		try {
			String query = "SELECT  * FROM attendence where studentname='"
					+ xStudentName + "' and date='" + xDate + "';";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(query, null);
			if (cursor.getCount() > 0) {
				result = true;
			}
		} catch (Exception e) {
			String xError = e.toString();
		}

		return result;
	}

	public ArrayList<String> fn_GetStudents() {

		ArrayList<String> my_array = new ArrayList<String>();
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor allrows = db.rawQuery("SELECT * FROM studentregistration",
					null);
			System.out.println("COUNT : " + allrows.getCount());

			if (allrows.moveToFirst()) {
				do {

					String NAME = allrows.getString(0);
					my_array.add(NAME);

				} while (allrows.moveToNext());
			}
			allrows.close();
			db.close();
		} catch (Exception e) {

		}
		return my_array;
	}

	public Boolean fn_InsertStudentRegistration(String xStudentName,
			String xDepartment, String xYear, String xMobileNo, String xGender,
			String xRollNo, String xRegNo, String xUserName, String xPassword) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("studentname", xStudentName);
			contentValues.put("department", xDepartment);
			contentValues.put("year", xYear);
			contentValues.put("username", xUserName);
			contentValues.put("password", xPassword);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("studentregistration", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public Boolean fn_InsertAttendenceEntry(String xStudentName, String xDate,
			String xStatus) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("studentname", xStudentName);
			contentValues.put("date", xDate);
			contentValues.put("status", xStatus);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("attendence", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public Boolean fn_InsertMarkEntry(String xStudentName, String xSubject1,
			String xSubject2, String xSubject3, String xSubject4,
			String xSubject5) {

		try {

			ContentValues contentValues = new ContentValues();
			contentValues.put("studentname", xStudentName);
			contentValues.put("subject1", xSubject1);
			contentValues.put("subject2", xSubject2);
			contentValues.put("subject3", xSubject3);
			contentValues.put("subject4", xSubject4);
			contentValues.put("subject5", xSubject5);
			SQLiteDatabase db = this.getWritableDatabase();
			db.insert("mark", null, contentValues);
		} catch (Exception e) {
			e.toString();
		}
		return true;
	}

	public List<String> fn_CheckStudents(String xWhereCondition) {
		List<String> xExpenses = new ArrayList<String>();
		SQLiteDatabase db = this.getWritableDatabase();
		String xQry = "Select * from studentregistration " + xWhereCondition;
		Cursor cursor = db.rawQuery(xQry, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				xExpenses.add(cursor.getString(0) + "- " + cursor.getString(1)
						+ "- " + cursor.getString(2));
			} while (cursor.moveToNext());
		}

		// closing connection
		cursor.close();
		db.close();

		// returning lables
		return xExpenses;
	}
}

/*


*/