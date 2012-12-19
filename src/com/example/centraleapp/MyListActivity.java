package com.example.centraleapp;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class MyListActivity extends Activity {
	
	List<PointOfInterest> pois;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);
		pois = ((MyApplication) getApplication()).getPOIS();

		ListView listView = (ListView) findViewById(R.id.listView1);
		PointOfInterestLittleAdapter poiAdapter = new PointOfInterestLittleAdapter(this, pois);
		listView.setAdapter(poiAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_list, menu);
		return true;
	}

}
