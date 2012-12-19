package com.example.centraleapp;
import java.util.List;

import android.app.Application;


public class MyApplication extends Application {
	private List<PointOfInterest> pois;
	
	public void setPOIS(List<PointOfInterest> pois){
		this.pois = pois;
	}
	
	public List<PointOfInterest> getPOIS(){
		return pois;
	}
}
