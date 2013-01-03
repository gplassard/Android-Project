package fr.gplassard.centraleapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.util.Log;

import com.google.android.maps.GeoPoint;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	private List<String> categories;
	private List<PointOfInterest> favoris;
	private SharedPreferences preferences;
	private SharedPreferences.Editor preferencesEditor;
	private Map<Long,PointOfInterest> dico;
	
	public MyApplication(){
		super();
		favoris = new ArrayList<PointOfInterest>();
		dico = new HashMap<Long, PointOfInterest>();
	}
	
	public void setPOIS(List<PointOfInterest> pois){
		this.pois = pois;
		dico.clear();
		for (PointOfInterest poi : pois){
			dico.put(poi.getId(), poi);
		}
	}
	
	public List<PointOfInterest> getPOIS(){
		return pois;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	public List<String> getCategories(){
		return categories;
	}
	
	public List<PointOfInterest> getFavoris(){
		return favoris;
	}

	public void setFavoris(PointOfInterest poi, boolean isFavoris) {
		poi.setFavoris(isFavoris);
		if (isFavoris && !favoris.contains(poi)){
			favoris.add(poi);
			Collections.sort(favoris);
		}
		else if (!isFavoris){
			favoris.remove(poi);
		}
		preferencesEditor.putBoolean(poi.getId().toString(), poi.isFavoris());
		preferencesEditor.apply();
	}
	
	public void initializeFavoris(Activity activite){
		preferences = activite.getPreferences(Context.MODE_PRIVATE);
		preferencesEditor = preferences.edit();
		for (PointOfInterest poi : pois){
			String clef = poi.getId()+"";
			boolean isFavoris = preferences.getBoolean(clef, false);
			setFavoris(poi, isFavoris);
		}
	}
	
	public PointOfInterest getPOI(long id){
		return dico.get(id);
	}
	
	public GeoPoint getLastKnownPosition(){
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);   
		LocationProvider provider = lm.getProvider(LocationManager.GPS_PROVIDER);
		Log.d(C.TAG,provider.toString());
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);  
		Log.d(C.TAG,location.toString());
		double latitude = 0.0;
		double longitude = 0.0;
		
		if(location != null){
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
		
		return new GeoPoint((int) (latitude *1E6), (int) (longitude *1E6));
	}
}
