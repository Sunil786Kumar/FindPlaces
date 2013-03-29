package com.sunilkumar.findplaces.places;

import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlacesResultRow extends RelativeLayout {

	private TextView mPlaceName;
	private TextView mPlaceAddress;
	private TextView mPlaceDistance;

	public PlacesResultRow(Context context) {
		super(context);
		LayoutInflater layoutInflator = ((Activity)context).getLayoutInflater();
		layoutInflator.inflate(R.layout.places_result_row, this);

		this.mPlaceName=(TextView)findViewById(R.id.places_result_row_placename);
		this.mPlaceAddress=(TextView)findViewById(R.id.places_result_row_result_address);
		this.mPlaceDistance=(TextView)findViewById(R.id.places_result_row_result_distance);
	}

	public void setPlaceName(String placeName){
		this.mPlaceName.setText(placeName);
	}

	public void setPlaceAddress(String placeAddress){
		this.mPlaceAddress.setText(placeAddress);
	}

	public void setPlaceDistance(String placeDistance){
		this.mPlaceDistance.setText(placeDistance);
	}



}
