package com.example.ag_and_014_a_speechbasedsms;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SpeechBasedSms extends Activity {

	private ImageButton btnMicrophone;
	final int PICK_CONTACT = 1;
	EditText xEdtMobileNo, xEdtSmsContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speechbasedsms);
		btnMicrophone = (ImageButton) findViewById(R.id.btn_mic);
		xEdtMobileNo = (EditText) findViewById(R.id.fedtMobileNo);
		xEdtSmsContent = (EditText) findViewById(R.id.fedtSmsContent);
		btnMicrophone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startSpeechToText();
			}
		});
	}

	public void sendsms(View v) {
		sendSMS(xEdtMobileNo.getText().toString(), xEdtSmsContent.getText()
				.toString());
	}

	public void sendSMS(String phoneNo, String msg) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
			Toast.makeText(getApplicationContext(), "Message Sent",
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}

	public void choosecontacts(View v) {
		// Declare

		Intent intent = new Intent(Intent.ACTION_PICK,
				ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(intent, 1);

	}

	/**
	 * Start speech to text intent. This opens up Google Speech Recognition API
	 * dialog box to listen the speech input.
	 * */
	private void startSpeechToText() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...");
		try {
			startActivityForResult(intent, 2);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(
					getApplicationContext(),
					"Sorry! Speech recognition is not supported in this device.",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Callback for speech recognition activity
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		case 1: {
			if (resultCode == Activity.RESULT_OK) {

				Uri contactData = data.getData();
				Cursor c = managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {

					String id = c
							.getString(c
									.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

					String hasPhone = c
							.getString(c
									.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

					if (hasPhone.equalsIgnoreCase("1")) {
						Cursor phones = getContentResolver()
								.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
										null,
										ContactsContract.CommonDataKinds.Phone.CONTACT_ID
												+ " = " + id, null, null);
						phones.moveToFirst();
						String cNumber = phones.getString(phones
								.getColumnIndex("data1"));
						xEdtMobileNo.setText(cNumber);
						System.out.println("number is:" + cNumber);
					}
					String name = c
							.getString(c
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				}
			}
			break;
		}

		case 2: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				String text = result.get(0);
				xEdtSmsContent.setText(text);
			}
			break;
		}
		}

	}

}