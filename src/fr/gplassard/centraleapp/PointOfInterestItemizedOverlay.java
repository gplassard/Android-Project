package fr.gplassard.centraleapp;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.MapView.LayoutParams;
import com.google.android.maps.OverlayItem;

public class PointOfInterestItemizedOverlay extends ItemizedOverlay<OverlayItem> implements OnClickListener {
	private ArrayList<OverlayItem> myOverlays;
	private MapView mapView;
	private Activity activity;
	private RelativeLayout layoutDescription;
	private PointOfInterest currentPOI;

	public PointOfInterestItemizedOverlay(Drawable defaultMarker, MapView mapView, Activity activity) {
		super(boundCenterBottom(defaultMarker));
		myOverlays = new ArrayList<OverlayItem>();
		this.mapView = mapView;
		this.activity = activity;

		LayoutInflater inflater = activity.getLayoutInflater();
		layoutDescription = (RelativeLayout) inflater.inflate(R.layout.layout_poi_on_map, mapView, false);
		populate();
	}

	public void addOverlay(OverlayItem overlay) {
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
	protected boolean onTap(int i) {
		GeoPoint point = myOverlays.get(i).getPoint();
		
		LayoutParams screenLP = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point,
				LayoutParams.TOP_LEFT);

		currentPOI = ((MyApplication) activity.getApplication()).getPOIS().get(i);

		((TextView) layoutDescription.findViewById(R.id.tvNom)).setText(currentPOI.getNom());
		((TextView) layoutDescription.findViewById(R.id.tvQuartier)).setText(currentPOI.getQuartier());
		((TextView) layoutDescription.findViewById(R.id.tvSecteur)).setText(currentPOI.getSecteur());
		try {
			Utilities.setImage((ImageView) layoutDescription.findViewById(R.id.ivIcone), currentPOI.getUrlImage());
		} catch (IOException e) {
			((ImageView) layoutDescription.findViewById(R.id.ivIcone)).setImageResource(C.IMAGE_NOT_FOUND);
		}

		((Button) layoutDescription.findViewById(R.id.buttonClose)).setOnClickListener(this);
		((Button) layoutDescription.findViewById(R.id.buttonDetails)).setOnClickListener(this);
		((Button) layoutDescription.findViewById(R.id.buttonFavoris)).setOnClickListener(this);
		((Button) layoutDescription.findViewById(R.id.buttonYAller)).setOnClickListener(this);

		mapView.removeView(layoutDescription);
		layoutDescription.setVisibility(View.VISIBLE);
		mapView.addView(layoutDescription, screenLP);

		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.buttonClose:
			layoutDescription.setVisibility(View.GONE);
			break;
		case R.id.buttonDetails:
			Utilities.startPOIActivity(currentPOI,activity);
			break;
		case R.id.buttonFavoris:
			openFavorisPopup();
			break;
		case R.id.buttonYAller:
			Utilities.goToPoi(currentPOI);
			break;
		}

	}


	private void openFavorisPopup() {
		DialogInterface.OnClickListener positiveClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				((MyApplication) activity.getApplication()).setFavoris(currentPOI, !currentPOI.isFavoris());
				dialog.dismiss();
			}
		};
		Utilities.openFavorisPopup(currentPOI, activity, positiveClickListener);
	}

}
