package www.votingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL PC on 20-01-2017.
 */
public class Get_Otp extends AppCompatActivity {
    int code;
    android.content.Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    EditText otp;

    String checking_id,get_otp,get_id,insert_otp;

    Button send;

    ProgressDialog pDialog;
    TextView check_id;

    JSONArray jarr = null;

    JSONObject json;


    ArrayList<String> otpal = new ArrayList<String>();
    ArrayList<String> IDArr = new ArrayList<String>();

    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_otp);

        check_id = (TextView) findViewById(R.id.t1);
        otp = (EditText) findViewById(R.id.editText2);
        send = (Button) findViewById(R.id.button);
        obj= new SimpleGetterAndSetter();

        insert_otp= Defines.TAG_OTP_URL;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            get_id = extras.getString("id");

            check_id.setText(get_id);
        }


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


           get_otp=otp.getText().toString();
           checking_id =check_id.getText().toString();

                if (get_otp.equals("")) {

                    Toast.makeText(getApplicationContext(),"pleae enter otp ",Toast.LENGTH_LONG).show();
                }else{
if(obj.f().equalsIgnoreCase(get_otp))
{

    Toast.makeText(getApplicationContext(), "Verified successfully", Toast.LENGTH_LONG).show();
    fn_UpdateVote();
/*    Intent intent = new Intent(Get_Otp.this,Vote_Type.class);
    intent.putExtra("id1",checking_id);
    startActivity(intent);*/
}
else
{
    Toast.makeText(getApplicationContext(), "OTP IS INVALID", Toast.LENGTH_LONG).show();
}
                   // new Otp_Activity().execute();

                }




            }
        });


    }

    public void fn_UpdateVote() {
        java.util.ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        //obj.setDetails(xUserName,xMobileNo,xImei);
        nameValuePairs.add(new BasicNameValuePair("mobileno",obj.g()));


        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/voting/update_vote.php");
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
                Toast.makeText(getBaseContext(), "Voted Successfully",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }
    class Otp_Activity extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Get_Otp.this);
            pDialog.setMessage("Registering Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            List<NameValuePair> params1 = new ArrayList<NameValuePair>();
            params1.add(new BasicNameValuePair("id", checking_id));
            params1.add(new BasicNameValuePair("otp", get_otp));
            // getting JSON string from URL
            json = JSONParser.makeHttpRequest(insert_otp, "GET", params1);
            Log.i("json", "" + json);

            // TODO Auto-generated method stub

            try {
                // Getting Array of Contacts
                Log.i("json", "" + json);
                jarr = json.getJSONArray(Defines.TAG_OTP_OBJECT);
                // looping through All Contacts
                Log.i("array", "" + jarr.length());
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject c = jarr.getJSONObject(i);
                    // Storing each json item in variable

                    checking_id= c.getString("id");
                    get_otp= c.getString("otp");


                    System.out.println(get_otp);

                    otpal.add(get_otp);
                    IDArr.add(checking_id);

                    Log.d("id" ,""+IDArr);


                    if ((get_otp.equals(get_otp) && checking_id.equals(checking_id)) ) {

                        System.out.println(get_otp);
                        System.out.println(checking_id);

                    } else {
                        Toast.makeText(getApplicationContext(), "No Details Found", Toast.LENGTH_LONG).show();
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
// TODO Auto-generated method stub
                    boolean flag = false;

                    for (int i = 0;i < otpal.size();i++) {
                        if (checking_id.equals(IDArr.get(i)) && (get_otp.equals(otpal.get(i)))) {
                            flag = true;
                        }
                    }

                    if (flag == true) {

                        Toast.makeText(getApplicationContext(), "Verified successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Get_Otp.this,Vote_Type.class);
                       intent.putExtra("id1",checking_id);
                        startActivity(intent);

                        finish();
                    } else {

                        Toast.makeText(getApplicationContext(),
                                "Invalid Verified",
                                Toast.LENGTH_SHORT).show();

                    }
                }

            });
        }
    }
}
