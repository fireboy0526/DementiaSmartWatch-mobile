package main.java.com.agileninjas.dementiasmartwatch;

import main.java.com.agileninjas.dementiasmartwatch.UniqueID;

import android.test.AndroidTestCase;

import com.agileninjas.dementiasmartwatch.R2;

public class UniqueIDTest extends AndroidTestCase{

	
	public void testGetUniqueID() {
		assertEquals(UniqueID.getUniqueID(), "1234");
	}
}
