package com.example.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class HelloAndroid extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //TextView tv = new TextView(this);
        //tv.setText("Hello, Android");
        //setContentView(tv);
        //tv.setText("Hello, my Chinese name is <Dong Hui dan> and my Chinese horoscope is <Horse>");
        //setContentView(tv);
    }
 
}
