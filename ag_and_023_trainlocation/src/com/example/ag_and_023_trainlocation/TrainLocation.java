package com.example.ag_and_023_trainlocation;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TrainLocation extends Activity {
	private TextView xTxtStep1, xTxtStep2, xTxtStep3, xTxtStep4, xTxtStep5;
	private ImageButton xBtnSpeak1;
	private final int REQ_CODE_SPEECH_INPUT = 100;
	DataBaseConnection mCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trainlocation);
		mCon = new DataBaseConnection(this);
		xTxtStep1 = (TextView) findViewById(R.id.fTxtSpeak1Output);
		xTxtStep2 = (TextView) findViewById(R.id.fTxtSpeak2Output);
		xTxtStep3 = (TextView) findViewById(R.id.fTxtSpeak3Output);
		xTxtStep4 = (TextView) findViewById(R.id.fTxtSpeak4Output);
		xTxtStep5 = (TextView) findViewById(R.id.fTxtSpeak5Output);
		xBtnSpeak1 = (ImageButton) findViewById(R.id.btnSpeak1);

		// hide the action bar
		getActionBar().hide();

	}

	public void speak1(View v) {
		promptSpeechInput(1);
	}

	public void speak2(View v) {
		promptSpeechInput(2);
	}

	public void speak3(View v) {
		promptSpeechInput(3);
	}

	public void speak4(View v) {
		promptSpeechInput(4);
	}

	public void speak5(View v) {
		promptSpeechInput(5);
	}

	public void savedata() {
		if (xTxtStep1.getText().toString().matches("")) {
			xTxtStep1.setError("Please Speak for Step1");
			return;
		}
		if (xTxtStep2.getText().toString().matches("")) {
			xTxtStep2.setError("Please Speak for Step2");
			return;
		}
		if (xTxtStep3.getText().toString().matches("")) {
			xTxtStep3.setError("Please Speak for Step3");
			return;
		}
		if (xTxtStep4.getText().toString().matches("")) {
			xTxtStep4.setError("Please Speak for Step4");
			return;
		}

		if (xTxtStep5.getText().toString().matches("")) {
			xTxtStep5.setError("Please Speak for Step5");
			return;
		}
		if (mCon.insertTrainingDetails(xTxtStep1.getText().toString(),
				xTxtStep2.getText().toString(), xTxtStep3.getText().toString(),
				xTxtStep4.getText().toString(), xTxtStep5.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Inserted", 1000).show();
		}
	}

	/**
	 * Showing google speech input dialog
	 * */
	private void promptSpeechInput(int xSpeechInput) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				getString(R.string.speech_prompt));
		try {
			startActivityForResult(intent, xSpeechInput);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.speech_not_supported),
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Receiving speech input
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 1: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				xTxtStep1.setText(result.get(0));
			}
			break;
		}
		case 2: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				xTxtStep2.setText(result.get(0));
			}
			break;
		}
		case 3: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				xTxtStep3.setText(result.get(0));
			}
			break;
		}
		case 4: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				xTxtStep4.setText(result.get(0));
			}
			break;
		}

		case 5: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> result = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				xTxtStep5.setText(result.get(0));
			}
			break;
		}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
