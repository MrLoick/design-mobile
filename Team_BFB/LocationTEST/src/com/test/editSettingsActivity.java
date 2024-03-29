package com.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class editSettingsActivity extends Activity {

	Spinner spinner;
	String serviceType = "skyhook";
	int minDist = 50;
	int freq = 10000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		spinner = (Spinner) findViewById(R.id.typeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.typeArray, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

	}

	public void saveSettings(View view) {
		minDist = Integer.parseInt(((EditText) findViewById(R.id.minDist))
				.getText().toString());
		freq = Integer.parseInt(((EditText) findViewById(R.id.freq)).getText()
				.toString());
		Intent i = new Intent(getBaseContext(), Location.class);
		i.putExtra("minDist", minDist);
		i.putExtra("freq", freq);
		i.putExtra("type", serviceType);
		startActivity(i);
	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			serviceType = parent.getItemAtPosition(pos).toString();
			// Toast.makeText(parent.getContext(), "The selected type is " +
			// parent.getItemAtPosition(pos).toString(),
			// Toast.LENGTH_LONG).show();
		}

		public void onNothingSelected(AdapterView<?> parent) {
			// Do nothing.
		}
	}

}
