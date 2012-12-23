package com.example.centraleapp;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PointOfInterestLittleAdapter extends BaseAdapter {
	List<PointOfInterest> biblio;
	LayoutInflater inflater;
	Context context;

	public PointOfInterestLittleAdapter(Context context,
			List<PointOfInterest> objects) {
		inflater = LayoutInflater.from(context);
		this.biblio = objects;
		this.context = context;
	}

	@Override
	public int getCount() {
		return biblio.size();
	}

	@Override
	public PointOfInterest getItem(int position) {
		return biblio.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			Log.v(C.TAG, "convertView is null");
			holder = new ViewHolder();
			convertView = inflater.inflate(	R.layout.point_of_interest_little_item, null);
			holder.icone = (ImageView) convertView.findViewById(R.id.image);
			holder.tvNom = (TextView) convertView.findViewById(R.id.textViewNom);
			holder.tvDescription = (TextView) convertView.findViewById(R.id.textViewQuartierSecteur);
			holder.tvCategorie = (TextView) convertView.findViewById(R.id.textViewCategorie);
			convertView.setTag(holder);
		} else {
			Log.v("test", "convertView is not null");
			holder = (ViewHolder) convertView.getTag();
		}

		PointOfInterest poi = biblio.get(position);
		
		try {
			Utilities.setImage(holder.icone, poi.getUrlSmallImage());
		} catch (IOException e) {
			holder.icone.setImageResource(R.drawable.ic_launcher);
			Log.i(C.TAG,e.getMessage());
		}
		
		holder.tvNom.setText(poi.getNom());
		holder.tvDescription.setText(poi.getShortDescription());
		holder.tvCategorie.setText(poi.getCategorie());
		
		return convertView;
	}

	private class ViewHolder {
		ImageView icone;
		TextView tvNom;
		TextView tvDescription;
		TextView tvCategorie;
	}

}
