package com.sunilkumar.findplaces;

import com.sunilkumar.findplaces.places.PlacesActivity;
import com.sunilkumar.findplaces.search.SearchMainActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {

	public static final String FIND_PLACES="FIND_PLACES";
	private LocationListener mLocationListener;
	private LocationManager mLocationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Acquire a reference to the system Location Manager
		mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		mLocationListener = new MyLocationListener();

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(FIND_PLACES, "Resume");
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(FIND_PLACES, "Pause");
		mLocationManager.removeUpdates(mLocationListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_places:
			Log.d(FIND_PLACES,"Places selected");
			Intent placesIntent = new Intent(this,PlacesActivity.class);
			startActivity(placesIntent);
			break;
		case R.id.action_search:
			Intent searchIntent = new Intent(this,SearchMainActivity.class);
			startActivity(searchIntent);
			Log.d(FIND_PLACES,"Search selected");
			break;
			
		}
		return true;
	}
}

//	private class myLocationListener implements LocationListener
//	{
//		@Override
//		public void onStatusChanged(String provider, int status, Bundle extras) {
//			Log.d(MainActivity.FIND_PLACES,"location is :"+"onLocationChanged"+provider);
//
//		}
//
//		@Override
//		public void onProviderEnabled(String provider) {
//			Log.d(MainActivity.FIND_PLACES,"location is :"+"onLocationChanged"+provider);
//
//		}
//
//		@Override
//		public void onProviderDisabled(String provider) {
//			Log.d(MainActivity.FIND_PLACES,"location is :"+"onLocationChanged"+provider);
//		}
//
//		@Override
//		public void onLocationChanged(Location location) {
//			Log.d(MainActivity.FIND_PLACES,"location is :"+"onLocationChanged"+location);
//			if(location!=null){
//				Toast.makeText(getBaseContext(), "Location changed:Lat: "+location.getLatitude()
//						+"Lng: "+location.getLongitude(), Toast.LENGTH_SHORT).show();
//			}
//		}
//	}
//}

/*
 	//Find the last known location
		Location lastKnownLocation =
				locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (lastKnownLocation != null) {
			Log.i(FIND_PLACES, lastKnownLocation.getLatitude() + ", "
					+ lastKnownLocation.getLongitude());
			locationManager.removeUpdates((LocationListener) this);
		} else
			Log.i(FIND_PLACES, "null");
 */

