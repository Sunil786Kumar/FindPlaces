package com.sunilkumar.findplaces.search;
import org.json.JSONArray;

import com.sunilkumar.findplaces.AppBackend;
import com.sunilkumar.findplaces.DisplaySpinner;
import com.sunilkumar.findplaces.JSONValueRetriever;
import com.sunilkumar.findplaces.MainActivity;
import com.sunilkumar.findplaces.ModalData;
import com.sunilkumar.findplaces.MyLocationListener;
import com.sunilkumar.findplaces.places.PlacesResultDetailActivity;
import com.sunilkumar.findplaces.places.PlacesResultDisplayActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SearchBaseAdaptor extends BaseAdapter {

	private Context mContext=null;
	private SearchRow view=null;
	private Handler mHandler=null;
	private String mSearchString=null;
	private JSONArray mSearchResponse=null;
	private String mLongitude=null;
	private String mLatitude=null;


	public SearchBaseAdaptor(Context context){
		this.mContext=context;
	}
	@Override
	public int getCount() {
		if(mSearchResponse==null)
			return 0;
		else
			return mSearchResponse.length();
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
			view = new SearchRow(this.mContext); 
		}
		else{
			view=(SearchRow)convertView;
		}

		view.setmSuburbName(JSONValueRetriever.getStringValueFromJsonArray(mSearchResponse, "line2", position));
		view.setmSuburbAddress(JSONValueRetriever.getStringValueFromJsonArray(mSearchResponse, "state", position)+","+
				JSONValueRetriever.getStringValueFromJsonArray(mSearchResponse, "country", position));
		mLongitude=JSONValueRetriever.getStringValueFromJsonArray(mSearchResponse, "longitude", position);
		mLatitude=JSONValueRetriever.getStringValueFromJsonArray(mSearchResponse, "latitude", position);

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
		Intent displayPlacesDetailIntent=new Intent(mContext,PlacesResultDisplayActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		MyLocationListener.latitude=mLatitude;
		MyLocationListener.longitude=mLongitude;
		PlacesResultDisplayActivity.mCallingClass=ModalData.CALLING_CLASS_SEARCH;
		SearchMainActivity mainSearchActivity=(SearchMainActivity)mContext;
		Log.d(MainActivity.FIND_PLACES,"Search is :"+mainSearchActivity.getSearchString());
		PlacesResultDisplayActivity.mSelectedPlaceCategory=mainSearchActivity.getSearchString();
		mContext.startActivity(displayPlacesDetailIntent);
	}
	public void filterData(CharSequence searchString){
		mSearchString=searchString.toString();
		mHandler=new Handler();
		DisplaySpinner.displaySpinner(mContext, ModalData.SPINNER_HEADING_SEARCH, ModalData.SPINNER_MESSAGE_SEARCH);
		if(startGettingPlacesDataThread.getState() == Thread.State.TERMINATED){
			startGettingPlacesDataThread.run();
		}
		else{
			startGettingPlacesDataThread.start();
		}
	}
	private Thread startGettingPlacesDataThread = new Thread(){
		public void run(){
			try {
				AppBackend.callWebServiceWithURL
				(AppBackend.createURLRequestForSearchPlaces(mSearchString),mContext);
				mHandler.post(showUpdate);
			} catch (Exception e) {
			}
		}
	};
	private Runnable showUpdate = new Runnable(){
		public void run(){
			startGettingPlacesDataThread.interrupt();
			if(AppBackend.searchDetailResponse!=null) {
				mSearchResponse=AppBackend.searchDetailResponse;
			}
			DisplaySpinner.hideSpinner();
		}
	};    
}

