package com.hellotamila.messagereadaudio;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("READ"); // Create a new TabSpec using tab host
        spec.setIndicator("READ"); // set the “CONTACT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(getApplicationContext(), PlayLargeSms.class);
        intent.putExtra("flag", "1");;
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("PLAY"); // Create a new TabSpec using tab host
        spec.setIndicator("PLAY"); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(getApplicationContext(), PlayLargeSms.class);
        intent.putExtra("flag", "3");
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("VOICE"); // Create a new TabSpec using tab host
        spec.setIndicator("VOICE"); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, VoicetoText.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
