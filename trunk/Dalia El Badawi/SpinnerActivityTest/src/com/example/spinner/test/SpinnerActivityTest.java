/**
 * 
Author: Dalia El Badawi
Created: 04/10/2011
 *
**/
package com.example.spinner.test;

import com.example.spinner.HelloSpinnerActivity;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.KeyEvent;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class SpinnerActivityTest extends		
        ActivityInstrumentationTestCase2<HelloSpinnerActivity> {
	
	private HelloSpinnerActivity mActivity;
	private Spinner mSpinner;
	private SpinnerAdapter mPlanetData;
	
	private String mSelection;
	private int mPos;
	
	public static final int ADAPTER_COUNT = 8;
	public static final int INITIAL_POSITION = 1;
	public static final int TEST_POSITION = 5;
	public static final int TEST_STATE_DESTROY_POSITION = 0;
	public static final String TEST_STATE_DESTROY_SELECTION = "Mercury";
	public static final int TEST_STATE_PAUSE_POSITION = 4;
	public static final String TEST_STATE_PAUSE_SELECTION = "Jupiter";

	 	public SpinnerActivityTest() {
		    super("com.example.spinner", HelloSpinnerActivity.class);
		  } // end of SpinnerActivityTest constructor definition
	 	
	 	 @Override
	 	  protected void setUp() throws Exception {
	 	    super.setUp();

	 	    setActivityInitialTouchMode(false);

	 	    mActivity = getActivity();

	 	    mSpinner =
	 	      (Spinner) mActivity.findViewById(com.example.spinner.R.id.spinner);

	 	      mPlanetData = mSpinner.getAdapter();

	 	  } // end of setUp() method definition
	 	 
	 	 public void testPreConditions() {
	 	    assertTrue(mSpinner.getOnItemSelectedListener() != null);
	 	    assertTrue(mPlanetData != null);
	 	    assertEquals(mPlanetData.getCount(),ADAPTER_COUNT);
	 	  } // end of testPreConditions() method definition
	 	 
	 	public void testSpinnerUI() {

	 	    mActivity.runOnUiThread(
	 	      new Runnable() {
	 	        public void run() {
	 	          mSpinner.requestFocus();
	 	          mSpinner.setSelection(INITIAL_POSITION);
	 	        } // end of run() method definition
	 	      } // end of anonymous Runnable object instantiation
	 	    ); // end of invocation of runOnUiThread
	 	   
	 	    //select spinner
	 	    this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	 	    for (int i = 1; i <= TEST_POSITION; i++) {
	 	      //scroll down	
	 	      this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
	 	    } // end of for loop
	 	    //enter/select
	 	    this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);
	 	    
	 	   mPos = mSpinner.getSelectedItemPosition();
	 	    mSelection = "The planet is " +(String)mSpinner.getItemAtPosition(mPos);
	 	    TextView resultView =
	 	      (TextView) mActivity.findViewById(
	 	        com.example.spinner.R.id.SpinnerResult
	 	      );

	 	    String resultText = (String) resultView.getText();

	 	    assertEquals(resultText,mSelection);

	 	  } // end of testSpinnerUI() method definition
	 	
	 	
	 	public void testStateDestroy() {
	 	    mActivity.setSpinnerPosition(TEST_STATE_DESTROY_POSITION);
	 	    mActivity.setSpinnerSelection(TEST_STATE_DESTROY_SELECTION);
	 	    mActivity.finish();
	 	    mActivity = this.getActivity();
	 	    int currentPosition = mActivity.getSpinnerPosition();
	 	    String currentSelection = mActivity.getSpinnerSelection();
	 	    assertEquals(TEST_STATE_DESTROY_POSITION, currentPosition);
	 	    assertEquals(TEST_STATE_DESTROY_SELECTION, currentSelection);
	 	  } // end of testStateDestroy() method definition
	 	
	 	@UiThreadTest
	    public void testStatePause() {
	 		Instrumentation mInstr = this.getInstrumentation();
	 		mActivity.setSpinnerPosition(TEST_STATE_PAUSE_POSITION);
	 	    mActivity.setSpinnerSelection(TEST_STATE_PAUSE_SELECTION);
	 	    mInstr.callActivityOnPause(mActivity);
	 	    
	 	    mActivity.setSpinnerPosition(0);
	 	    mActivity.setSpinnerSelection("");
	 	    
	 	    mInstr.callActivityOnResume(mActivity);
	 	    
	 	    int currentPosition = mActivity.getSpinnerPosition();
	 	    String currentSelection = mActivity.getSpinnerSelection();
	 	    assertEquals(TEST_STATE_PAUSE_POSITION,currentPosition);
	 	    assertEquals(TEST_STATE_PAUSE_SELECTION,currentSelection);
	 	}// end of testStatePause() method definition
}