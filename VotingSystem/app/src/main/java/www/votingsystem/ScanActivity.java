package www.votingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ScanActivity extends AppCompatActivity {

    String get_id,get_mobile;

    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scan =(Button)findViewById(R.id.btn_scan);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            get_id= extras.getString("id");
            get_mobile= extras.getString("mobile");
        }
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext() ,Checkscan.class);
                ii.putExtra("id",get_id);
                ii.putExtra("mobile",get_mobile);
                startActivity(ii);
            }
        });

    }

}
