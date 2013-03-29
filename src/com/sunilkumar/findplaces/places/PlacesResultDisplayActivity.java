package com.sunilkumar.findplaces.places;

import com.sunilkumar.findplaces.AppBackend;
import com.sunilkumar.findplaces.DisplaySpinner;
import com.sunilkumar.findplaces.ModalData;
import com.sunilkumar.findplaces.MyLocationListener;
import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class PlacesResultDisplayActivity extends Activity {

	private ListView mResultPlacesList = null;
	private Handler mHandler = null;
	private Context mContext=null;

	/*
	 * public API to take String from other Activities
	 */
	public static String mSelectedPlaceCategory=null;

	/*
	 * Other things required are longitude and latitude and callingClass
	 */
	public static String mCallingClass=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.places_result);
		super.onCreate(savedInstanceState);

		//mSelectedPlace = getIntent().getIntExtra(PlacesListAdaptor.PLACE_SELECTED, ModalData.DEFAULT_VALUE);
		Toast.makeText(this, mSelectedPlaceCategory+MyLocationListener.latitude+MyLocationListener.longitude, Toast.LENGTH_LONG).show();
		this.mResultPlacesList = (ListView)findViewById(R.id.places_result_listview);
		mContext=this;

		this.mHandler=new Handler();

		DisplaySpinner.displaySpinner(this,ModalData.SPINNER_HEADING_PLACES_RESULT,ModalData.SPINNER_MESSAGE_PLACES_RESULT);
		startGettingPlacesData.start();
	}
	private Thread startGettingPlacesData = new Thread(){
		public void run(){
			try {
				if(MyLocationListener.latitude==null || MyLocationListener.longitude==null ){
					DisplaySpinner.hideSpinner();
					throw new NullPointerException();
				}
				else{
					AppBackend.callWebServiceWithURL(AppBackend.createURLRequestToGetReleventPlaces
							(mSelectedPlaceCategory,mCallingClass),mContext);


					mHandler.post(showUpdate);
				}
			} catch (Exception e) {
				if(e.equals(NullPointerException.class))
					System.out.println("Longitude and Latitude is not set");
			}
		}
	};
	private Runnable showUpdate = new Runnable(){
		public void run(){
			PlacesResultAdaptor rowAdaptor = new PlacesResultAdaptor(PlacesResultDisplayActivity.this);
			mResultPlacesList.setAdapter(rowAdaptor);
			DisplaySpinner.hideSpinner();
		}
	};    
}
