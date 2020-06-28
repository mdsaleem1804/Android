package com.example.ag_and_011_smsencryption;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Sender extends Activity {
	EditText xEdtMessage, xEdtKeyValue, xEdtSenderNo;
	AlertDialog.Builder alertDialogBuilder;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sender);
		xEdtMessage = (EditText) findViewById(R.id.fedt_message);
		xEdtSenderNo = (EditText) findViewById(R.id.fedt_senderno);
		xEdtKeyValue = (EditText) findViewById(R.id.fedt_keyvalue);
		xEdtKeyValue.setEnabled(false);
		xEdtSenderNo.setInputType(InputType.TYPE_CLASS_NUMBER);
		InputFilter[] FilterArray = new InputFilter[1];
		FilterArray[0] = new InputFilter.LengthFilter(10);
		xEdtSenderNo.setFilters(FilterArray);
		alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder
				.setMessage("Are you sure, Encrypted Text Will Place into Tex box for further proceedings");
		alertDialogBuilder.setPositiveButton("yes",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						fn_EncodeText();
					}
				});

		alertDialogBuilder.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});

	}

	public void encrypttext(View v) {
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	public void generatekey(View v) {
		try {
			/*
			 * progress = new ProgressDialog(this);
			 * progress.setTitle("Please Wait!!");
			 * progress.setMessage("Wait!!"); progress.setCancelable(true);
			 * progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); int
			 * delay = 1000;// in ms=5 SECS
			 * 
			 * Timer timer = new Timer();
			 * 
			 * timer.schedule(new TimerTask() { public void run() {
			 * progress.show(); } }, delay); progress.dismiss();
			 */
			xEdtKeyValue.setText("" + ((int) (Math.random() * 9000) + 1000));
		} catch (Exception e) {
			String xError = e.toString();
		}
	}

	public void sendtoreciever(View v) {
		if (TextUtils.isEmpty(xEdtMessage.getText().toString())) {
			xEdtMessage.setError("Please Enter Message");
			return;
		}
		if (TextUtils.isEmpty(xEdtKeyValue.getText().toString())) {
			xEdtKeyValue.setError("Generate Key");
			return;
		}
		if (TextUtils.isEmpty(xEdtSenderNo.getText().toString())) {
			xEdtSenderNo.setError("Sender No Required");
			return;
		}
		if(xEdtSenderNo.getText().toString().length() <10)
		{
			xEdtSenderNo.setError("Sender No Must be Ten Digits");
			return;
		}
		
		sendSMS(xEdtSenderNo.getText().toString(), xEdtKeyValue.getText()
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

	public void fn_EncodeText() {

		// Set up secret key spec for 128-bit AES encryption and decryption
		SecretKeySpec sks = null;
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.setSeed("any data used as random seed".getBytes());
			KeyGenerator kg = KeyGenerator.getInstance("AES");
			kg.init(128, sr);
			sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
		} catch (Exception e) {
			// Log.e(TAG, "AES secret key spec error");
		}

		// Encode the original data with AES
		byte[] encodedBytes = null;
		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, sks);
			encodedBytes = c.doFinal(xEdtMessage.getText().toString()
					.getBytes());
		} catch (Exception e) {
			// Log.e(TAG, "AES encryption error");
		}

		xEdtMessage
				.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT));

		// Decode the encoded data with AES
		byte[] decodedBytes = null;
		try {
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, sks);
			decodedBytes = c.doFinal(encodedBytes);
		} catch (Exception e) {
			// Log.e(TAG, "AES decryption error");
		}
		// TextView tvdecoded = (TextView)findViewById(R.id.tvdecoded);
		// tvdecoded.setText("[DECODED]:\n" + new String(decodedBytes) + "\n");
	}
}