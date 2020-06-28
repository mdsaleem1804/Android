package com.hellotamila.ah_and_011_book;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class  StudentHomePage extends AppCompatActivity {
    Intent xIntent;
    String xRollNo;
    HttpResponse httpResponse;
    Button button;
    TextView textView;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    ProgressBar progressBar;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://hellotamila.com/barani/petcollege/fees_notification.php";
    String xFeesPending,xDueDateForBook,xBookName;
    SimpleGetterAndSetter obj;
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);
        obj = new SimpleGetterAndSetter();
        xRollNo = obj.getRollno();
        String xSessionStudent=obj.getSessionStudent();
        if(xSessionStudent.equalsIgnoreCase("No")) {
          // new GetDataFromServerIntoTextView(StudentHomePage.this).execute();
            new GetBookNotificationData(StudentHomePage.this).execute();
            obj.setSessionStudent("Yes");
        }
    }

    public void library(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "http://hellotamila.com/barani/petcollege/viewbooks.php");
        startActivity(xIntent);
   }
    public void news_feed(View v) {
        xIntent = new Intent(getApplicationContext(), NewsFeed.class);
        startActivity(xIntent);
    }
    public void logout(View v) {
        xIntent = new Intent(getApplicationContext(), HomePage.class);
        obj.setSessionStudent("No");
        this.finish();
        startActivity(xIntent);
    }


    public void department(View v) {
        xIntent = new Intent(getApplicationContext(), ListDepartment.class);
        startActivity(xIntent);
    }
    public void faculty(View v) {
        xIntent = new Intent(getApplicationContext(), FacultyDepartment.class);
        startActivity(xIntent);
    }
    private void addNotificationforBook() {
        String xMessage=" "+xBookName +" Renewal Date- " +xDueDateForBook;
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.add_books)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(xMessage))
                        .setContentTitle("Book  Notification")
                        .setContentText(xMessage);

        Intent notificationIntent = new Intent(this, StudentHomePage.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }


    public class GetBookNotificationData extends AsyncTask<Void, Void, Void> {
        // Declaring CONTEXT.
        public Context context;


        public GetBookNotificationData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://hellotamila.com/barani/petcollege/issue_book_notification.php");
            ArrayList<NameValuePair> postParameters;
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("rollno", xRollNo));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
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
            try {
                // Passing string holder variable to JSONArray.
                JSONArray jsonArray = new JSONArray(StringHolder);
                jsonObject = jsonArray.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result) {
            try {

                xDueDateForBook=jsonObject.getString("date");
                xBookName=jsonObject.getString("book_name");
                if(xDueDateForBook.equalsIgnoreCase("null"))
                {
                }
                else
                {
                    addNotificationforBook();
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}


/*
package com.nellaibill.petcollege;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class StudentHomePage extends AppCompatActivity {
    Intent xIntent;
    String xRollNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_home_page);
        customBar();
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        xRollNo = b.getString("rollno");
    }

    public void department(View v) {
*/
/*        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url", "file:///android_asset/dept_homepage.html");
        startActivity(xIntent);*//*

        xIntent = new Intent(getApplicationContext(), ListDepartment.class);
        startActivity(xIntent);
    }

    public void maps(View v) {
        xIntent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(xIntent);
    }
    public void fees(View v) {
      */
/*  xIntent = new Intent(getApplicationContext(), FeesActivity.class);
        xIntent.putExtra("rollno", xRollNo);
        startActivity(xIntent);*//*


        xIntent = new Intent(getApplicationContext(), FeesActivity.class);
        xIntent.putExtra("passrollno", xRollNo);
        startActivity(xIntent);
    }
    public void customBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.custom_actionbar, null);

        ImageView menus =(ImageView) customView.findViewById(R.id.slidingmenu);

        actionBar.setCustomView(customView);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        Toolbar toolbar=(Toolbar)actionBar.getCustomView().getParent();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.getContentInsetEnd();
        toolbar.setPadding(0, 0, 0, 0);
    }
}
*/
