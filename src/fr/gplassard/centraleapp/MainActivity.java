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
		
		if (launchMap()){
			intentMap.putExtras(getIntent());
		}
		
		TabSpec tabSpec = tabHost.newTabSpec("Liste").setIndicator("Liste").setContent(intentList);
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec("Favoris").setIndicator("Favoris").setContent(intentFavoris);
		tabHost.addTab(tabSpec);
		tabSpec= tabHost.newTabSpec("Carte").setIndicator("Carte").setContent(intentMap);
		tabHost.addTab(tabSpec);
		
		if (launchMap()){
			tabHost.setCurrentTab(2);
		}
		else{
			tabHost.setCurrentTab(0);
		}		
	}

	private boolean launchMap() {
		Bundle extras = getIntent().getExtras();
		if (extras == null){
			return false;
		}
		return extras.containsKey(C.GO_TO_LATITUDE) && extras.containsKey(C.GO_TO_LONGITUDE);
	}

}
