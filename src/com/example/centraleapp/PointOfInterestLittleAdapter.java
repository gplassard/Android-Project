package com.example.centraleapp;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class PointOfInterestLittleAdapter extends BaseAdapter {
	List<PointOfInterest> biblio;
	LayoutInflater inflater;
	Context context;

	public PointOfInterestLittleAdapter(Context context,
			List<PointOfInterest> objects) {
		inflater = LayoutInflater.from(context);
		this.biblio = objects;
		this.context = context;
		ImageLoader imageLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
		imageLoader.init(config);
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
			holder.iconeFavoris = (ImageView) convertView.findViewById(R.id.iconeFavori);
			convertView.setTag(holder);
		} else {
			Log.v("test", "convertView is not null");
			holder = (ViewHolder) convertView.getTag();
		}

		PointOfInterest poi = biblio.get(position);

		//ImageLoader.getInstance().displayImage(poi.getUrlSmallImage(), holder.icone);
		holder.icone.setImageResource(R.drawable.ic_launcher);
		holder.tvNom.setText(poi.getNom());
		holder.tvDescription.setText(poi.getShortDescription());
		if (poi.isFavorite()){
			holder.iconeFavoris.setImageResource(R.drawable.defacto_poi_ajoutfavoris_b);
		}
		else{
			holder.iconeFavoris.setImageResource(R.drawable.defacto_poi_ajoutfavoris);
		}
		
		return convertView;
	}

	private class ViewHolder {
		ImageView icone;
		TextView tvNom;
		TextView tvDescription;
		ImageView iconeFavoris;
	}

}
