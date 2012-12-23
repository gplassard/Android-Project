package fr.gplassard.centraleapp;
import java.util.List;

import android.app.Application;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	private List<String> categories;
	
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
}
