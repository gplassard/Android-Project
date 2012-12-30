package fr.gplassard.centraleapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MyListActivity extends Activity implements OnItemClickListener,
		TextWatcher, OnItemSelectedListener {

	private List<PointOfInterest> allPois;
	private List<PointOfInterest> matchingPois;
	private List<String> categories;
	private PointOfInterestLittleAdapter poiAdapter;
	private Spinner spinnerCategories;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list);
		allPois = ((MyApplication) getApplication()).getPOIS();
		matchingPois = new ArrayList<PointOfInterest>();
		matchingPois.addAll(allPois);

		ListView listView = (ListView) findViewById(R.id.listView1);
		poiAdapter = new PointOfInterestLittleAdapter(this, matchingPois,this);
		listView.setAdapter(poiAdapter);

		listView.setOnItemClickListener(this);

		EditText editText = (EditText) findViewById(R.id.editText1);
		editText.addTextChangedListener(this);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		initializeCategoriesSpinner();
	}

	private void initializeCategoriesSpinner() {
		categories = ((MyApplication) getApplication()).getCategories();
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		for (String categorie : categories){
			adapter.add(categorie);
		}
		
		spinnerCategories = (Spinner) findViewById(R.id.spinner1);
		spinnerCategories.setOnItemSelectedListener(this);
		spinnerCategories.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_list, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intentPointOfInterest = new Intent(this,
				PointOfInterestActivity.class);
		intentPointOfInterest.putExtra(C.SELECTED_POI, matchingPois.get(position));
		startActivity(intentPointOfInterest);
	}

	@Override
	public void afterTextChanged(Editable recherche) {
		matchingPois.clear();
		for (PointOfInterest poi : allPois) {
			if (poi.matches(recherche.toString())) {
				matchingPois.add(poi);
			}
		}
		poiAdapter.notifyDataSetChanged();
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
		matchingPois.clear();
		for (PointOfInterest poi : allPois) {
			if (poi.isOfCategorie(categories.get(pos))) {
				matchingPois.add(poi);
			}
		}
		poiAdapter.notifyDataSetChanged();	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {}

}
