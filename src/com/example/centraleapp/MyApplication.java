package com.example.centraleapp;
import java.util.List;

import android.app.Application;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	private List<Integer> categories;
	
	public void setPOIS(List<PointOfInterest> pois){
		this.pois = pois;
	}
	
	public List<PointOfInterest> getPOIS(){
		return pois;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}
	
	public List<Integer> getCategories(){
		return categories;
	}
}
