package fr.gplassard.centraleapp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FavorisActivity extends Activity implements OnItemClickListener {
	List<PointOfInterest> favoris;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoris);
		favoris = ((MyApplication) getApplication()).getFavoris();


		ListView listView = (ListView) findViewById(R.id.listView1);
		PointOfInterestLittleAdapter poiAdapter = new PointOfInterestLittleAdapter(this, favoris,this);
		listView.setAdapter(poiAdapter);

		listView.setOnItemClickListener(this);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intentPointOfInterest = new Intent(this,	PointOfInterestActivity.class);
		intentPointOfInterest.putExtra(C.SELECTED_POI, favoris.get(position));
		//intentPointOfInterest.putExtra(C.KEY_POI_SELECTED,favoris.get(position).getId());
		startActivity(intentPointOfInterest);
		
	}
}
