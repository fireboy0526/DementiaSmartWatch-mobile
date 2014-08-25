package com.agileninjas.dementiasmartwatch.tests;

import android.location.Location;
//import android.location.LocationManager;
import org.junit.Test;

import com.agileninjas.dementiasmartwatch.GPSLocation;

import static org.junit.Assert.*;

public class GPSLocationTest{
	
	private static final String PROVIDER = "My Provider";
    private static final double LAT = 37.377166;
    private static final double LNG = -122.086966;
    private Location mLocation = new Location(PROVIDER);
    private static final double EPSILON = 10e-07;
    private final GPSLocation testGPS = new GPSLocation();
    //private final LocationManager mLocationManager;

    @Test
	public void getLongitudeTest() {
		mLocation.setLongitude(LNG);
		assertEquals(mLocation.getLongitude(), LNG, EPSILON);
	}
	
	@Test
	public void getLatitudeTest() {
		mLocation.setLatitude(LAT);
		assertEquals(mLocation.getLatitude(), LAT, EPSILON);
	}
	
//	@Test
//	public void onLocationChangedTest() {
//		testGPS.onLocationChanged(mLocation);
//		assertFalse(testGPS.getChanged());
//		mLocation.setLatitude(38.00);
//		testGPS.onLocationChanged(mLocation);
//		assertTrue(testGPS.getChanged());
//		mLocation.setLatitude(LAT);
//		mLocation.setLongitude(-123.00);
//		testGPS.onLocationChanged(mLocation);
//		assertTrue(testGPS.getChanged());
//	}
}
