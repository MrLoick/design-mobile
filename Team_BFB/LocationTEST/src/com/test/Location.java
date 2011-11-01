package com.test;

import com.skyhookwireless.wps.*;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Location extends Activity {
	Button btnStopService;
	TextView txtMsg;
	Intent intentMyService;
	Intent Skyhook;
	ComponentName service;
	BroadcastReceiver receiver;
	String GPS_FILTER = "guc.action.GPS_LOCATION";
	private Handler _handler;
    private static final int LOCATION_MESSAGE = 1;
    private static final int ERROR_MESSAGE = 2;
    private static final int DONE_MESSAGE = 3;
   
    private final MyLocationCallback _callback = new MyLocationCallback();
    boolean skyhook = true;

//	@Override
//	protected void onDestroy() {
//		try {
//			stopService(intentMyService);
//			unregisterReceiver(receiver);
//		} catch (Exception e) {
//			Log.e("MAIN-DESTROY>>>", e.getMessage());
//		}
//		Log.e("MAIN-DESTROY>>>", "Adios");
//	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		
		
		//setContentView(R.layout.main);
		if(skyhook){
		setUIHandler();	
		WPS wps = new WPS(this);
	    WPSAuthentication auth = new WPSAuthentication("salma_7amed", "German university in cairo");
	     wps.getIPLocation(auth,WPSStreetAddressLookup.WPS_NO_STREET_ADDRESS_LOOKUP,_callback);
	//   wps.getLocation(auth,WPSStreetAddressLookup.WPS_NO_STREET_ADDRESS_LOOKUP,_callback);
	     
	     }
		else{
		// initiate the service
		intentMyService = new Intent(this, MyGpsService.class);
		service = startService(intentMyService);
		txtMsg.setText("MyGpsService started - (see DDMS Log)");
		// register & define filter for local listener
		IntentFilter mainFilter = new IntentFilter(GPS_FILTER);
		receiver = new MyMainLocalReceiver();
		registerReceiver(receiver, mainFilter);
		btnStopService = (Button) findViewById(R.id.btnStopService);
		btnStopService.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					stopService(new Intent(intentMyService));
					txtMsg.setText("After stoping Service: \n"
							+ service.getClassName());
					btnStopService.setText("Finished");
					btnStopService.setClickable(false);
				} catch (Exception e) {
					Log.e("MYGPS", e.getMessage());
				}
			}
		});
		}
	}

	 private void setUIHandler()
    {
        _handler = new Handler()
        {
            @Override
            public void handleMessage(final Message msg)
            {
                switch (msg.what)
                {
                case LOCATION_MESSAGE:
                 //   final Location location = (Location) msg.obj;
                    txtMsg.setText( ((Location) msg.obj).toString());
                    Log.e("location msg","ay kalam");
                    return;
                case ERROR_MESSAGE:
                	txtMsg.setText(((WPSReturnCode) msg.obj).name());
                	Log.e("error msg",((WPSReturnCode) msg.obj).name());
                    return;
                case DONE_MESSAGE:
                	Log.e("end","false");
                    skyhook = false;
                }
            }
        };
    }

	
	 private class MyLocationCallback implements IPLocationCallback,WPSLocationCallback{
		 
     public void done()
     {
         // tell the UI thread to re-enable the buttons
    	 Log.e("location msg","done");
         _handler.sendMessage(_handler.obtainMessage(DONE_MESSAGE));
     }

     public WPSContinuation handleError(final WPSReturnCode error)
     {
    	 Log.e("location msg","error");
         // send a message to display the error
    	 
         _handler.sendMessage(_handler.obtainMessage(ERROR_MESSAGE, error));
         // return WPS_STOP if the user pressed the Stop button
             return WPSContinuation.WPS_STOP;
     }

     public void handleIPLocation(final IPLocation location)
     {
    	 Log.e("location msg","handle");
         // send a message to display the location
    	 txtMsg.setText(location.toString());
    	  Log.e("yarab",location.toString());
//         _handler.sendMessage(_handler.obtainMessage(LOCATION_MESSAGE, location));
     }

     public void handleWPSLocation(final WPSLocation location)
     {
         // send a message to display the location
         _handler.sendMessage(_handler.obtainMessage(LOCATION_MESSAGE, location));
     }
 }


	class MyMainLocalReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context localContext, Intent callerIntent) {
			double latitude = callerIntent.getDoubleExtra("latitude", -1);
			double longitude = callerIntent.getDoubleExtra("longitude", -1);
			Log.e("MAIN>>>", Double.toString(latitude));
			Log.e("MAIN>>>", Double.toString(longitude));
			String msg = " lat: " + Double.toString(latitude) + " " + " lon: "
					+ Double.toString(longitude);
			txtMsg.append("\n" + msg);
			// testing the SMS-texting feature
			texting(msg);
		}

		private void texting(String msg) {
			try {
				SmsManager smsMgr = SmsManager.getDefault();
				// Parameter of sendTextMessage are:
				// destinationAddress, senderAddress,
				// text, sentIntent, deliveryIntent)
				// ----------------------------------------------------------
				smsMgr.sendTextMessage("5556", "5551234", "Please meet me at: "
						+ msg, null, null);
			} catch (Exception e) {
				Toast.makeText(getBaseContext(), "texting\n" + e.getMessage(),
						1).show();
			}
		}// texting

	}
}