package com.agileninjas.dementiasmartwatch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SendErrorCode implements LocationListener {
	
	private static LocationManager locationManager;
	private static String provider;
	private static Criteria criteria;
	private double gLatitude, gLongitude;
	private int errorCodeNum = 0;
	
	public void sendErrorCode (Context context, int errorCode) {
		
		//Getting LocationManager Object
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        
        //Creating an empty criteria object
        criteria = new Criteria();
        
        //Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);
        
        if(provider != null && !provider.equals("")) {
        	//Get the location from the given provider
        	Location location = locationManager.getLastKnownLocation(provider);
        	
        	//locationManager setting (GPS/Network Provider, time beore update, distance before update, this)
        	locationManager.requestLocationUpdates(provider, 1000, 5, this);
        	
        	if(location != null) {
        		gLongitude = (double)(location.getLongitude());
        		gLatitude = (double)(location.getLatitude());
        		errorCodeNum = errorCode;
        		new asyncTask().execute();
        	}

        } else {
        	Toast.makeText(context, "No Provider Found", Toast.LENGTH_SHORT).show();
        }
	}
	
	public class asyncTask extends AsyncTask<Void, Void, Void> {
		
		String responseBody;
		@Override
		protected Void doInBackground(Void... params) {
			try {
				//Get Date timestamp
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				String date = s.format(new Date());
				
				//Set Connection data
				final HttpClient httpclient = new DefaultHttpClient();//httpparams);
				final HttpPost httppost = new HttpPost("http://hungpohuang.com/agile/include/gpsrecord.php");
				
				//Adding data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
				nameValuePairs.add(new BasicNameValuePair("user_id", UniqueID.getUniqueID()));
				nameValuePairs.add(new BasicNameValuePair("lat", String.valueOf(gLatitude)));
				nameValuePairs.add(new BasicNameValuePair("lon", String.valueOf(gLongitude)));
				nameValuePairs.add(new BasicNameValuePair("date", String.valueOf(date)));
				nameValuePairs.add(new BasicNameValuePair("error_code", String.valueOf(errorCodeNum)));
				//Encoding data
				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
		            // log exception
		            e.printStackTrace();
		            Log.e("SendErrorCode Encoding Error:", e.getMessage());
		        }
				
				//Connect to database and get response
				try {
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
			        responseBody = httpclient.execute(httppost, responseHandler);
					Log.d("Response of POST: ", responseBody);
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				    Log.e("SendErrorCode ClientProtocol Error: ", e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("SendErrorCode IOException Error: ", e.getMessage());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("SendErrorCode AsyncTask Error: ", e.getMessage());
			}
			return null;
		}
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

}
