package com.example;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Lab1Activity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.table_layout);

		Bundle extras = getIntent().getExtras();
		final int position = extras.getInt("position");
		Resources res = getResources();
		String[] horo_names = res.getStringArray(R.array.horo_array);
		String[] horo_descriptions = res
				.getStringArray(R.array.horo_description_array);

		TextView titleView = (TextView) findViewById(R.id.title);
		TextView descriptionView = (TextView) findViewById(R.id.description);
		ImageView imageView = (ImageView) findViewById(R.id.image);
		titleView.setText(horo_names[position]);
		descriptionView.setText(horo_descriptions[position]);
		imageView.setImageResource(ImageMapper.findBig(position));

		final Button next = (Button) findViewById(R.id.next);
		next.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(),
						Lab1Activity.class);
				int newPosition = (position >= 3) ? 0 : position + 1;
				intent.putExtra("position", newPosition);
				startActivity(intent);
			}
		});

		final Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(getApplicationContext(),
						Lab1Activity.class);
				int newPosition = (position <= 0) ? 3 : position - 1;
				intent.putExtra("position", newPosition);
				startActivity(intent);
			}
		});
	}
}