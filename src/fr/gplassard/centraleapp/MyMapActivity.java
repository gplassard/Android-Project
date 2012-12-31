package fr.gplassard.centraleapp;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyMapActivity extends MapActivity {
	private MyLocationOverlay myLocation;
	private MapView mapView;
	private MapController mapController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		mapView = (MapView) findViewById(R.id.map_view);
		mapController = mapView.getController();
		
		myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		mapView.getOverlays().add(myLocation);
		myLocation.enableMyLocation();
		
		myLocation.runOnFirstFix(new Runnable(){
			@Override
			public void run() {
				mapController.animateTo(myLocation.getMyLocation());
				mapController.setZoom(17);
			}			
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
