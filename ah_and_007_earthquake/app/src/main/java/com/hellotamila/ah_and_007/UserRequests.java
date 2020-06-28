package com.hellotamila.ah_and_007;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
public class UserRequests extends AppCompatActivity {
    DataBaseConnection mCon;
    EditText xFullName, xAadhar,xMobileNo,xRemarks;
    TextView textFile;

    private static final int PICKFILE_RESULT_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_requests);
        mCon = new DataBaseConnection(this);
        xFullName = (EditText) findViewById(R.id.fullname);
        xAadhar = (EditText) findViewById(R.id.aadhar);
        xMobileNo = (EditText) findViewById(R.id.mobileno);
        xRemarks = (EditText) findViewById(R.id.remarks);
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
    public void user_save(View v) {
      mCon.insertUserRequests(xFullName.getText().toString(),xAadhar.getText().toString(),xMobileNo.getText().toString(),xRemarks.getText().toString());
        Toast.makeText(getApplicationContext(),"Posted Succesfully",Toast.LENGTH_LONG).show();
        Intent xIntent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(xIntent);
}

}
