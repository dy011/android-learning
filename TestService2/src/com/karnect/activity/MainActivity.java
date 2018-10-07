package com.karnect.activity;

import com.karnect.service.TestService;
import com.karnect.testservice2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_start;
	private Button btn_stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_start = (Button) findViewById(R.id.btnStart);
		btn_stop  = (Button) findViewById(R.id.btnStop);
		
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startService();
				
			}
		});
		
		btn_stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopService();
				
			}
		});
	}
	
	
	
	protected void stopService() {
		Intent intent = new Intent(this, TestService.class);
		stopService(intent);
		
	}



	protected void startService() {
		Intent intent = new Intent(this, TestService.class);
		startService(intent);
				
			}
	 
}
