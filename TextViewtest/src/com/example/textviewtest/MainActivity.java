package com.example.textviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//MarqueeTextView tv = (MarqueeTextView) findViewById(R.id.tv);
		//tv.setSelected(true);
		
		TextView tv_one = (TextView) findViewById(R.id.tv_One);
		tv_one.setSelected(true);
		
	}
}
