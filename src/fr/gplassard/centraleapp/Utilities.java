package fr.gplassard.centraleapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;

import com.google.android.maps.GeoPoint;

public class Utilities {

	public static void setImage(ImageView view, String url) throws IOException {
		final URLConnection conn = new URL(url).openConnection();
		conn.connect();
		final InputStream is = conn.getInputStream();
		final BufferedInputStream bis = new BufferedInputStream(is, 100000);
		final Bitmap bm = BitmapFactory.decodeStream(bis);
		bis.close();
		is.close();

		view.setImageBitmap(bm);
	}
	
	public static void openFavorisPopup(final PointOfInterest poi, final Activity activity, android.content.DialogInterface.OnClickListener onPositiveClickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (poi.isFavoris())
			builder.setMessage("Supprimer des favoris?");
		else
			builder.setMessage("Ajouter aux favoris?");
		builder.setPositiveButton("Oui", onPositiveClickListener);
		builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		});
		builder.create();
		builder.show();
	}

	public static void startPOIActivity(PointOfInterest poi, Activity launchingActivity) {
		Intent intentPOIActivity = new Intent(launchingActivity, PointOfInterestActivity.class);
		intentPOIActivity.putExtra(C.KEY_SELECTED_POI, poi.getId());
		launchingActivity.startActivity(intentPOIActivity);
	}

	public static void goToPoi(PointOfInterest currentPOI, Activity activity) {
		Intent i = new Intent(Intent.ACTION_VIEW);
//		GeoPoint currentPosition = ((MyApplication) activity.getApplication()).getLastKnownPosition();
//		double currentLat = ((double) currentPosition.getLatitudeE6()) / 1E6;
//		double currentLon = ((double) currentPosition.getLongitudeE6()) / 1E6;
		Uri u = Uri.parse(getUrl(currentPOI.getLatitude(), currentPOI.getLongitude()));
		i.setData(u);
		activity.startActivity(i); 
	}
	
	private static String getUrl(  double toLat, double toLon) {// connect to map web service
			  StringBuffer urlString = new StringBuffer();
			  urlString.append("http://maps.google.com/maps?f=d&hl=fr");
//			  urlString.append("&saddr=");// from
//			  urlString.append(Double.toString(fromLat));
//			  urlString.append(",");
//			  urlString.append(Double.toString(fromLon));
			  urlString.append("&daddr=");// to
			  urlString.append(Double.toString(toLat));
			  urlString.append(",");
			  urlString.append(Double.toString(toLon));
			  urlString.append("&ie=UTF8&0&om=0");
			  return urlString.toString();
			 }

	public static void startMapActivity(GeoPoint location, Activity launchingActivity) {
		Intent intentMainActivity = new Intent(launchingActivity, MainActivity.class);
		intentMainActivity.putExtra(C.GO_TO_LATITUDE, location.getLatitudeE6());
		intentMainActivity.putExtra(C.GO_TO_LONGITUDE, location.getLongitudeE6());
		launchingActivity.startActivity(intentMainActivity);
	}

}
