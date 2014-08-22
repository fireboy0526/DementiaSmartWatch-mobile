package com.agileninjas.dementiasmartwatch;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class GPSLocation implements LocationListener {
	private static LocationManager locationManager;
	private static String provider;
	private static Criteria criteria;
	private double gLatitude, gLongitude;

	public static void runGPS(final Context context) {
		final GPSLocation gps = new GPSLocation();
		gps.start(context);
		
		//To continuously get the GPS connection after first load
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				gps.start(context);
				handler.postDelayed(this, 1000);
			}
		},1000);
	}
	
	public void start(Context context) {
		 //Getting LocationManager Object
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        
        //Creating an empty criteria object
        criteria = new Criteria();
        
        //Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);
        
        if(provider != null && !provider.equals("")) {
        	//Get the location from the given provider
        	Location location = locationManager.getLastKnownLocation(provider);
        	
        	locationManager.requestLocationUpdates(provider, 1000, 1, this);
        	
        	if(location != null) {
        		onLocationChanged(location);
        	}
        } else {
        	Toast.makeText(context, "No Provider Found", Toast.LENGTH_SHORT).show();
        }
	}
	
	
	
	//To return Longitude
	public double getLongitude() {
		return gLongitude;
	}
	
	//To return Latitude
	public double getLatitude() {
		return gLatitude;
	}

	//Run this when location changes
	public void onLocationChanged(Location location) {
		gLongitude = (double)(location.getLongitude());
		gLatitude = (double)(location.getLatitude());
		System.out.println("Lon: " + gLongitude + ", Lan: " + gLatitude);
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
}
