package com.karnect.framelayoutmove;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FrameLayout frame = (FrameLayout)findViewById(R.id.mylayout);
		final GirlView girl = new GirlView(MainActivity.this);
		
		girl.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				girl.bitmapX = event.getX() - 150;
				girl.bitmapY = event.getY() - 150;
				
				girl.invalidate();
				return true;
			}
		});
		
		frame.addView(girl);
	}
}
