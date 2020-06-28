package com.hellotamila.ah_and_001_security;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class User_BankPage extends AppCompatActivity {
    Intent xIntent;
    SimpleGetterAndSetter obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_bankpage);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        obj = new SimpleGetterAndSetter();
    }


    public void my_profile(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url",  "http://hellotamila.com/barani/color/my_profile.php?username="+obj.getUsername());
        startActivity(xIntent);
    }

    public void list_beneficeary(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url",  "http://hellotamila.com/barani/color/my_profile_all.php");
        startActivity(xIntent);
    }
    public void upload(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url",  "http://hellotamila.com/barani/color/file_upload.php");
        startActivity(xIntent);
    }
    public void validate(View v) {
        xIntent = new Intent(getApplicationContext(), Webform.class);
        xIntent.putExtra("url",  "http://hellotamila.com/barani/color/readrgb.php");
        startActivity(xIntent);
    }
    public void my_transactions(View v) {
      xIntent = new Intent(getApplicationContext(), MyTransactions.class);
      startActivity(xIntent);
    }
    public void send_image(View v) {
        shareImage();
    }
    // Method to share any image.
    private void shareImage() {
        try {
            String image_url = "http://hellotamila.com/barani/color/test.png";

/*            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            sharingIntent.setType("image/*");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, image_url);
            startActivity(Intent.createChooser(sharingIntent, "Share Image Using"));*/


            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, image_url);
            try {
                startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                //ToastHelper.MakeShortText("Whatsapp have not been installed.");
            }

        }
        catch(Exception e)
        {
            String xError=e.toString();
        }
    }
}
