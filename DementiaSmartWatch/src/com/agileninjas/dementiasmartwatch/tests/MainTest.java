package com.agileninjas.dementiasmartwatch.tests;

//import org.robolectric.shadows.ShadowToast;

import com.agileninjas.dementiasmartwatch.Main;
import com.agileninjas.dementiasmartwatch.R;

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
import android.widget.LinearLayout;
import android.test.suitebuilder.annotation.MediumTest;
//import android.widget.TextView;

public class MainTest extends ActivityInstrumentationTestCase2<Main> implements OnClickListener{

	private Main mActivity;
	private LinearLayout mLinearLayout;
	private AnalogClock mAnalogClock;
	private DigitalClock mDigitalClock;
	private Button mButton;
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
		
		mButton = (Button)mActivity.findViewById(R.id.fullscreen_content_controls);
		mButton.setOnClickListener((android.view.View.OnClickListener) this);
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

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		
	}
	
	public void testViewVisibility() throws NullPointerException {
	    /**
	    * here view.getVisibility() returns
	    * 0 - VISIBLE
	    * 4 - INVISIBLE
	    * 8 - GONE
	    */
	    String visibility = null;
	    switch(mButton.getVisibility()) {
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
	
	//@MediumTest
	/*public void testStartMyActivity() {
	    mMonitor = getInstrumentation().addMonitor(Main.class.getName(), null, false);

	    TouchUtils.clickView(this, mButton);

	    Main myActivity = (Main) mMonitor.waitForActivityWithTimeout(2000);
	    assertNotNull("MyActivity activity not started, activity is null", myActivity);

	    AlertDialog dialog = myActivity.getLastDialog(); // I create getLastDialog method in MyActivity class. Its return last created AlertDialog
	    if (dialog.isShowing()) {
	        try {
	            performClick(dialog.getButton(DialogInterface.BUTTON_POSITIVE));
	        } catch (Throwable e) {
	            e.printStackTrace();
	        }
	    }

	    myActivity.finish();
	    getInstrumentation().removeMonitor(mMonitor);
	}*/

	private void performClick(final Button button) throws Throwable {
	    runTestOnUiThread(new Runnable() {
	        @Override
	        public void run() {
	            button.performClick();
	        }
	    });
	    getInstrumentation().waitForIdleSync();
	}
}