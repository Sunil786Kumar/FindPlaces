package com.sunilkumar.findplaces;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
	
	public static String longitude=null;
	public static String latitude=null;
	
	@Override
	public void onLocationChanged(Location location) {
		if(longitude!=null) longitude =null;
		if(latitude!=null) latitude=null;
		
		Log.d(MainActivity.FIND_PLACES,"location is :"+"Location :"
	+location.getLatitude()+"Longitude :"+location.getLongitude());
	
		longitude = location.getLongitude()+"";
		latitude = location.getLatitude()+"";
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
