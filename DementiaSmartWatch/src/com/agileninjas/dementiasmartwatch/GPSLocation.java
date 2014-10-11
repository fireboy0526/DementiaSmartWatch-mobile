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
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.agileninjas.dementiasmartwatch.R;

public class GPSLocation implements LocationListener {
	private static LocationManager locationManager;
	private static String provider;
	private static Criteria criteria;
	private double gLatitude, gLongitude, oldLon = 0.0, oldLat = 0.0;
	private boolean locationChanged;
	private boolean emailAlert = false;
	private boolean errorCode = false;
	private int errorCodeNum = 0;
	private String responseBody;
	private Context gContext;
	//Declaring a class level context for future use
	/*
	public static void runGPS(final Context context) {
		final GPSLocation gps = new GPSLocation();
		//UniqueID.setUniqueID(context);
		gps.start(context);
		
	}
	*/
	
	public boolean checkSignal(Context context) {
		locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void start(final Context context) {
		
		this.gContext = context;
		
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
        		onLocationChanged(location);
        	}

        } else {
        	Toast.makeText(context, context.getResources().getString(R.string.gps_no_provider), Toast.LENGTH_SHORT).show();
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
	
	public boolean getLocationChanged()
	{
		return locationChanged;
	}
	
	public boolean getEmailAlert()
	{
		return emailAlert;
	}
	
	public String getResponseBody()
	{
		return responseBody;
	}
	
	public void setErrorCode(boolean errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setErrorCodeNum(int errorCodeNum) {
		this.errorCodeNum = errorCodeNum;
	}
	
	//Run this when location changes
	public void onLocationChanged(Location location) {
		locationChanged = false;
		gLongitude = (double)(location.getLongitude());
		gLatitude = (double)(location.getLatitude());
		
		/*if (oldLon != 0.0 && oldLat != 0.0) {
			
		}*/
		Log.e ("Distance", String.valueOf(distance(oldLat, oldLon, gLatitude, gLongitude)));
		
		if (oldLon != gLongitude) {
			oldLon = gLongitude;
			locationChanged = true;
		}
		
		if (oldLat != gLatitude) {
			oldLat = gLatitude;
			locationChanged = true;
		}
		
		if (locationChanged == true) {
			new asyncTask().execute();
		}
		
	}
	
	private double deg2rad(double rad) {
		return (rad * 180 / Math.PI);
	}

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = deg2rad(dist);
		  dist = dist * 60 * 1.1515;
		  dist = dist * 1.609344;
		  return (dist);
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

	public class asyncTask extends AsyncTask<Void, Void, Void> {
		
		
		//String responseBody;
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
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
				nameValuePairs.add(new BasicNameValuePair("user_id", UniqueID.getUniqueID()));
				nameValuePairs.add(new BasicNameValuePair("lat", String.valueOf(gLatitude)));
				nameValuePairs.add(new BasicNameValuePair("lon", String.valueOf(gLongitude)));
				nameValuePairs.add(new BasicNameValuePair("date", String.valueOf(date)));
				
				//Encoding data
				try {
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				} catch (UnsupportedEncodingException e) {
		            // log exception
		            e.printStackTrace();
		            Log.e("GPSLocation Encoding Error:", e.getMessage());
		        }
				
				//Connect to database and get response
				try {
					ResponseHandler<String> responseHandler = new BasicResponseHandler();
			        responseBody = httpclient.execute(httppost, responseHandler);
					Log.d("Response of POST: ", responseBody);
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				    Log.e("GPSLocation ClientProtocol Error: ", e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("GPSLocation IOException Error: ", e.getMessage());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("GPSLocation AsyncTask Error: ", e.getMessage());
			}
			return null;
		}
		
		//After AsyncTask finished running
		protected void onPostExecute(Void param) {
			if (responseBody.toString().equals("outside")) {
				//Toast message to tell them to stand still
				Toast.makeText(Main.mainContext.getApplicationContext(), gContext.getResources().getString(R.string.gps_out_of_boundry), Toast.LENGTH_SHORT).show();
				
				//Play sound to warn them
				//Plays default notification sound
			    try {
			    	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			    	Ringtone r = RingtoneManager.getRingtone(Main.mainContext.getApplicationContext(), notification);
			    	r.play();
			    } catch (Exception e) {
			    	e.printStackTrace();
			    	Log.e("GPSLocation onPostExecute Error: ", e.getMessage());
			    }
			    
			    if (emailAlert == false) {
			    	emailAlert = true;
			    	//Sending email to patient relative
				    EmailPost ep = new EmailPost();
				    ep.postEmail(gContext.getResources().getString(R.string.email_gps_out_of_boundry_subject), gContext.getResources().getString(R.string.email_gps_out_of_boundry_content));
			    }
			} else if (responseBody.toString().equals("inside")) {
				if (emailAlert == true) {
					emailAlert = false;
					//Sending email to patient relative
				    EmailPost ep = new EmailPost();
				    ep.postEmail(gContext.getResources().getString(R.string.email_gps_in_boundry_subject), gContext.getResources().getString(R.string.email_gps_in_boundry_content));
				}
			}
		}
	}
}
