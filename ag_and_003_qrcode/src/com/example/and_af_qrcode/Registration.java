package com.example.and_af_qrcode;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Registration extends Activity {
	//Z
	
	EditText xEdtFullName, xEdtMobileNo, xEdtUserName,xEdtPassword;
	String xError;
	Bundle xBundle;
	int xMaxId;
	private DBhelper mydb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
            super.onCreate(savedInstanceState);
			setContentView(R.layout.registration);
			mydb = new DBhelper(this);
			fn_InitViews();
		
		}
		
		catch (Exception e) {
			xError = e.toString();

		}
		
	}
	
	//------------------DATA-PROCESS-----------------------
		public boolean fn_DataProcess(String xMode) {
			//SAVE
			if (xMode.equalsIgnoreCase("S")) {
			
				mydb.insertRegistration(xEdtFullName.getText()
						.toString(), xEdtMobileNo.getText().toString(),
						xEdtUserName.getText().toString(),xEdtPassword.getText().toString());
			}
		
			return true;
		}
		
		public void registration(View v)
		{
			if(xEdtUserName.getText().toString().matches(""))
			{
			
				Toast.makeText(getApplicationContext(),
						"User Name to be Filled Out", Toast.LENGTH_LONG)
						.show();
				xEdtUserName.requestFocus();
		} 
	
			else
			{
				if(!isValidMobile(xEdtMobileNo.getText().toString()))
				{
					 Toast.makeText(getApplicationContext(), "Mobile Number Must Be 10 Digit", Toast.LENGTH_SHORT).show();
					 xEdtMobileNo.requestFocus();
					    return;
				}
				if (fn_DataProcess("S")) {
					Toast.makeText(getApplicationContext(),
							"Inserted Succesfully", Toast.LENGTH_LONG)
							.show();
					xEdtUserName.requestFocus();
					fn_DataClear();

					Intent xIntentLoginForm = new Intent(Registration.this,
							Login.class);
					startActivity(xIntentLoginForm);
					
				}
			}
		}
	public void fn_InitViews() {
		xEdtFullName = (EditText) findViewById(R.id.edtFullName);
		xEdtMobileNo = (EditText) findViewById(R.id.edtMobileNo);
		xEdtUserName = (EditText) findViewById(R.id.edtUserName);
		xEdtPassword = (EditText) findViewById(R.id.edtPassword);
	}
	
	//-----------------DATA-CLEAR---------------------
	public void fn_DataClear() {
		xMaxId=mydb
				.fn_GetMaxID("SELECT CASE WHEN(userno>0)then max(userno+1) Else 1 " +
						"END AS userno from tbl_registration");
		xEdtFullName.setText("");
		xEdtMobileNo.setText("");
		xEdtUserName.setText("");
		xEdtPassword.setText("");
	}
	private boolean isValidMobile(String phone2) 
	{
	    boolean check;
	    if(phone2.length() < 10 || phone2.length() > 12)
	    {
	        check = false;
	        xEdtMobileNo.setError("Not Valid Number");
	    }
	    else
	    {
	        check = true;
	    }
	    return check;
	}
}