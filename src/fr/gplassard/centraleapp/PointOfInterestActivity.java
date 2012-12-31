package fr.gplassard.centraleapp;

import java.io.IOException;

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
		poi = (PointOfInterest) getIntent().getExtras().get(C.SELECTED_POI);
		full();
	}

	private void full() {
		ImageView imageViewIcone = (ImageView) findViewById(R.id.icone);
		try {
			Utilities.setImage(imageViewIcone, poi.getUrlImage());
		} catch (IOException e) {
			imageViewIcone.setImageResource(C.IMAGE_NOT_FOUND);
//			Log.i(C.TAG,"Image not found : "+e.getMessage());
		}
		((TextView) findViewById(R.id.nom)).setText(poi.getNom());
		((TextView) findViewById(R.id.quartier)).setText(poi.getQuartier());
		((TextView) findViewById(R.id.secteur)).setText(poi.getSecteur());
		((TextView) findViewById(R.id.informations)).setText(poi.getInformations().replaceAll("</br>","\n"));
		if (poi.isFavoris()){
			((ImageView) findViewById(R.id.iconeFavoris)).setImageResource(C.IMAGE_FAVORIS);
		}
		else{
			((ImageView) findViewById(R.id.iconeFavoris)).setImageResource(C.IMAGE_PAS_FAVORIS);
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
