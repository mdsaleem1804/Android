package www.votingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Userprofile extends AppCompatActivity {

    TextView name, uid, gender, yob;
    String data, get_id, get_mobile, get_name, get_uid, get_gender, get_yob, profile_url;

    Button submit;

    ProgressDialog pDialog;

    JSONArray jarr = null;

    JSONObject json;

    private int STORAGE_PERMISSION_CODE = 25;

    ArrayList<String> nameal = new ArrayList<String>();
    ArrayList<String> uidal = new ArrayList<String>();
    ArrayList<String> genderal = new ArrayList<String>();
    ArrayList<String> yobal = new ArrayList<String>();
    SimpleGetterAndSetter obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        obj= new SimpleGetterAndSetter();

        name = (TextView)findViewById(R.id.t2);
        uid = (TextView)findViewById(R.id.t4);
        gender = (TextView)findViewById(R.id.t6);
        yob = (TextView)findViewById(R.id.t8);

        profile_url = Defines.TAG_PROFILE_URL;

        submit = (Button)findViewById(R.id.btn_verify);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(obj.e().equalsIgnoreCase(data)) {
               // Toast.makeText(getApplicationContext(), "Voted Succesfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Userprofile.this, Otp_Activity.class);
               /* intent.putExtra("id", get_id);
                intent.putExtra("mobile", get_mobile);*/
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Id Not Matched", Toast.LENGTH_LONG).show();
            }
                //new loadlogin().execute();


            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("msg");
        }

/*        String xmlString = data;  // some XML String previously created
        XmlToJson xmlToJson = new XmlToJson.Builder(xmlString).build();

        String jsondata = String.valueOf(xmlToJson);*/
        get_name = obj.a();
        get_uid = obj.b();
        get_gender = obj.c();
        get_yob =obj.d();
        name.setText(get_name);
        uid.setText(get_uid);
        gender.setText(get_gender);
        yob.setText(get_yob);

      /* Log.d("checkJson", jsondata);
        JSONObject resobj = null;
        try {
            resobj = new JSONObject(jsondata);
            Iterator<?> keys = resobj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                try {
                    if (resobj.get(key) instanceof JSONObject) {
                        JSONObject xx = new JSONObject(resobj.get(key).toString());
                        Log.d("name", xx.getString("name"));
                        Log.d("uid", xx.getString("uid"));

                       *//* get_name = xx.get("name").toString();
                        get_uid = xx.get("uid").toString();
                        get_gender = xx.get("gender").toString();
                        get_yob = xx.get("yob").toString();
*//*


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


*/}
    class loadlogin extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Userprofile.this);
            pDialog.setMessage("Loading Please wait...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params1 = new ArrayList<NameValuePair>();
            params1.add(new BasicNameValuePair("name", get_name));
            params1.add(new BasicNameValuePair("gender",get_gender));
            params1.add(new BasicNameValuePair("uid", get_uid));
            params1.add(new BasicNameValuePair("yob", get_yob));

            // getting JSON string from URL
            json = JSONParser.makeHttpRequest(profile_url, "GET", params1);
            Log.i("json", "" + json);

            // TODO Auto-generated method stub

            try {
                // Getting Array of Contacts
                Log.i("json", "" + json);
                jarr = json.getJSONArray(Defines.TAG_PROFILE_OBJECT);
                // looping through All Contacts
                Log.i("array", "" + jarr.length());
                for (int i = 0; i < jarr.length(); i++) {
                    JSONObject c = jarr.getJSONObject(i);
                    // Storing each json item in variable


                    get_id = c.getString("id");
                    get_mobile = c.getString("mobile");


                    nameal.add(get_name);
                    genderal.add(get_gender);
                    uidal.add(get_uid);
                    yobal.add(get_yob);

                    if ((get_name.equals(get_name)) && (get_gender.equals(get_gender)) && (get_uid.equals(get_uid)) && (get_yob.equals(get_yob))) {

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

                    for (int i = 0; i < nameal.size(); i++) {
                        if ((get_name.equals(nameal.get(i)))
                                && (get_gender.equals(genderal.get(i))) && (get_uid.equals(uidal.get(i))) && (get_yob.equals(yobal.get(i)))) {
                            flag = true;
                        }
                    }

                    if (flag == true) {

                        Toast.makeText(getApplicationContext(), "Verified successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Userprofile.this, Otp_Activity.class);
                        intent.putExtra("id", get_id);
                        intent.putExtra("mobile", get_mobile);
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
