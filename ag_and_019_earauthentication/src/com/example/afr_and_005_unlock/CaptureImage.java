
/*
 * Healing wounds unfolds in different phases:

Exsudative phase (the first few minutes to hours)
Shortly after the injury, the body is occupied trying to keep the damage as limited as possible. When tissue is injured, bleeding takes place. In order to minimize the loss of blood, the first objective is to stop the bleeding; blood coagulation sets in and scabs form. The scab closes off the wound to the outside and protects the injured spot against being invaded by germs and potential wound infections. It is possible that the wounded area swells up somewhat (wound edema).

Resorptive phase (days 1 - 3)
Various body cells, proteins and messenger of the immune system find their way to the immediate area of the wound. Phagocytes (scavenger cells) begin to remove the blood and cellular residue which has exuded as well as to fight off potential germs.

Proliferative phase (days 4 - 7)
New skin cells are generated inside the wound, blood vessels grow and connective tissue forms. Depending on the type of wound, the rims may heal more quickly, especially for small incisions, or the skin may granulate in the wound, creating an interim tissue.

Reparative phase (day 7 - 8 months later)
The skin begins to close the wound permanently with new skin cells, thereby making the wound smaller and smaller. The scar tissue becomes paler in colour and less elastic than the surrounding skin; it also lacks perspiration and sabaceous glands.
 * */package com.example.afr_and_005_unlock;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CaptureImage extends Activity implements OnClickListener {
	//Spinner xSpnGroup;
	private ImageView xImageView = null;
	private Button xBtnInsert = null;
	Button xBtnPhoto;
	DataBaseConnection mdb;
	private SQLiteDatabase db = null;
	//String xGetUser,xPatientRemarks;
	//EditText xEdtRemarks;

	
	private byte[] xByteImage = null;
	private String[] xGroupItems = { "Exsudative(Few Mins)", "Resorptive(1-3 Days)", "Proliferative(4-7 Days)", "Reparative 1Week Plus" };
	String xSelectedGroup;
	private static final int CAMERA_REQUEST = 1888;
	//Bitmap bitmap_Source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		xBtnInsert = (Button) findViewById(R.id.button_insert);
		xImageView = (ImageView) findViewById(R.id.viewimage);
		//xSpnGroup = (Spinner) findViewById(R.id.spnGroup);
		xBtnPhoto = (Button) this.findViewById(R.id.button_capture);
		//xEdtRemarks=(EditText) this.findViewById(R.id.edtRemarks);
		//xPatientRemarks="No Remarks";

		//bitmap_Source = BitmapFactory.decodeResource(getResources(),
				//R.drawable.wound1);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			//xGetUser=extras.getString("passuser");
		    
		}
		xBtnPhoto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent cameraIntent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, CAMERA_REQUEST);
			}
		});
		// imageview.setImageResource(0);
		xBtnInsert.setOnClickListener(this);
		mdb = new DataBaseConnection(this);
		db = mdb.getWritableDatabase();
		/*ArrayAdapter<String> xGroupAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, xGroupItems);
		xGroupAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		xSpnGroup.setAdapter(xGroupAdapter);

		xSpnGroup.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				xSelectedGroup = xSpnGroup.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				xSelectedGroup = "Exsudative(Few Mins)";
			}

		});*/
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			//Drawable d = new BitmapDrawable(getResources(), photo);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
			xByteImage = bos.toByteArray();
			xImageView.setImageBitmap(photo);

		}
	}

	@Override
	public void onClick(View arg0) {
		try {

			if (xBtnInsert == arg0) {
				//xPatientRemarks=xEdtRemarks.getText().toString();
				@SuppressWarnings("unused")
				String whereClause = "username" + "=xGetUser";
				ContentValues cv = new ContentValues();
				cv.put("image", xByteImage);
				/*ByteArrayOutputStream bos = new ByteArrayOutputStream();
				bitmap_Source.compress(Bitmap.CompressFormat.PNG, 100, bos);
				xByteImage = bos.toByteArray();
				cv.put("image",xByteImage);*/
				cv.put("groupname", "");
				cv.put("username", "");
				cv.put("patientremarks", "");
				cv.put("doctorremarks", "Not Updated");
				//db.delete("userimagedetails", whereClause,null);
				db.insert("userimagedetails", null, cv);
						   



				Toast.makeText(this, "Image Stored SuccessFully !",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Error", e.toString());

		}
	}


}