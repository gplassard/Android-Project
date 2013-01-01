package fr.gplassard.centraleapp;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PointOfInterestItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem> myOverlays;
	
	public PointOfInterestItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		myOverlays = new ArrayList<OverlayItem>();
		populate();
	}
	
	public void addOverlay(OverlayItem overlay){
		myOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		return myOverlays.get(i);
	}
	
	@Override
	public int size() {
		return myOverlays.size();
	}
	
	@Override
	protected boolean onTap(int i){
		GeoPoint point = myOverlays.get(i).getPoint();
		double lat = point.getLatitudeE6() / 1E6;
		double lon = point.getLongitudeE6() / 1E6;
		
		return true;
	}

}
