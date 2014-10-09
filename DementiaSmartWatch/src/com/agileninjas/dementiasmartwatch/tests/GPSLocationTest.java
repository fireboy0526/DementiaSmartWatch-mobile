package com.agileninjas.dementiasmartwatch.tests;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.agileninjas.dementiasmartwatch.GPSLocation;
import com.agileninjas.dementiasmartwatch.GPSLocation.asyncTask;

import android.location.Location;
import android.os.AsyncTask;
//import android.location.LocationManager;
import android.test.AndroidTestCase;

//import com.robotium.solo.Solo;


public class GPSLocationTest extends android.test.InstrumentationTestCase{

	private static final String PROVIDER = "My Provider";
    private static final double LAT = 37.377166;
    private static final double LNG = -122.086966;
    private Location mLocation = new Location(PROVIDER);
    private final GPSLocation testGPS = new GPSLocation();
    //private final LocationManager mLocationManager;
    //private Solo solo; 
    
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
	
	public void testAsynTask() throws Throwable {
	    // create  a signal to let us know when our task is done.
	    final CountDownLatch signal = new CountDownLatch(1);

	    /* Just create an in line implementation of an asynctask. Note this 
	     * would normally not be done, and is just here for completeness.
	     * You would just use the task you want to unit test in your project. 
	     */
	    final AsyncTask<Void, Void, Void> myTask = new AsyncTask<Void, Void, Void>() {

	        @Override
	        protected Void doInBackground(Void... params) {
				return null;
	        }

	        @Override
	        protected void onPostExecute(Void param) {
	            super.onPostExecute(param);

	            signal.countDown();
	        }
	    };
	    
	    /*
	    // Execute the async task on the UI thread! THIS IS KEY!
	    runTestOnUiThread(new Runnable() {

	        @Override
	        public void run() {
	            myTask.execute();                
	        }
	    });       
   
	    signal.await(30, TimeUnit.SECONDS);
	    
	    if(testGPS.getResponseBody() == "outside")
	    {
	       assertTrue(this.solo.waitForText("Toast"));
	       assertTrue(testGPS.getEmailAlert());
	    }
	    else if(testGPS.getResponseBody() == "inside")
	    	assertFalse(testGPS.getEmailAlert());
	    */
	}
}
