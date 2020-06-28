package com.example.ag_and_009_personalassistant;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ag_and_009_dailystudentactivities.R;

public class DetailFragment extends Fragment {



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle args) {
		View view = inflater.inflate(R.layout.menu_detail_fragment, container,
				false);

		ListView lstItems = (ListView) view.findViewById(R.id.listView1);

		ArrayList<String> prueba = new ArrayList<String>();
		prueba.add("Element1");
		prueba.add("Element2");
		prueba.add("Element3");

		ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(
				getActivity().getBaseContext(),
				android.R.layout.simple_list_item_1, prueba);

		lstItems.setAdapter(allItemsAdapter);

		String menu = getArguments().getString("Menu");

		getActivity().setTitle(menu);
		return view;
	}

}