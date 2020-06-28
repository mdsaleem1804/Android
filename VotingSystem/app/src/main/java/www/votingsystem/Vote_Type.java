package www.votingsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Vote_Type extends AppCompatActivity {

    Spinner vote_name;

    Button sub;

    JSONObject json;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    // products JSONArray
    JSONArray namelistJsonArray = null;

    String get_id1,get_vote_name, insert_url,name_list, get_vid,get_id, get_name;

    ProgressDialog pDialog;

    InputStream is;

    ArrayList<String> VoteIDArr = new ArrayList<String>();
    ArrayList<String> VotenameArr = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote__type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vote_name = (Spinner) findViewById(R.id.spin);
        sub = (Button) findViewById(R.id.b1);

       Bundle extras = getIntent().getExtras();

        if (extras != null) {


            get_id1 = extras.getString("id1");


        }

        insert_url ="http://192.168.43.162/votingsystem/election.php";

        name_list="http://192.168.43.162/votingsystem/get_vote.php";

     new loadspin().execute();


        vote_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            get_vote_name = VotenameArr.get(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new insertvote().execute();

            }
        });

    }

    class loadspin extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params1 = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            json = jParser.makeHttpRequest(name_list, "GET", params1);
            // Check your log cat for JSON reponse
            if (!json.equals(null)) {

                try {

                    namelistJsonArray = json
                            .getJSONArray(Defines.TAG_SPINVIEW_OBJECT);

                    if (namelistJsonArray.length() != 0) {
                        // looping through All Products
                        for (int i = 0; i < namelistJsonArray.length(); i++) {

                            JSONObject c = namelistJsonArray.getJSONObject(i);

                            get_vid = c.getString("v_id");
                            get_vote_name = c.getString("party_name");


                            VoteIDArr.add(get_vid);
                            VotenameArr.add(get_vote_name);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                    (Vote_Type.this, android.R.layout.simple_spinner_item, VotenameArr);

            dataAdapter.setDropDownViewResource
                    (android.R.layout.simple_spinner_dropdown_item);

            vote_name.setAdapter(dataAdapter);


        }
    }

    class insertvote extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Vote_Type.this);
            pDialog.setMessage("Registering Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            HttpClient httpclient = new DefaultHttpClient();
            try {
                List<NameValuePair> nameValuePair = new ArrayList<>();


                nameValuePair.add(new BasicNameValuePair("id", get_id1));
                nameValuePair.add(new BasicNameValuePair("vote_type", get_vote_name));


                String params1 = URLEncodedUtils.format(nameValuePair, "utf-8");
                insert_url += "?" + params1;
                Log.i("urlvalue", insert_url);
                HttpGet httpget = new HttpGet(insert_url);
                HttpResponse httpresponse = httpclient.execute(httpget);
                HttpEntity httpentity = httpresponse.getEntity();
                is = httpentity.getContent();
            } catch (ClientProtocolException e) {
            } catch (IOException e) {
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

                    try {

                        Toast.makeText(getApplicationContext(), "Vote Successfully ", Toast.LENGTH_LONG).show();

                        Intent i1 = new Intent(Vote_Type.this, LoginActivity.class);
                        startActivity(i1);


                        finish();

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            });


        }
    }
}
