package com.nellaibill.petcollege;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FeesActivity extends Activity {

    WebView xWebView;
    WebSettings xWebSettings;
    String xUrl;
    Bundle xBundle;
    Intent xIntent;

    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webform);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        String   xRollNo = b.getString("passrollno");
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        setContentView(R.layout.webform);
        xWebView = (WebView) findViewById(R.id.webView1);
        xWebSettings = xWebView.getSettings();
        //customBar();
        xWebSettings.setJavaScriptEnabled(true);
        String postData = "rollno="+xRollNo;

        xWebView.postUrl(
                "http://hellotamila.com/barani/petcollege/jsontextview.php",
                EncodingUtils.getBytes(postData, "BASE64"));
        //  xWebView.loadUrl(xUrl);

    }


}
/*

    HttpResponse httpResponse;
    Button button;
    TextView xTextTotalAmount,xTextPaidAmount,xTextName;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    ProgressBar progressBar;
    ArrayList<NameValuePair> postParameters;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://hellotamila.com/barani/petcollege/jsontextview.php";
String xRollNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fees);

        // Assigning ID's to button, textView and progressbar.
        button = (Button)findViewById(R.id.button);
        xTextName = (TextView)findViewById(R.id.textName);
        xTextTotalAmount = (TextView)findViewById(R.id.text_total_amount);
        xTextPaidAmount = (TextView)findViewById(R.id.text_paid_amount);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        xRollNo = b.getString("rollno");
        new GetDataFromServerIntoTextView(FeesActivity.this).execute();
        // Adding click lister to button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress bar on button click.
                progressBar.setVisibility(View.VISIBLE);

                //Calling GetDataFromServerIntoTextView method to Set JSon MySQL data into TextView.
                new GetDataFromServerIntoTextView(FeesActivity.this).execute();

            }
        });
    }

    // Declaring GetDataFromServerIntoTextView method with AsyncTask.
    public class GetDataFromServerIntoTextView extends AsyncTask<Void, Void, Void>
    {
        // Declaring CONTEXT.
        public Context context;


        public GetDataFromServerIntoTextView(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient httpClient = new DefaultHttpClient();

            // Adding HttpURL to my HttpPost oject.
            HttpPost httpPost = new HttpPost(HttpURL);

            List<NameValuePair> nameValuePairs = new
                    ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("rollno",xRollNo));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                httpResponse = httpClient.execute(httpPost);

                StringHolder = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                // Passing string holder variable to JSONArray.
                JSONArray jsonArray = new JSONArray(StringHolder);
                jsonObject = jsonArray.getJSONObject(0);


            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {

                // Adding JSOn string to textview after done loading.
                xTextName.setText(jsonObject.getString("name"));
                xTextTotalAmount.setText(jsonObject.getString("bus_total_fees"));
                xTextPaidAmount.setText(jsonObject.getString("bus_paid_fees"));


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.
            progressBar.setVisibility(View.GONE);

        }
    }


}*/
