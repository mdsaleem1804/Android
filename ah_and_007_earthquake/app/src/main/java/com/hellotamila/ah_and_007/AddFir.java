package com.hellotamila.ah_and_007;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFir extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText xFullName, xAadhar,xMobileNo,xRemarks,xLatitude,xLongitude;
    GPSTracker gps;
    TextView textFile;
    private static final int PICKFILE_RESULT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fir);
        mCon = new DataBaseConnection(this);
        xFullName = (EditText) findViewById(R.id.fullname);
        xAadhar = (EditText) findViewById(R.id.aadhar);
        xMobileNo = (EditText) findViewById(R.id.mobileno);
        xRemarks = (EditText) findViewById(R.id.remarks);
        xLatitude = (EditText) findViewById(R.id.latitude);
        xLongitude = (EditText) findViewById(R.id.longitude);
        gps = new GPSTracker(AddFir.this);
        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            xLatitude.setText(String.valueOf(latitude));
            xLongitude.setText(String.valueOf(longitude));
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        Button buttonPick = (Button)findViewById(R.id.buttonpick);
        textFile = (TextView)findViewById(R.id.textfile);

        buttonPick.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent,PICKFILE_RESULT_CODE);

            }});
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if(resultCode==RESULT_OK){
                    String FilePath = data.getData().getPath();
                    int index = FilePath.lastIndexOf("/");
                    String fileName = FilePath.substring(index + 1);
                    textFile.setText(fileName);
                    Toast.makeText(getApplicationContext(),fileName,Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    public void fir_save(View v) {
      mCon.insertFir(xFullName.getText().toString(),xAadhar.getText().toString(),
              xMobileNo.getText().toString(),xRemarks.getText().toString(),xLatitude.getText().toString(),xLongitude.getText().toString());
        Toast.makeText(getApplicationContext(),"FIR Entered Succesfully",Toast.LENGTH_LONG).show();
        Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(xIntent);
}

}

