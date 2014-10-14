package com.agileninjas.dementiasmartwatch;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

public class MockLocationProvider {
	
	  private String mProvider;
	  private Context mContex;
	 
	  public MockLocationProvider(String name, Context ctx) {
	    this.mProvider = name;
	    this.mContex = ctx;
	 
	    LocationManager mLocationManager = (LocationManager) ctx.getSystemService(
	      Context.LOCATION_SERVICE);
	    mLocationManager.addTestProvider(mProvider, false, false, false, false, false, 
	      true, true, 0, 5);
	    mLocationManager.setTestProviderEnabled(mProvider, true);
	  }
	 
	  public void pushLocation(double lat, double lon) {
	    LocationManager mLocationManager = (LocationManager) mContex.getSystemService(
	      Context.LOCATION_SERVICE);
	 
	    Location mockLocation = new Location(mProvider);
	    mockLocation.setLatitude(lat);
	    mockLocation.setLongitude(lon); 
	    mockLocation.setAltitude(0); 
	    mockLocation.setTime(System.currentTimeMillis()); 
	    mLocationManager.setTestProviderLocation(mProvider, mockLocation);
	  }
	 
	  public void shutdown() {
	    LocationManager mLocationManager = (LocationManager) mContex.getSystemService(
	      Context.LOCATION_SERVICE);
	    mLocationManager.removeTestProvider(mProvider);
	  }
	}
