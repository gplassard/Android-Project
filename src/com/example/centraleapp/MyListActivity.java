package com.example.centraleapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MyListActivity extends Activity implements OnItemClickListener {
	public final static String KEY_INDICE_SELECTED = "SELECTED INDICE";	
	List<PointOfInterest> pois;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);
		pois = ((MyApplication) getApplication()).getPOIS();

		ListView listView = (ListView) findViewById(R.id.listView1);
		PointOfInterestLittleAdapter poiAdapter = new PointOfInterestLittleAdapter(this, pois);
		listView.setAdapter(poiAdapter);
		
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
		Intent intentPointOfInterest = new Intent(this,PointOfInterestActivity.class);
		intentPointOfInterest.putExtra(KEY_INDICE_SELECTED, position);
		startActivity(intentPointOfInterest);
	}

}
