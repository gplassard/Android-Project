package com.example.centraleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PointOfInterestActivity extends Activity implements OnClickListener {
	private PointOfInterest poi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_point_of_interest);
		int indice = getIntent().getExtras().getInt(MyListActivity.KEY_INDICE_SELECTED);
		poi = ((MyApplication) getApplication()).getPOIS().get(indice);
		full();
	}

	private void full() {
		((ImageView) findViewById(R.id.icone)).setImageResource(R.drawable.ic_launcher);
		((TextView) findViewById(R.id.nom)).setText(poi.getNom());
		((TextView) findViewById(R.id.quartier)).setText(poi.getQuartier());
		((TextView) findViewById(R.id.secteur)).setText(poi.getSecteur());
		((TextView) findViewById(R.id.informations)).setText(poi.getInformations());
		if (poi.isFavorite()){
			((ImageView) findViewById(R.id.iconeFavoris)).setImageResource(R.drawable.defacto_poi_ajoutfavoris_b);
		}
		else{
			((ImageView) findViewById(R.id.iconeFavoris)).setImageResource(R.drawable.defacto_poi_ajoutfavoris);
		}		
		((Button) findViewById(R.id.boutonFavoris)).setOnClickListener(this);
		((Button) findViewById(R.id.boutonCarte)).setOnClickListener(this);
		((Button) findViewById(R.id.boutonYAller)).setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_point_of_interest, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		
	}

}
