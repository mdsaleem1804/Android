package com.example.ag_and_005_reminder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Chart extends Activity {
	LinearLayout linearChart;
DataBaseConnection mCon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart);
		linearChart = (LinearLayout) findViewById(R.id.linearChart);
		mCon=new DataBaseConnection(this);
		/*
		 * int colerloop[] = { 1, 2,3}; int heightLoop[] = { 100,25,50}; for
		 * (int j = 0; j < colerloop.length; j++) { drawChart(1, colerloop[j],
		 * heightLoop[j]); }
		 */
		int xMedical=mCon.fn_SumExpenses("Medical");
		int xEntertainment=mCon.fn_SumExpenses("Entertainment");
		int xFood=mCon.fn_SumExpenses("Food");
		int xTravel=mCon.fn_SumExpenses("Travel");
		int xDress=mCon.fn_SumExpenses("Dress");
		int xOther=mCon.fn_SumOtherExpenses();
		drawChart(1, 1, xMedical);
		drawChart(1, 2, xEntertainment);
		drawChart(1, 3, xFood);
		drawChart(1, 4, xTravel);
		drawChart(1, 5, xDress);
		drawChart(1, 6, xOther);
		// drawChart(5);
	}

	public void drawChart(int count, int color, int height) {
		System.out.println(count + color + height);
		if (color == 1) {
			color = Color.RED;
		} else if (color == 2) {
			color = Color.BLUE;
		} else if (color == 3) {
			color = Color.GREEN;
		} else if (color == 4) {
			color = Color.YELLOW;
		} else if (color == 5) {
			color = Color.BLACK;
		}
		else if (color == 6) {
			color = Color.CYAN;
		}
		for (int k = 1; k <= count; k++) {
			View view = new View(this);
			view.setBackgroundColor(color);
			view.setLayoutParams(new LinearLayout.LayoutParams(75, height));
			LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
					.getLayoutParams();
			params.setMargins(3, 0, 0, 0);
			view.setLayoutParams(params);
			linearChart.addView(view);
		}

	}

	public void drawChart(int count) {
		System.out.println(count);

		View view = new View(this);
		// view.setBackgroundColor(R.color.aqua);
		view.setBackgroundColor(Color.parseColor("#ff6233"));
		view.setLayoutParams(new LinearLayout.LayoutParams(130, 400));
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view
				.getLayoutParams();
		params.setMargins(25, 0, 0, 0);
		view.setLayoutParams(params);
		linearChart.addView(view);

	}

}
