package www.votingsystem;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static www.votingsystem.R.layout.get_otp;

public class Otp_Activity extends AppCompatActivity {

    EditText mobile;

    String insert_url,get_mobile, get_id;

    Random rand;

    ProgressDialog pDialog;

    InputStream is;
    Button send;

    int n;
    SimpleGetterAndSetter obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mobile = (EditText) findViewById(R.id.editText);
        send = (Button) findViewById(R.id.button2);
        obj= new SimpleGetterAndSetter();

        insert_url = Defines.TAG_OTPGEN_URL;

      /*  Bundle extras = getIntent().getExtras();
        if (extras != null) {

            get_id = extras.getString("id");
            get_mobile = extras.getString("mobile");

        }*/
        mobile.setText(obj.g());


        rand = new Random();

        n = rand.nextInt(12345) + 4;

        Log.d("checkrand", String.valueOf(n));

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                get_mobile = mobile.getText().toString();
                obj.setOtp(String.valueOf(n));

                Intent intent = new Intent(getApplicationContext(), Get_Otp.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(get_mobile, null, String.valueOf(n), pi, null);


              //  new updateotp().execute();


            }
        });


    }

    class updateotp extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Otp_Activity.this);
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


                nameValuePair.add(new BasicNameValuePair("id", get_id));
                nameValuePair.add(new BasicNameValuePair("mobile", get_mobile));
                nameValuePair.add(new BasicNameValuePair("otp", String.valueOf(n)));


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

                        Toast.makeText(getApplicationContext(), "OTP Successfully Sent ", Toast.LENGTH_LONG).show();

                        Intent i1 = new Intent(Otp_Activity.this, Get_Otp.class);

                        i1.putExtra("id",get_id);
                        i1.putExtra("otp",get_otp);
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

