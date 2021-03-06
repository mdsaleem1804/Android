package com.example.ag_and_009_personalassistant;

import com.example.ag_and_009_dailystudentactivities.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class Slider extends Activity {
	     String[] menu;
	     DrawerLayout dLayout;
	     ListView dList;
	     ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);
		

	     	menu = new String[]{"Meetings","Food","Classes","Seminar","Functions","Sports","More"};
	        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        dList = (ListView) findViewById(R.id.left_drawer);

	        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
	        
	        dList.setAdapter(adapter);
			dList.setSelector(android.R.color.holo_blue_dark);

	        dList.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
					
					dLayout.closeDrawers();					
					Bundle args = new Bundle();
					args.putString("Menu", menu[position]);
					Fragment detail = new DetailFragment();
					detail.setArguments(args);
				    FragmentManager fragmentManager = getFragmentManager();		    
					fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
					
				}
	        	
	        });
	}



}
