package com.hellotamila.ah_and_013_roadsense;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListUserRequests extends ListActivity {

	String xGetDriverName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DataBaseConnection mCon;
		mCon = new DataBaseConnection(this);

		List<String> xExpenses = mCon.fn_GetUserRequests();

		setListAdapter(new ArrayAdapter<String>(this, R.layout.listdata,
				xExpenses));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String names = ((TextView) view).getText().toString();
				

				String names_list[] = names.split("-");
				String xMobileNo = names_list[0];
				
				// When clicked, show a toast with the TextView text
				/*Toast.makeText(getApplicationContext(),
						xMobileNo, Toast.LENGTH_SHORT).show();
				*/
				sendSMS(xMobileNo, "Your Request Accepted.");
			}
		});

	}
	public void sendSMS(String phoneNo, String msg) {
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, msg, null, null);
			Toast.makeText(getApplicationContext(), "Message Sent to "+phoneNo,
					Toast.LENGTH_LONG).show();
		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage().toString(),
					Toast.LENGTH_LONG).show();
			ex.printStackTrace();
		}
	}
}