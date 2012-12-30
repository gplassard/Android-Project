package fr.gplassard.centraleapp;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	private List<String> categories;
	private List<PointOfInterest> favoris;
	
	public MyApplication(){
		super();
		favoris = new ArrayList<PointOfInterest>();
//		for (int i=0; i< pois.size();i++){
//			if (i%25==0){
//				favoris.add(pois.get(i));
//				pois.get(i).setFavoris(true);
//			}
//		}
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
		if (isFavoris){
			favoris.add(poi);
		}
		else{
			favoris.remove(poi);
		}
		
	}
}
