package com.example.centraleapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoadingActivity extends Activity {

	private ProgressBar progressBar;
	private List<PointOfInterest> pois;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // or
																		// .detectAll()
																		// for
																		// all
																		// detectable
																		// problems
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar.setIndeterminate(false);
		
		pois = new ArrayList<PointOfInterest>();
		
		loadData();
	}

	private void loadData() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://cci.corellis.eu/pois.php");
		try {
			HttpResponse response = httpClient.execute(httpGet);
			if (response != null) {
				String line = "";
				InputStream inputStream = response.getEntity().getContent();
				line = convertStreamToString(inputStream);
				try {
					JSONObject jsonObject = new JSONObject(line);
					JSONArray jsonArray = jsonObject.getJSONArray("results");
					progressBar.setMax(jsonArray.length());
					for (int i = 0; i < jsonArray.length(); i++) {
						progressBar.setProgress(i);
						PointOfInterest poi = makePointOfInterest(jsonArray.getJSONObject(i));
						pois.add(poi);
						Log.i(C.TAG, poi.toString());
						Log.i(C.TAG,i+"/"+jsonArray.length());
					}
					launchMainActivity();

				} catch (JSONException e) {
					Toast.makeText(this, e.getMessage(),
							Toast.LENGTH_LONG).show();
					Log.e(C.TAG,e.getMessage());
				}
			} else {
				Toast.makeText(this, "Unable to complete the request",
						Toast.LENGTH_LONG).show();
			}
		} catch (ClientProtocolException e) {
			Toast.makeText(this, e.getMessage(),
					Toast.LENGTH_LONG).show();
			Log.e(C.TAG,e.getMessage());
		} catch (IOException e) {
			Toast.makeText(this, e.getMessage(),
					Toast.LENGTH_LONG).show();
			Log.e(C.TAG,e.getMessage());
		}

	}

	private void launchMainActivity() {
		((MyApplication) getApplication()).setPOIS(pois);
		startActivity(new Intent(this, MainActivity.class));
	}

	private PointOfInterest makePointOfInterest(JSONObject jsonObject)
			throws JSONException {
		PointOfInterest poi = new PointOfInterest();
		poi.setNom(jsonObject.getString("nom"));
		poi.setSecteur(jsonObject.getString("secteur"));
		poi.setCategorie(jsonObject.getString("categorie_id"));
		poi.setInformations(jsonObject.getString("informations"));
		poi.setLatitude(jsonObject.getDouble("lat"));
		poi.setLongitude(jsonObject.getDouble("lon"));
		poi.setQuartier(jsonObject.getString("quartier"));
		poi.setUrlImage(jsonObject.getString("image"));
		poi.setUrlSmallImage(jsonObject.getString("small_image"));
		return poi;
	}

	private String convertStreamToString(InputStream inputStream) {
		String ligne = "";
		StringBuilder total = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
		try {
			while ((ligne = rd.readLine()) != null) {
				total.append(ligne);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return total.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_loading, menu);
		return true;
	}

}
