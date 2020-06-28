package www.votingsystem;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by DELL PC on 18-01-2017.
 */

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    static String responseString;

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public static JSONObject makeHttpRequest(String url, String method,
                                             List<NameValuePair> params) {

        // Making HTTP request
        Log.d("json class", url + "," + method + "," + params.toString());
        try {

            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                Log.d("json class", "post method");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                Log.d("json class", "HttpPost" + httpPost);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                Log.d("json class", "setentity");
                HttpResponse httpResponse = httpClient.execute(httpPost);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                int responce_code = httpResponse.getStatusLine()
                        .getStatusCode();
                Log.d("responce code", "response method");
                Log.d("responce code", "" + responce_code);
                StatusLine statusLine = httpResponse.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    Log.i("RESPONSE", "6");
					/*
					 * httpResponse.getEntity().writeTo(out); out.close();
					 * String responseString = out.toString(); Log.i("RESPONSE",
					 * ""+responseString);
					 */
                    // ..more logic
                } else {
                    Log.d("RESPONSE", "null pointer exception");
                    // Closes the connection.
                    httpResponse.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }

                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                Log.i("url", url);
                HttpGet httpGet = new HttpGet(url);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                HttpResponse httpResponse = httpClient.execute(httpGet);
                int responce_code = httpResponse.getStatusLine()
                        .getStatusCode();
                Log.d("responce code", "response method");
                Log.d("responce code", "" + responce_code);

                StatusLine statusLine = httpResponse.getStatusLine();

                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {

                    Log.d("RESPONSE", "6");
                    httpResponse.getEntity().writeTo(out);
                    Log.d("RESPONSE", "7");
                    out.close();
                    Log.d("RESPONSE", "8");
                    responseString = out.toString();
                    Log.d("RESPONSE", "9");
                    Log.i("RESPONSE", "" + responseString);
                    // ..more logic
                } else {
                    Log.d("RESPONSE", "null pointer exception");
                    // Closes the connection.
                    httpResponse.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
				/*
				 * HttpEntity httpEntity = httpResponse.getEntity(); is =
				 * httpEntity.getContent();
				 */
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
			/*
			 * BufferedReader reader = new BufferedReader(new InputStreamReader(
			 * is, "iso-8859-1"), 8); StringBuilder sb = new StringBuilder();
			 * String line = null; while ((line = reader.readLine()) != null) {
			 * sb.append(line + "\n"); } is.close();
			 */
            json = responseString.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }
}



