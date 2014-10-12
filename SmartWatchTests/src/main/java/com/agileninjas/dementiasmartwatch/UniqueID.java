package main.java.com.agileninjas.dementiasmartwatch;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import com.agileninjas.dementiasmartwatch.R2;


public class UniqueID {
	private static String uniqueID;
	
	public void setUniqueID() {
		/*WifiManager m_wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		if (m_wm != null) {
			uniqueID = m_wm.getConnectionInfo().getMacAddress();
			System.out.println("UniqueID: " + uniqueID.toString());
		}*/
		/*BluetoothAdapter m_ba = BluetoothAdapter.getDefaultAdapter();
		if (m_ba != null) {
			this.uniqueID = m_ba.getAddress();
			System.out.println("UniqueID: " + uniqueID.toString());
		}*/
	}
	
	public static String getUniqueID() {
		return uniqueID = "1234";
	}
}
