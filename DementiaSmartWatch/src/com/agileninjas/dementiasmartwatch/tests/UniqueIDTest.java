package com.agileninjas.dementiasmartwatch.tests;

import com.agileninjas.dementiasmartwatch.UniqueID;

import android.test.AndroidTestCase;

public class UniqueIDTest extends AndroidTestCase{

	
	public void testGetUniqueID() {
		assertEquals(UniqueID.getUniqueID(), "1234");
	}
}
