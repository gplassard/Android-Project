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

	public static void goToPoi(PointOfInterest currentPOI) {
		// TODO Auto-generated method stub
		
	}

	public static void startMapActivity(GeoPoint location, Activity launchingActivity) {
		Intent intentMapActivity = new Intent(launchingActivity, MyMapActivity.class);
		intentMapActivity.putExtra(C.GO_TO_LATITUDE, location.getLatitudeE6());
		intentMapActivity.putExtra(C.GO_TO_LONGITUDE, location.getLongitudeE6());
		launchingActivity.startActivity(intentMapActivity);
	}

}
