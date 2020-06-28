package com.nellaibill.petcollege;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] xDoctorName,xUserName,xProblem;

    public CustomList(Activity context,
                      String[] a, String[] b, String[] c) {
        super(context, R.layout.list_single, a);
        this.context = context;
        this.xDoctorName = a;
        this.xUserName = b;
        this.xProblem = c;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.doctorname);

        txtTitle.setText(xDoctorName[position]);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.username);
        txtTitle1.setText(xUserName[position]);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.problem);
        txtTitle2.setText(xProblem[position]);

        return rowView;
    }
}