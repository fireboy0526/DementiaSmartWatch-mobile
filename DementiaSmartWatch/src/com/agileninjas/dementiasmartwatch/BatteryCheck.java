package com.agileninjas.dementiasmartwatch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryCheck {
	private boolean batteryLow = false;
	
	public boolean getBatteryLow()
	{
		return batteryLow;
	}
	
	public void getBatterLevel(Context context) {
		BroadcastReceiver batteryLevelReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				//Unregister receiver
				//context.unregisterReceiver(this);
				
				//Get current battery level
				int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
				
				//Get total battery level (max level)
				int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
				
				int level = -1;
				
				//get the current battery percentage
				if (currentLevel >= 0 && scale > 0) {
					level = (currentLevel * 100) / scale;
				}
				
				//check to see if battery is lower than 20% then pop alert, sound and email
				if (level > 20) {
					batteryLow = false;
					//Log.d("BroadcastNotifier", "BatteryLow: false");
				} else if(level <= 20 && batteryLow == false){
				    batteryLow = true;
				    //Log.e("BraodcastNotifier", "GOT LOW BATTERY WARNING");
				    
				    //Send data
					final HttpClient httpclient = new DefaultHttpClient();
					final HttpPost httppost = new HttpPost("http://hungpohuang.com/agile/include/mail.php");
					
					//set data
					String to = "agileninjas2014@gmail.com";
					String subject = "Patient watch low battery";
					String message = "Your patient battery is low, please recharged as soon as possible";
					
					//Adding data
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
					nameValuePairs.add(new BasicNameValuePair("to", to));
					nameValuePairs.add(new BasicNameValuePair("subject", subject));
					nameValuePairs.add(new BasicNameValuePair("message", message));

					//Encoding data
					try {
						httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					} catch (UnsupportedEncodingException e) {
			            // log exception
			            e.printStackTrace();
			        }
					
					//making Post Request
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								ResponseHandler<String> responseHandler=new BasicResponseHandler();
						        String responseBody = httpclient.execute(httppost, responseHandler);
								Log.d("Response of POST: ", responseBody);
							} catch (ClientProtocolException e) {
							    e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					};
					new Thread(runnable).start();
				    
				    //Plays default notification sound
				    try {
				    	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				    	Ringtone r = RingtoneManager.getRingtone(context, notification);
				    	r.play();
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
				    
				    //Create alert dialog with OK button only
				    AlertDialog.Builder builder = new AlertDialog.Builder(context);
				    builder.setMessage("Device power low. Please recharge as soon as possible")
				    		.setCancelable(false)
				    		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				    			public void onClick(DialogInterface dialog, int id) {
				    				//To-Do
				    			}
				    		});
				    AlertDialog alert = builder.create();
				    alert.show();
		        }
			}
		};
		IntentFilter batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		context.registerReceiver(batteryLevelReceiver, batteryLevelFilter);
	}
}
