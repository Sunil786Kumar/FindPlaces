package com.sunilkumar.findplaces.places;

import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PlacesActivity extends Activity {

	private ListView mPlacesList=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places_list);

		this.mPlacesList = (ListView)findViewById(R.id.list_items_places);
		
		PlacesListAdaptor placesListAdaptor = new PlacesListAdaptor(PlacesActivity.this);
		this.mPlacesList.setAdapter(placesListAdaptor);
	}
	

}
