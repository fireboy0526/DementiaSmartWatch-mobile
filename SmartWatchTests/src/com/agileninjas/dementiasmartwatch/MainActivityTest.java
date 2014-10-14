package com.agileninjas.dementiasmartwatch;

import com.robotium.solo.Solo;

import com.agileninjas.dementiasmartwatch.Main;
import com.agileninjas.dementiasmartwatch.R;

import android.content.Intent;
import android.content.Context;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

public class MainActivityTest extends ActivityUnitTestCase<Main>{

    private Intent mStartIntent;
    private Button mButton;
    
	public MainActivityTest() {
		super(Main.class);
	}
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // In setUp, you can create any shared test data, or set up mock components to inject
        // into your Activity.  But do not call startActivity() until the actual test methods.
        mStartIntent = new Intent(Intent.ACTION_MAIN);
    }
    
    @MediumTest
    public void testPreconditions() {
        startActivity(mStartIntent, null, null);
        mButton = (Button) getActivity().findViewById(R.id.fullscreen_content_controls);
        
        assertNotNull(getActivity());
        assertNotNull(mButton);
    }
    
    /**
     * This test demonstrates examining the way that activity calls startActivity() to launch 
     * other activities.
     */
    @MediumTest
    public void testSubLaunch() {
        Main activity = startActivity(mStartIntent, null, null);
        mButton = (Button) activity.findViewById(R.id.fullscreen_content_controls);
        Solo solo = new Solo(getInstrumentation(),getActivity());
        // This test confirms that when you click the button, the activity attempts to open
        // another activity (by calling startActivity) and close itself (by calling finish()).
        mButton.performClick();
        assertTrue("Could not find the dialog!", solo.searchText("Panic message have been sent."));
        assertNotNull(getStartedActivityIntent());
        assertTrue(isFinishCalled());
    }    
    
    /**
     * This test demonstrates ways to exercise the Activity's life cycle.
     */
    @MediumTest
    public void testLifeCycleCreate() {
        Main activity = startActivity(mStartIntent, null, null);
        
        // At this point, onCreate() has been called, but nothing else
        // Complete the startup of the activity
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);
        
        // At this point you could test for various configuration aspects, or you could 
        // use a Mock Context to confirm that your activity has made certain calls to the system
        // and set itself up properly.
        
        getInstrumentation().callActivityOnPause(activity);
        
        // At this point you could confirm that the activity has paused properly, as if it is
        // no longer the topmost activity on screen.
        
        getInstrumentation().callActivityOnStop(activity);
        
        // At this point, you could confirm that the activity has shut itself down appropriately,
        // or you could use a Mock Context to confirm that your activity has released any system
        // resources it should no longer be holding.
    }

}
