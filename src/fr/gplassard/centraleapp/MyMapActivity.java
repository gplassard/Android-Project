package fr.gplassard.centraleapp;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MyMapActivity extends MapActivity {
	private MyLocationOverlay myLocation;
	private MapView mapView;
	private MapController mapController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();
		
		int latitude = getIntent().getIntExtra(C.GO_TO_LATITUDE, 0);
		int longitude = getIntent().getIntExtra(C.GO_TO_LONGITUDE,0);
		
		myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		myLocation.enableMyLocation();
		if (latitude != 0.0 && longitude != 0.0){
			GeoPoint destination = new GeoPoint(latitude,longitude);
			mapController.animateTo(destination);
			mapController.setZoom(20);
		}
		else{
			myLocation.runOnFirstFix(new Runnable(){
				@Override
				public void run() {
					mapController.animateTo(myLocation.getMyLocation());
					mapController.setZoom(17);
				}			
			});
		}
		
		mapView.getOverlays().add(myLocation);		
		addOverlays();
	}

	private void addOverlays() {
		List<Overlay> overlays = mapView.getOverlays();
		Drawable icone = getResources().getDrawable(C.ICONE_ON_MAP);
		PointOfInterestItemizedOverlay itemizedOverlay = new PointOfInterestItemizedOverlay(icone,mapView,this);
		
		List<PointOfInterest> pois = ((MyApplication) getApplication()).getPOIS();
		for (PointOfInterest poi : pois){
			OverlayItem item = new OverlayItem(poi.getLocation(), poi.getNom(),poi.getShortDescription());
			itemizedOverlay.addOverlay(item);
		}
		overlays.add(itemizedOverlay);
		mapView.postInvalidate();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
