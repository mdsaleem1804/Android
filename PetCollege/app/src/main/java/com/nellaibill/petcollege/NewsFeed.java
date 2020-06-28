package com.nellaibill.petcollege;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
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
public class NewsFeed extends AppCompatActivity {
    ListView list;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    ArrayList<String> list1 = new ArrayList<String>();
    ArrayList<String> list2 = new ArrayList<String>();
    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_newsfeed);
        obj = new SimpleGetterAndSetter();
        NewsFeedAdapter adapter=new NewsFeedAdapter(this, list1, list2);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        LoadNewsFeed();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String xGetSelectedItem = parent.getItemAtPosition(position).toString();

                        //code specific to first list item
                                       obj.setName(xGetSelectedItem);
                                       Toast.makeText(getApplicationContext(),xGetSelectedItem,Toast.LENGTH_SHORT).show();
                                       Intent xIntent = new Intent(getApplicationContext(), CommentForNews.class);
                                       startActivity(xIntent);




            }
        });
    }
    public void LoadNewsFeed() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("username", "saleem"));

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://hellotamila.com/barani/petcollege/view_news_feed.php");
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

                    list1.add(jsonChildNode.optString("itemcategoryname"));
                    list2.add(jsonChildNode.optString("itemcategoryshortname"));
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
    class ScrollListener implements AbsListView.OnScrollListener{
        boolean aligned;

        @Override
        public void onScrollStateChanged(AbsListView absListView, int state) {
            if (state == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                aligned = false;
            }
            if (state == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                if (!aligned) {
                    if (Math.abs(absListView.getChildAt(0).getY()) < Math.abs(absListView.getChildAt(1).getY())) {
                        list.smoothScrollToPosition(absListView.getFirstVisiblePosition());
                    } else {
                        list.smoothScrollToPosition(absListView.getLastVisiblePosition());
                    }
                }
                aligned = true;
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }
    }
}