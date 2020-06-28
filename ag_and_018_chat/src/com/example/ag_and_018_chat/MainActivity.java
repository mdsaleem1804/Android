package com.example.ag_and_018_chat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	EditText xEdtChatText;
	String xChatText;
	String id;
	InputStream is = null;
	String result = null;
	String line = null;
	int code;
	double latitude;
	double longitude;
	/** Items entered by the user is stored in this ArrayList variable */
	ArrayList<String> list = new ArrayList<String>();

	/** Declaring an ArrayAdapter to set items to ListView */
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/** Reference to the add button of the layout main.xml */
		Button btn = (Button) findViewById(R.id.btnAdd);
		xEdtChatText = (EditText) findViewById(R.id.txtItem);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		 select();
		/** Reference to the delete button of the layout main.xml */
		// Button btnDel = (Button) findViewById(R.id.btnDel);

		/** Defining the ArrayAdapter to set items to ListView */
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);

		/** Defining a click event listener for the button "Add" */
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				insert();
				list.add(xEdtChatText.getText().toString());
				xEdtChatText.setText("");
				adapter.notifyDataSetChanged();
			}
		};
		/** Setting the event listener for the add button */
		btn.setOnClickListener(listener);

		/** Setting the event listener for the delete button */
		// btnDel.setOnClickListener(listenerDel);

		/** Setting the adapter to the ListView */
		setListAdapter(adapter);
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

		    public void onItemClick(AdapterView<?> parent, View view, int position,
		        long id) {
		        // Your logic when clicking. You can get the object for that row by 
		        // calling parent.get(position) and casting to your data type in your adapter.
		        // which in this case seems to be string.
	/*			String xSelectedValue = parent
						.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(),
						xSelectedValue, Toast.LENGTH_SHORT).show();
				Intent xIntent = new Intent(getApplicationContext(),
						MapView.class);
		
			xIntent.putExtra("name", xSelectedValue);

			startActivity(xIntent);*/
		    	String url="http://tcssnellai.com/tcss/ag_and_018_chat/map_view.php?id="+position;
		    	if (!url.startsWith("https://") && !url.startsWith("http://")){
		    	    url = "http://" + url;
		    	}
		    	Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		    	startActivity(openUrlIntent);
		    }
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void insert() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("username", "saleem"));
		nameValuePairs.add(new BasicNameValuePair("chat", xEdtChatText
				.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("lat",  String.valueOf(latitude)));
		nameValuePairs.add(new BasicNameValuePair("long", String.valueOf(longitude)));

		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://tcssnellai.com/tcss/ag_and_018_chat/testandroidinsert.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			JSONObject json_data = new JSONObject(result);
			code = (json_data.getInt("code"));

			if (code == 1) {
				Toast.makeText(getBaseContext(), "Inserted Successfully",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(getBaseContext(), "Sorry, Try Again",
						Toast.LENGTH_LONG).show();
			}
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}
	
	public void select() {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		nameValuePairs.add(new BasicNameValuePair("username", "saleem"));


		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					   "http://tcssnellai.com/tcss/ag_and_018_chat/testandroidselect.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			Log.e("pass 1", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 1", e.toString());
			Toast.makeText(getApplicationContext(), "Invalid IP Address",
					Toast.LENGTH_LONG).show();
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			Log.e("pass 2", "connection success ");
		} catch (Exception e) {
			Log.e("Fail 2", e.toString());
		}

		try {
			
			try {
				JSONObject jsonResponse = new JSONObject(result);
				JSONArray jsonMainNode = jsonResponse.optJSONArray("chat_data");

				for (int i = 0; i < jsonMainNode.length(); i++) {
					JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
					String xFullName = jsonChildNode.optString("chattext");
					list.add(xFullName);
					//String outPut = xFullName;
					//doctorlist.add(createDoctors("employees", outPut));
				}
			} catch (JSONException e) {
				Toast.makeText(getApplicationContext(), "Error" + e.toString(),
						Toast.LENGTH_SHORT).show();
			}

	
		} catch (Exception e) {
			Log.e("Fail 3", e.toString());
		}
	}
	public void fn_CollectGpsValues() {
		GPSTracker gps;
		gps = new GPSTracker(this);
		if (gps.canGetLocation()) {
			 latitude = gps.getLatitude();
			 longitude = gps.getLongitude();
			

		} else {

			gps.showSettingsAlert();
		}
	}

}
