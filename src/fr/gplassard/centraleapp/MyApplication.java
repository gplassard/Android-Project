package fr.gplassard.centraleapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	private List<String> categories;
	private List<PointOfInterest> favoris;
	private SharedPreferences preferences;
	private SharedPreferences.Editor preferencesEditor;
	
	public MyApplication(){
		super();
		favoris = new ArrayList<PointOfInterest>();
	}
	
	public void setPOIS(List<PointOfInterest> pois){
		this.pois = pois;
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
		preferencesEditor.commit();
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
}
