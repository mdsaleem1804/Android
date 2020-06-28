package www.votingsystem;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {

    EditText username, password;

    Button login;

    String get_vote_name,get_name, get_pass,get_id,get_mobile,login_url;

    ProgressDialog pDialog;

    JSONArray jarr = null;

    JSONObject json;
    int code;
    Intent xIntent;
    String xChatText;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    String xMobileNo,xUserName;
    EditText xEdtMobileNo,xEdtUserName;
    String xImei;

    private int STORAGE_PERMISSION_CODE = 25;

    ArrayList<String> useral = new ArrayList<String>();
    ArrayList<String> passal = new ArrayList<String>();
    SimpleGetterAndSetter obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        obj= new SimpleGetterAndSetter();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_signin);

        login_url = Defines.TAG_LOGIN_URL;

        requestStoragePermission();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                get_name = username.getText().toString();
                get_pass = password.getText().toString();

                if (get_name.equals("") || get_pass.equals("")) {

                    Toast.makeText(getApplicationContext(), "Please enter filed", Toast.LENGTH_LONG).show();
                } else {
                    //new loadlogin().execute();
                    checkUser();
                }
            }
        });


    }
    public void checkUser() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username",get_name));
        nameValuePairs.add(new BasicNameValuePair("password",get_pass));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/spiro/voting/checkuser.php");
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
            get_id = json_data.getString("id");
            get_name = json_data.getString("username");
            get_pass = json_data.getString("password");
            get_mobile = json_data.getString("mobile");
            get_vote_name = json_data.getString("vote_type");


            if (code == 1) {
                if (get_vote_name.equals("")){
                    get_id = json_data.getString("id");
                    get_name = json_data.getString("username");
                    get_pass = json_data.getString("password");
                    get_mobile = json_data.getString("mobile");
                    get_vote_name = json_data.getString("vote_type");
                    String get_name = json_data.getString("name");
                    String get_uid = json_data.getString("uid");
                    String get_gender = json_data.getString("gender");
                    String get_yob = json_data.getString("yob");
                    String get_barcode = json_data.getString("barcode");
                    Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();
                    obj.setDetails(get_name,get_uid,get_gender,get_yob,get_barcode,get_barcode,get_mobile);
                    Intent intent = new Intent(LoginActivity.this, ScanActivity.class);
                    intent.putExtra("id",get_id);
                    intent.putExtra("mobile",get_mobile);
                    startActivity(intent);
                    finish();
                }else {

                    Toast.makeText(getApplicationContext(), "Already insert vote", Toast.LENGTH_LONG).show();

                }

            }
            else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }
    }

    class loadlogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Loading Please wait...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params1 = new ArrayList<NameValuePair>();
            params1.add(new BasicNameValuePair("username", get_name));
            params1.add(new BasicNameValuePair("password", get_pass));
            // getting JSON string from URL
            json = JSONParser.makeHttpRequest(login_url, "GET", params1);
            Log.i("json", "" + json);

            // TODO Auto-generated method stub

            try {
                // Getting Array of Contacts
                Log.i("json", "" + json);
                jarr = json.getJSONArray(Defines.TAG_LOGIN_OBJECT);
                // looping through All Contacts
                Log.i("array", "" + jarr.length());
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject c = jarr.getJSONObject(i);
                    // Storing each json item in variable

                    get_id = c.getString("id");
                    get_name = c.getString("username");
                    get_pass = c.getString("password");
                    get_mobile = c.getString("mobile");
                    get_vote_name = c.getString("vote_type");


                    System.out.println(get_name);
                    System.out.println(get_pass);


                    useral.add(get_name);
                    passal.add(get_pass);

                    if ((get_name.equals(get_name)) && (get_pass.equals(get_pass))) {

                        System.out.println(get_name);

                    } else {
                        Toast.makeText(getApplicationContext(), "No Details Found", Toast.LENGTH_LONG).show();
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    boolean flag = false;

                    for (int i = 0; i < useral.size(); i++) {
                        if ((get_name.equals(useral.get(i)))
                                && (get_pass.equals(passal.get(i)))) {
                            flag = true;
                        }
                    }

                    if (flag == true) {

                        if (get_vote_name.equals("")){

                            Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(LoginActivity.this, ScanActivity.class);
                            intent.putExtra("id",get_id);
                            intent.putExtra("mobile",get_mobile);
                            startActivity(intent);
                            finish();
                        }else {

                            Toast.makeText(getApplicationContext(), "Already insert vote", Toast.LENGTH_LONG).show();

                        }

                    } else {

                        Toast.makeText(getApplicationContext(),
                                "Invalid username or Password",
                                Toast.LENGTH_SHORT).show();

                    }
                }

            });
        }


    }

    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

}
