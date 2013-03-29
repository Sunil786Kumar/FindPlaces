package com.sunilkumar.findplaces.places;
import org.json.JSONException;
import org.json.JSONObject;

import com.sunilkumar.findplaces.AppBackend;
import com.sunilkumar.findplaces.DisplaySpinner;
import com.sunilkumar.findplaces.ModalData;
import com.sunilkumar.findplaces.R;
import com.sunilkumar.findplaces.R.id;
import com.sunilkumar.findplaces.R.layout;
import com.sunilkumar.findplaces.web.WebViewActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PlacesResultDetailActivity extends Activity {

	private String REFERENCE_KEY=null;
	private Handler mHandler=null;
	private Context mContext=null;

	private TextView mPlaceName=null;
	private TextView mPlacePhone=null;
	private TextView mPlaceBlog=null;
	private String mURL=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places_details);

		mContext = this;
		REFERENCE_KEY=getIntent().getStringExtra(PlacesResultAdaptor.PLACE_SELECTED_REFERENCE);
		mPlaceName=(TextView)findViewById(R.id.place_detail_id_name);
		mPlacePhone=(TextView)findViewById(R.id.place_detail_id_phone);
		mPlaceBlog=(TextView)findViewById(R.id.place_detail_id_blog);

		setmPlacesName("");
		setmPlacesPhone("");
		setmPlacesBlog("");

		mHandler=new Handler();
		DisplaySpinner.displaySpinner(this,ModalData.SPINNER_HEADING_PLACES_DETAIL, ModalData.SPINNER_MESSAGE_PLACES_DETAIL);

		startGettingPlacesData.start();
	}
	private Thread startGettingPlacesData = new Thread(){
		public void run(){
			try {
				AppBackend.callWebServiceWithURL
				((AppBackend.createURLRequestForMoreDetailsOfAPlace(REFERENCE_KEY)),mContext);
				mHandler.post(showUpdate);

			} catch (Exception e) {
				System.out.println("Longitude and Latitude is not set");
			}
		}
	};
	private Runnable showUpdate = new Runnable(){
		public void run(){
			startUpdatingUI();
			DisplaySpinner.hideSpinner();
		}
	};    
	private void startUpdatingUI(){
		JSONObject placesDetailJSONObject=AppBackend.placesDetailJSONObject;
		try {
			setmPlacesName(placesDetailJSONObject.getString("name"));
			setmPlacesPhone(placesDetailJSONObject.getString("international_phone_number"));
			setmPlacesBlog("Blog address");
			setmURL(placesDetailJSONObject.getString("url"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void setmPlacesName(String placeName){
		this.mPlaceName.setText(placeName);
	}
	private void setmPlacesPhone(String placePhone){
		this.mPlacePhone.setText(placePhone);

	}
	private void setmPlacesBlog(String placeBlog){
		this.mPlaceBlog.setText(placeBlog);
	}
	private void setmURL(String URL){
		this.mURL=URL;
	}
	public void goToUrl(View v){
		WebViewActivity.mURL=mURL;
		Intent openURL=new Intent(this,WebViewActivity.class);
		startActivity(openURL);
	}
}
