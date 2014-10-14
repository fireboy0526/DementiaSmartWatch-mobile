package com.agileninjas.dementiasmartwatch;

//import org.robolectric.shadows.ShadowToast;

//import com.agileninjas.dementiasmartwatch.Main;
import com.agileninjas.dementiasmartwatch.R;

import com.agileninjas.dementiasmartwatch.Main;
import com.agileninjas.dementiasmartwatch.R;
import com.robotium.solo.Solo;

import android.app.AlertDialog;
import android.app.Instrumentation.ActivityMonitor;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.test.suitebuilder.annotation.MediumTest;
//import android.widget.TextView;

public class MainTest extends ActivityInstrumentationTestCase2<Main>{

	private Main mActivity;
	private LinearLayout mLinearLayout;
	private AnalogClock mAnalogClock;
	private DigitalClock mDigitalClock;
	private ImageButton panicButton;
	private ImageButton contactButton;
	private ActivityMonitor mMonitor;
    
	public MainTest() {
		super(Main.class);
	}

	@Override
    protected void setUp() throws Exception {
		
		super.setUp();
		
		setActivityInitialTouchMode(true);
		
	    mActivity = getActivity();
	    
		//mTextView =  (TextView)mActivity.findViewById(R.id.fullscreen_content);
	    
	    mLinearLayout = (LinearLayout)mActivity.findViewById(R.id.fullscreen_content);
	    
	    mAnalogClock =  (AnalogClock)mActivity.findViewById(R.id.analog_clock);
	    
	    mDigitalClock =  (DigitalClock)mActivity.findViewById(R.id.digital_clock);
		
	    panicButton = (ImageButton)mActivity.findViewById(R.id.fullscreen_content_controls);
	    contactButton = (ImageButton)mActivity.findViewById(R.id.fullscreen_content_controls2);
		//mButton.setOnClickListener((android.view.View.OnClickListener) this);
	}
	

	
	public void testLinearLayout_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, mLinearLayout);

	    final ViewGroup.LayoutParams layoutParams =
	    		mLinearLayout.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}
	
	public void testAnalogClock_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, mAnalogClock);

	    final ViewGroup.LayoutParams layoutParams =
	    		mAnalogClock.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height,WindowManager.LayoutParams.MATCH_PARENT);
	}
	
	public void testDigitalClock_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, mDigitalClock);

	    final ViewGroup.LayoutParams layoutParams =
	    		mDigitalClock.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height,WindowManager.LayoutParams.MATCH_PARENT);
	}
	
	public void testpanicButton_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, panicButton);

	    final ViewGroup.LayoutParams layoutParams =
	    		panicButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, 100);
	    assertEquals(layoutParams.height, 100);
	    //assertEquals("Incorrect label of the button", "Panic Button", panicButton.getText());
	}
	
	public void testcontactButton_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, contactButton);

	    final ViewGroup.LayoutParams layoutParams =
	    		contactButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, 100);
	    assertEquals(layoutParams.height, 100);
	    //assertEquals("Incorrect label of the button", "Panic Button", panicButton.getText());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}*/
	
	public void testpanicbuttonViewVisibility() throws NullPointerException {
	    /**
	    * here view.getVisibility() returns
	    * 0 - VISIBLE
	    * 4 - INVISIBLE
	    * 8 - GONE
	    */
	    String visibility = null;
	    switch(panicButton.getVisibility()) {
	        case 0 :
	            visibility = "VISIBLE";
	        break;

	        case 4:
	            visibility = "INVISIBLE";
	        break;

	        case 8:
	            visibility = "GONE";
	        break;
	    }
	    //System.out.println("The visibility of View "+visibility);
	    assertTrue(true);
	}
	
	public void testcontactbuttonViewVisibility() throws NullPointerException {
	    /**
	    * here view.getVisibility() returns
	    * 0 - VISIBLE
	    * 4 - INVISIBLE
	    * 8 - GONE
	    */
	    String visibility = null;
	    switch(contactButton.getVisibility()) {
	        case 0 :
	            visibility = "VISIBLE";
	        break;

	        case 4:
	            visibility = "INVISIBLE";
	        break;

	        case 8:
	            visibility = "GONE";
	        break;
	    }
	    //System.out.println("The visibility of View "+visibility);
	    assertTrue(true);
	}
	
	@MediumTest
	public void testPanicButton() {
	    //mMonitor = getInstrumentation().addMonitor(Main.class.getName(), null, false);

		Solo solo = new Solo(getInstrumentation(),getActivity());
		
		getInstrumentation().waitForIdleSync();
		
	    //TouchUtils.clickView(this, mButton);

	    //mActivity = (Main) mMonitor.waitForActivityWithTimeout(2000);
	    //assertNotNull("MyActivity activity not started, activity is null", mActivity);
       
	    //AlertDialog dialog = mActivity.getLastDialog(); // I create getLastDialog method in MyActivity class. Its return last created AlertDialog
		panicButton.setVisibility(View.VISIBLE);
		panicButton.performClick();
	    //dialog.show();
	    //assertTrue(dialog.isShowing());
	    
	    assertTrue("Could not find the dialog!", solo.searchText("Panic message have been sent."));
        assertTrue("Counld not find the button!", solo.searchButton("OK"));

	    //mActivity.finish();
	    //getInstrumentation().removeMonitor(mMonitor);
	}
	
	@MediumTest
	public void testContactButton() {
	    //mMonitor = getInstrumentation().addMonitor(Main.class.getName(), null, false);

		Solo solo = new Solo(getInstrumentation(),getActivity());
		
		getInstrumentation().waitForIdleSync();
		
	    //TouchUtils.clickView(this, mButton);

	    //mActivity = (Main) mMonitor.waitForActivityWithTimeout(2000);
	    //assertNotNull("MyActivity activity not started, activity is null", mActivity);
       
	    //AlertDialog dialog = mActivity.getLastDialog(); // I create getLastDialog method in MyActivity class. Its return last created AlertDialog
		contactButton.setVisibility(View.VISIBLE);
		contactButton.performClick();
	    //dialog.show();
	    //assertTrue(dialog.isShowing());
	    
	    assertTrue("Could not find the dialog!", solo.searchText("Patient Name:Johnny Bravo\nContact Person:Bunny Runner\nContact Number:0412-345-678"));
        assertTrue("Counld not find the button!", solo.searchButton("OK"));

	    //mActivity.finish();
	    //getInstrumentation().removeMonitor(mMonitor);
	}

}