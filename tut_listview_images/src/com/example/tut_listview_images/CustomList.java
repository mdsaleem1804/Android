package com.example.tut_listview_images;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{
	 
private final Activity context;
private final String[] web;
private final String[] web1;
public CustomList(Activity context,
String[] web, String[] web1) {
super(context, R.layout.list_single, web);
this.context = context;
this.web = web;
/*this.imageId = imageId;*/
this.web1=web1;
 
}
@Override
public View getView(int position, View view, ViewGroup parent) {
LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.list_single, null, true);
TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txt1);
//ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
txtTitle.setText(web[position]);
txtTitle1.setText(web1[position]);
 
//imageView.setImageResource(imageId[position]);
return rowView;
}
}