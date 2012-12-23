package fr.gplassard.centraleapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabHost = getTabHost();
		
		Intent intentList = new Intent(this,MyListActivity.class);
		Intent intentFavoris = new Intent(this,FavorisActivity.class);
		Intent intentMap = new Intent(this,MyMapActivity.class);
		
		TabSpec tabSpec = tabHost.newTabSpec("Liste").setIndicator("Liste").setContent(intentList);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("Favoris").setIndicator("Favoris").setContent(intentFavoris);
		tabHost.addTab(tabSpec);
		tabSpec= tabHost.newTabSpec("Carte").setIndicator("Carte").setContent(intentMap);
		tabHost.addTab(tabSpec);
		
		 tabHost.setCurrentTab(0);
	}

}
