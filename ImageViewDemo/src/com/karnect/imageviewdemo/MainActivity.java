package com.karnect.imageviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	//private ImageView btn_pen;
	//private LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(48, 48);
		//layout = (LinearLayout) findViewById(R.layout.activity_main);
		setContentView(R.layout.activity_main);
		//btn_pen = (ImageView) findViewById(R.id.btn_pen);
		//layout.addView(btn_pen, layoutParam); //动态view不会，没搞定
		
	}
}
