package main.java.com.agileninjas.dementiasmartwatch;

import android.content.Context;
import android.test.AndroidTestCase;


import java.io.*;  

import main.java.com.agileninjas.dementiasmartwatch.BatteryCheck;
import main.java.com.agileninjas.dementiasmartwatch.R2;

public class BatteryCheckTest extends AndroidTestCase{
	
	private BatteryCheck mBatteryCheck;
	
	@Override
    protected void setUp() throws Exception {
		
		super.setUp();
		
		mBatteryCheck = new BatteryCheck();		
	}
	
//	private void openScript() throws IOException
//	{
//		String [] cmd = {"open", "/Users/mana/Desktop/sc.command"};
//		Runtime.getRuntime().exec(cmd);
//	}
	
	//set power capacity to 100
	public void testBatteryLevelNormalCases1() {
		mBatteryCheck.getBatterLevel(getContext());
		assertFalse(mBatteryCheck.getBatteryLow());
	}
	
	//set power capacity to 10
	public void testBatteryLevelNormalCases2() throws IOException{
		//script.openScript();
		mBatteryCheck.getBatterLevel(getContext());
		assertTrue(mBatteryCheck.getBatteryLow());
	}
	
	//set power capacity to 20
	public void testBatteryLevelBoundaryCases() {
		mBatteryCheck.getBatterLevel(getContext());
		assertTrue(mBatteryCheck.getBatteryLow());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
}
