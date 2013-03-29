package com.sunilkumar.findplaces.places;

import org.json.JSONArray;

import com.sunilkumar.findplaces.AppBackend;
import com.sunilkumar.findplaces.JSONValueRetriever;
import com.sunilkumar.findplaces.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PlacesResultAdaptor extends BaseAdapter {

	private Context mContext=null;
	private PlacesResultRow view=null;
	private int mNumRows;
	private JSONArray mWebServiceData;
	public static String PLACE_SELECTED_REFERENCE="PLACE_SELECTED_REFERENCE";

	public PlacesResultAdaptor(Context context){
		this.mContext = context;
		this.mNumRows=AppBackend.webServiceResponse.length();
		Log.d(MainActivity.FIND_PLACES,"NUM ROWS :"+this.mNumRows);
		this.mWebServiceData=AppBackend.webServiceResponse;
	}
	@Override
	public int getCount() {
		return this.mNumRows;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			view = new PlacesResultRow(this.mContext); 
		}
		else{
			view=(PlacesResultRow)convertView;
		}
		view.setPlaceName(JSONValueRetriever.getStringValueFromJsonArray(mWebServiceData, "name", position));
		view.setPlaceAddress(JSONValueRetriever.getStringValueFromJsonArray(mWebServiceData, "vicinity", position));
		view.setPlaceDistance("Distance here");
		view.setTag(position);
		//need to get reference key from this selection to display the details,USE THE BELOW URL
		view.setOnClickListener( new View.OnClickListener()
		{
			public void onClick(View v)
			{
					callIntent(v);
			}

		});
		return view;
	}
	private void callIntent(View v){
		Log.i(MainActivity.FIND_PLACES,
		JSONValueRetriever.getStringValueFromJsonArray(mWebServiceData, "reference", Integer.parseInt( v.getTag().toString())));
		
		Intent displayPlacesDetailIntent=new Intent(mContext,PlacesResultDetailActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		displayPlacesDetailIntent.putExtra(PLACE_SELECTED_REFERENCE,JSONValueRetriever.getStringValueFromJsonArray(mWebServiceData, "reference", Integer.parseInt( v.getTag().toString())));
		mContext.startActivity(displayPlacesDetailIntent);
	}

}
