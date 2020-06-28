package nellaibill.tetcomparisonsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
								listDataHeader.get(groupPosition)).get(
								childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("2017");
		listDataHeader.add("2013");
		listDataHeader.add("2012-OCT");
		listDataHeader.add("2012-AUG");

		// Adding child data
		List<String> x2017 = new ArrayList<String>();
        x2017.add("TAMIL");
        x2017.add("ENGLISH");
        x2017.add("MATHS");
        x2017.add("SCIENCE");
        x2017.add("SOCIAL SCIENCE");

		List<String> x2013 = new ArrayList<String>();
        x2013.add("TAMIL");
        x2013.add("ENGLISH");
        x2013.add("MATHS");
        x2013.add("SCIENCE");
        x2013.add("SOCIAL SCIENCE");

		List<String> x2012B = new ArrayList<String>();
        x2012B.add("TAMIL");
        x2012B.add("ENGLISH");
        x2012B.add("MATHS");
        x2012B.add("SCIENCE");
        x2012B.add("SOCIAL SCIENCE");

        List<String> x2012A = new ArrayList<String>();
        x2012A.add("TAMIL");
        x2012A.add("ENGLISH");
        x2012A.add("MATHS");
        x2012A.add("SCIENCE");
        x2012A.add("SOCIAL SCIENCE");
		listDataChild.put(listDataHeader.get(0), x2017); // Header, Child data
		listDataChild.put(listDataHeader.get(1), x2013);
        listDataChild.put(listDataHeader.get(2), x2012B);
		listDataChild.put(listDataHeader.get(3), x2012A);
	}
}
