package com.agileninjas.dementiasmartwatch.tests;

import com.agileninjas.dementiasmartwatch.Main;
import com.agileninjas.dementiasmartwatch.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.LinearLayout;
//import android.widget.TextView;

public class MainTest extends ActivityInstrumentationTestCase2<Main> {

	//private int ANALOG_CLOCK_WIDTH = 246;
	//private int ANALOG_CLOCK_HEIGHT = 244;
	//private int DIGITAL_CLOCK_HEIGHT = 56;
	//private TextView mTextView;
	private Main mActivity;
	private LinearLayout mLinearLayout;
	private AnalogClock mAnalogClock;
	private DigitalClock mDigitalClock;
	private Button mButton;
    
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
		
		mButton = (Button)mActivity.findViewById(R.id.fullscreen_content_controls);
	}
	
	
//	public void testPreconditions() {
//	    assertTrue(mTextView != null);
//	}
//
//	public void testText_layout() {
//	    final View decorView = mActivity.getWindow().getDecorView();
//
//	    ViewAsserts.assertOnScreen(decorView, mTextView);
//
//	    final ViewGroup.LayoutParams layoutParams =
//	            mTextView.getLayoutParams();
//	    assertNotNull(layoutParams);
//	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
//	    assertEquals(layoutParams.height, WindowManager.LayoutParams.MATCH_PARENT);
//	}
	
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
	
	public void testButton_layout() {
	    final View decorView = mActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, mButton);

	    final ViewGroup.LayoutParams layoutParams =
	            mButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals("Incorrect label of the button", "Panic Button", mButton.getText());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}