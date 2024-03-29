package com.example.helloandroid.test;

import com.example.helloandroid.HelloAndroidActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class HelloAndroidTest extends ActivityInstrumentationTestCase2<HelloAndroidActivity> {
    private HelloAndroidActivity mActivity;  // the activity under test
    private TextView mView;          // the activity's TextView (the only view)
    private String resourceString;

    public HelloAndroidTest() {
      super("com.example.helloandroid", HelloAndroidActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mView = (TextView) mActivity.findViewById(com.example.helloandroid.R.id.textview);
        resourceString = mActivity.getString(com.example.helloandroid.R.string.chinese_name);
    }
    public void testPreconditions() {
      assertNotNull(mView);
    }
    public void testText() {
      assertEquals(resourceString,(String)mView.getText());
    }
}