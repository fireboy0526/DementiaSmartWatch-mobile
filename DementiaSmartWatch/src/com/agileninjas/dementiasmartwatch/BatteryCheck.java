package com.agileninjas.dementiasmartwatch;

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
				    
				    //Sending email to patient relative
				    EmailPost ep = new EmailPost();
				    ep.postEmail("Patient watch low battery", "Your patient battery is low, please recharged as soon as possible.");
				    
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
