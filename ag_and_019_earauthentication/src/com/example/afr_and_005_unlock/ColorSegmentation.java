package com.example.afr_and_005_unlock;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ColorSegmentation extends Activity {
	//Bitmap photo;
	String xColorName1, xColorName2, xColorName3, xColorName4;
	EditText xImgColorName1, xImgColorName2, xImgColorName3, xImgColorName4;
	Map<String, Integer> mColors = new HashMap<String, Integer>();
	Bitmap theImage,photo;

	ImageView imageSource;
	DataBaseConnection mdb;
	private SQLiteDatabase db = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.colorsegmentation);
		mdb = new DataBaseConnection(this);
		db = mdb.getWritableDatabase();
		
		
		try {
			imageSource = (ImageView) findViewById(R.id.imageView1);
			imageSource.setOnTouchListener(imgSourceOnTouchListener);
			photo= BitmapFactory.decodeResource(getResources(),
					R.drawable.wound);
			//imageSource.setImageBitmap(photo);
	
			Cursor cursor = mdb.fetchProfileImageFromDatabase();
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						byte[] data = cursor.getBlob(cursor
								.getColumnIndex("image"));
						ByteArrayInputStream imageStream = new ByteArrayInputStream(
								data);
						theImage = BitmapFactory.decodeStream(imageStream);
						imageSource.setImageBitmap(theImage);

					} while (cursor.moveToNext());
				}
				cursor.close();
			}

			xImgColorName1 = (EditText) findViewById(R.id.edtColorName1);
			xImgColorName2 = (EditText) findViewById(R.id.edtColorName2);
			xImgColorName3 = (EditText) findViewById(R.id.edtColorName3);
			xImgColorName4 = (EditText) findViewById(R.id.edtColorName4);
			// For More Colors You cann Find and add like this
			// -http://cloford.com/resources/colours/500col.htm
			mColors.put("red", Color.rgb(255, 0, 0));
			mColors.put("pink", Color.rgb(255, 192, 203));
			mColors.put("voilet", Color.rgb(36, 10, 64));
			mColors.put("blue", Color.rgb(0, 0, 255));
			mColors.put("green", Color.rgb(0, 255, 0));
			mColors.put("yellow", Color.rgb(255, 255, 0));
			mColors.put("orange", Color.rgb(255, 104, 31));
			mColors.put("white", Color.rgb(255, 255, 255));
			mColors.put("black", Color.rgb(0, 0, 0));
			mColors.put("gray", Color.rgb(128, 128, 128));
			mColors.put("tea", Color.rgb(193, 186, 176));
			mColors.put("cream", Color.rgb(255, 253, 208));
			mColors.put("indigo", Color.rgb(75, 0, 130));
			mColors.put("blueviolet", Color.rgb(138, 43, 226));
			mColors.put("slategray", Color.rgb(112, 128, 144));
			mColors.put("deepskyblue", Color.rgb(0, 191, 255));
			mColors.put("peacock", Color.rgb(51, 161, 201));
			mColors.put("springgreen", Color.rgb(0, 255, 127));
			mColors.put("seagreen", Color.rgb(84, 255, 159));
			mColors.put("mint", Color.rgb(189, 252, 201));
			mColors.put("gold", Color.rgb(255, 215, 0));
			mColors.put("brick", Color.rgb(152, 106, 31));


			/*int touchedRGB1 = photo.getPixel(0, 0);
			int touchedRGB2 = photo.getPixel(0, 100);
			int touchedRGB3 = photo.getPixel(40, 0);
			int touchedRGB4 = photo.getPixel(40, 100);
			xColorName1 = getBestMatchingColorName(touchedRGB1);
			xColorName2 = getBestMatchingColorName(touchedRGB2);
			xColorName3 = getBestMatchingColorName(touchedRGB3);
			xColorName4 = getBestMatchingColorName(touchedRGB4);*/
	
			  xColorName1="red"; xColorName2="blue"; xColorName3="green";
			 xColorName4="blue";
			 
			xImgColorName1.setText(xColorName1);
			xImgColorName2.setText(xColorName2);
			xImgColorName3.setText(xColorName3);
			xImgColorName4.setText(xColorName4);
			ContentValues cv = new ContentValues();
			cv.put("topleft", xColorName1);
			cv.put("topright", xColorName2);
			cv.put("bottomleft", xColorName3);
			cv.put("bottomright", xColorName4);
			// db.delete("userimagedetails", whereClause,null);
			//db.insert("colordetails", null, cv);
		} catch (Exception e) {
			String xError = e.toString();
		}

	}

	 OnTouchListener imgSourceOnTouchListener
	    = new OnTouchListener(){

	  @Override
	  public boolean onTouch(View view, MotionEvent event) {
	   
	   float eventX = event.getX();
	   float eventY = event.getY();
	   float[] eventXY = new float[] {eventX, eventY};
	   
	   Matrix invertMatrix = new Matrix();
	   ((ImageView)view).getImageMatrix().invert(invertMatrix);
	
	   invertMatrix.mapPoints(eventXY);
	   int x = Integer.valueOf((int)eventXY[0]);
	   int y = Integer.valueOf((int)eventXY[1]);
	   /* 
	   touchedXY.setText(
	     "touched position: "
	     + String.valueOf(eventX) + " / " 
	     + String.valueOf(eventY));
	   invertedXY.setText(
	     "touched position: "
	     + String.valueOf(x) + " / " 
	     + String.valueOf(y));
*/
	   Drawable imgDrawable = ((ImageView)view).getDrawable();
	   Bitmap bitmap = ((BitmapDrawable)imgDrawable).getBitmap();
	   
	   /*
	   imgSize.setText(
	     "drawable size: "
	     + String.valueOf(bitmap.getWidth()) + " / " 
	     + String.valueOf(bitmap.getHeight()));
	   */
	   //Limit x, y range within bitmap
	   if(x < 0){
	    x = 0;
	   }else if(x > bitmap.getWidth()-1){
	    x = bitmap.getWidth()-1;
	   }
	   
	   if(y < 0){
	    y = 0;
	   }else if(y > bitmap.getHeight()-1){
	    y = bitmap.getHeight()-1;
	   }

	   int touchedRGB = bitmap.getPixel(x, y);
	   ContentValues cv = new ContentValues();
		cv.put("topleft", getBestMatchingColorName(touchedRGB));
		cv.put("topright", "");
		cv.put("bottomleft", "");
		cv.put("bottomright", "");
		db.insert("colordetails", null, cv);
		if(getBestMatchingColorName(touchedRGB).equalsIgnoreCase("brick"))
		{
			  Toast.makeText(getApplicationContext(), "Ear Identified", Toast.LENGTH_LONG).show();
			  Intent intent = new Intent(ColorSegmentation.this,
						WelcomePage.class);
				startActivity(intent);
		}
		if(getBestMatchingColorName(touchedRGB).equalsIgnoreCase("grey"))
		{
			  Intent intent = new Intent(ColorSegmentation.this,
						WelcomePage.class);
				startActivity(intent);
		}
		
		if(getBestMatchingColorName(touchedRGB).equalsIgnoreCase("grey"))
		{
			  Toast.makeText(getApplicationContext(), "SOme Problem Occured Validation", Toast.LENGTH_LONG).show();
		}
	   Toast.makeText(getApplicationContext(), "Selected X,Y  is["+x + "," +y+"]", Toast.LENGTH_LONG).show();
	 //  Toast.makeText(getApplicationContext(), "Selected Color is"+getBestMatchingColorName(touchedRGB), Toast.LENGTH_LONG).show();
	   //colorRGB.setText("touched color: " + "#" + Integer.toHexString(touchedRGB));
	   //colorRGB.setTextColor(touchedRGB);
	   
	   return true;
	  }};

	private String getBestMatchingColorName(int pixelColor) {
		// largest difference is 255 for every colour component
		int currentDifference = 3 * 255;
		// name of the best matching colour
		String closestColorName = null;
		// get int values for all three colour components of the pixel
		int pixelColorR = Color.red(pixelColor);
		int pixelColorG = Color.green(pixelColor);
		int pixelColorB = Color.blue(pixelColor);

		Iterator<String> colorNameIterator = mColors.keySet().iterator();
		// continue iterating if the map contains a next colour and the
		// difference is greater than zero.
		// a difference of zero means we've found an exact match, so there's no
		// point in iterating further.
		while (colorNameIterator.hasNext() && currentDifference > 0) {
			// this colour's name
			String currentColorName = colorNameIterator.next();
			// this colour's int value
			int color = mColors.get(currentColorName);
			// get int values for all three colour components of this colour
			int colorR = Color.red(color);
			int colorG = Color.green(color);
			int colorB = Color.blue(color);
			// calculate sum of absolute differences that indicates how good
			// this match is
			int difference = Math.abs(pixelColorR - colorR)
					+ Math.abs(pixelColorG - colorG)
					+ Math.abs(pixelColorB - colorB);
			// a smaller difference means a better match, so keep track of it
			if (currentDifference > difference) {
				currentDifference = difference;
				closestColorName = currentColorName;
			}
		}
		return closestColorName;
	}
}
