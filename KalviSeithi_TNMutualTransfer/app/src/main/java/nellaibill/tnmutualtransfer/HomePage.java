package nellaibill.tnmutualtransfer;

/**
 * Created by user on 11/6/2017.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class HomePage extends AppCompatActivity {
    private TextView mLevelTextView;
    Button button1;
    Intent xIntent;
    DatabaseHandler db;
    EditText xEdtMobileNo;
    String xPhoneNumber;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        customBar();
        try {
            button1 = (Button) findViewById(R.id.btn_go);
            xEdtMobileNo = (EditText) findViewById(R.id.editText);
            xIntent = new Intent(this, ListData.class);
            xPhoneNumber = "0000000000";
            db = new DatabaseHandler(this);
            List<Contact> contacts = db.getAllContacts();
            for (Contact cn : contacts) {
                xPhoneNumber = cn.getPhoneNumber();
            }
            if (!xPhoneNumber.equalsIgnoreCase("0000000000"))
            {
                finish();
                xIntent.putExtra("passmobileno", xPhoneNumber);
                startActivity(xIntent);
            } else {
                db.addContact(new Contact("Test", xPhoneNumber));

            }
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isValidMobile(xEdtMobileNo.getText().toString())) {
                        db.updateContact(new Contact(1, "Test", xEdtMobileNo.getText().toString()));
                        finish();
                        xIntent.putExtra("passmobileno",  xEdtMobileNo.getText().toString());
                        startActivity(xIntent);
                    }

                }
            });
        }
        catch(Exception e)
        {
            String xError=e.toString();
        }

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
    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() <10 || phone.length() > 10) {
                // if(phone.length() != 10) {
                check = false;
                xEdtMobileNo.setError("Not Valid Number");
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }
}