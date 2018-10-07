package com.karnect.textviewdemo;

import com.example.textviewdemo.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView txtWST;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//setContentView(R.layout.textviewdrawable);
		setContentView(R.layout.textviewdrawablexxx);
		
		txtWST = (TextView) findViewById(R.id.txtWST);
		Drawable[] drawables = txtWST.getCompoundDrawables();
		drawables[0].setBounds(0,50,200,200);
		drawables[1].setBounds(0,50,200,200);
		txtWST.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
	}
}
