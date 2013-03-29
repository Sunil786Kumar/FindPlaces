package com.sunilkumar.findplaces.places;



import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlacesListRow extends LinearLayout {

	private TextView mPlacesTabText;

	public PlacesListRow(Context context) {
		super(context);
		LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
		layoutInflater.inflate(R.layout.places_row, this);

		this.mPlacesTabText = (TextView)findViewById(R.id.places_row_textview);

	}

	public void setTextForPlacesTab(String placesTab){
		this.mPlacesTabText.setText(placesTab);
	}

	public String getTextForPlacesTab(){
		return this.mPlacesTabText.getText().toString();
	}
}
