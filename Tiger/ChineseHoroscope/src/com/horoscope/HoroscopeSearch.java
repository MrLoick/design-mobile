package com.horoscope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

/**
 * @author kamasheto
 */
public class HoroscopeSearch extends Activity {
	String[] horoscopes;

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.search);
		horoscopes = getResources().getStringArray(R.array.horoscope_array);

		/*
		 * Link autocomplete textbox to list of horoscopes
		 */
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_horoscope);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view, horoscopes);
		textView.setThreshold(1);
		textView.setAdapter(adapter);
	}

	/**
	 * What to do when clicking ok? Search.
	 * 
	 * @param view
	 */
	public void onOkClick(View view) {
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_horoscope);
		//
		int position = 0;
		if (textView.getText() == null) {
			showMessage("Please enter a search query.");
			return;
		}

		String query = textView.getText().toString();
		boolean found = false;
		for (String horoscope : horoscopes) {
			if (horoscope.toLowerCase().contains(query.toLowerCase())) {
				found = true;
				query = horoscope;
				break;
			}
			position++;
		}

		if (!found) {
			showMessage("No matches found.");
			return;
		}

		Intent i = new Intent(getBaseContext(), HoroscopeDetails.class);
		i.putExtra("pos", position);
		i.putExtra("title", query);
		startActivity(i);
	}

	public void showMessage(String text) {
		Toast.makeText(HoroscopeSearch.this, text, Toast.LENGTH_SHORT).show();
	}
}
