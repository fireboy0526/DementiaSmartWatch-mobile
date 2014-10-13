package main.java.com.agileninjas.dementiasmartwatch;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;

public class MedicineAlarm {
	public void SetAlarms(Context context){
		Intent openNewAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
		openNewAlarm.putExtra(AlarmClock.EXTRA_HOUR, 0);
		openNewAlarm.putExtra(AlarmClock.EXTRA_MINUTES, 20);
//		startActivity(openNewAlarm);
	}
}
