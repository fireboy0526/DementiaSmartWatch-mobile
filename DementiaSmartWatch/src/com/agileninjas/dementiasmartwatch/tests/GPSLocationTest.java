package com.agileninjas.dementiasmartwatch.tests;

import com.agileninjas.dementiasmartwatch.GPSLocation;

import android.location.Location;
//import android.location.LocationManager;
import android.test.AndroidTestCase;


public class GPSLocationTest extends AndroidTestCase{

	private static final String PROVIDER = "My Provider";
    private static final double LAT = 37.377166;
    private static final double LNG = -122.086966;
    private Location mLocation = new Location(PROVIDER);
    private final GPSLocation testGPS = new GPSLocation();
    //private final LocationManager mLocationManager;

	public void testGetLongitude() {
		mLocation.setLongitude(LNG);
		assertEquals(mLocation.getLongitude(), LNG);
	}
	
	public void testGetLatitude() {
		mLocation.setLatitude(LAT);
		assertEquals(mLocation.getLatitude(), LAT);
	}
	
	public void testOnLocationChanged() {
		testGPS.onLocationChanged(mLocation);
		assertFalse(testGPS.getLocationChanged());
		mLocation.setLatitude(38.00);
		testGPS.onLocationChanged(mLocation);
		assertTrue(testGPS.getLocationChanged());
		mLocation.setLatitude(LAT);
		mLocation.setLongitude(-123.00);
		testGPS.onLocationChanged(mLocation);
		assertTrue(testGPS.getLocationChanged());
	}
}
