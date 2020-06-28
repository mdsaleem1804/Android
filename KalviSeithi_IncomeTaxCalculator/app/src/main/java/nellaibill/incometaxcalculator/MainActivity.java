package nellaibill.incometaxcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Intent xIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
    }

    public void btn_go_itforms(View v)
        {
            xIntent = new Intent(getApplicationContext(), Webform.class);
            startActivity(xIntent);
        }

    public void mini(View v) {

        String smsNumber = "+919578795653"; // E164 format without '+' sign
        smsNumber = smsNumber.replace("+", "").replace(" ", "");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);
      //  Uri uri = Uri.parse("android.resource://com.nellaibill.incometaxcalculator/drawable/it_logo");
        //  Toast.makeText(getApplicationContext(),imageUri,Toast.LENGTH_SHORT).show();
       /* xIntent = new Intent(getApplicationContext(), WebView.class);
        startActivity(xIntent);
        Intent sendIntent = new Intent("android.intent.action.MAIN");
       sendIntent.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators("9578795653")+"@s.whatsapp.net");//phone number without "+" prefix

        startActivity(sendIntent);

      String smsNumber = "9578795653"; // E164 format without '+' sign
        smsNumber = smsNumber.replace("+", "").replace(" ", "");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sendIntent.setType("image/jpeg");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);


        /*String picture_text = "hello";
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setPackage("com.whatsapp");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "My sample image text");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


        try {
            startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            //ToastHelper.MakeShortText("Kindly install whatsapp first");

        }

        Intent share = new Intent(Intent.ACTION_SEND);

        share.setType("image/jpg");

        String imagePath = Environment.getExternalStorageDirectory()
                + "/picture.jpg";

        File imageFileToShare = new File(imagePath);

        Uri uri = Uri.fromFile(imageFileToShare);
        //share.putExtra(Intent.EXTRA_STREAM, uri);
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("/storage/emulated/0/picture.jpg"));
        startActivity(Intent.createChooser(share, "Share Image!"));*/
    }
}