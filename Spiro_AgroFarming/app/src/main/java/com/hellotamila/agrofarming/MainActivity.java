package com.hellotamila.agrofarming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();



            }
        });

        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                )
                        .show();
                String xSelectedItem = expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).toString();
                if (xSelectedItem.equalsIgnoreCase("Rice")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/rice.pdf");
                    xIntent.putExtra("image", "rice");
                    startActivity(xIntent);

                }
                if (xSelectedItem.equalsIgnoreCase("BlackGram")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/blackgram.pdf");
                    xIntent.putExtra("image", "blackgram");
                    startActivity(xIntent);
                }

                if (xSelectedItem.equalsIgnoreCase("GreenGram")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/greengram.pdf");
                    xIntent.putExtra("image", "greengram");
                    startActivity(xIntent);
                }
                if (xSelectedItem.equalsIgnoreCase("IrrigatedMaize")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/irrigatedmaize.pdf");
                    xIntent.putExtra("image", "maize");
                    startActivity(xIntent);
                }
                if (xSelectedItem.equalsIgnoreCase("RainFedMaize")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/rainfedmaize.pdf");
                    xIntent.putExtra("image", "maize");
                    startActivity(xIntent);
                }
                if (xSelectedItem.equalsIgnoreCase("IrrigatedCotton")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/irrigatedcotton.pdf");
                    xIntent.putExtra("image", "cotton");
                    startActivity(xIntent);
                }
                if (xSelectedItem.equalsIgnoreCase("RainFedCotton")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                    xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/rainfedcotton.pdf");
                    xIntent.putExtra("image", "cotton");
                    startActivity(xIntent);
                }
                if (xSelectedItem.equalsIgnoreCase("Sugarcane")) {
                    Intent xIntent = new Intent(getApplicationContext(), Webform.class);
                        xIntent.putExtra("url", "http://hellotamila.com/spiro/agri/sugarcane.pdf");
                    xIntent.putExtra("image", "sugarcane");
                    startActivity(xIntent);
                }
                return false;
            }
        });
    }
}