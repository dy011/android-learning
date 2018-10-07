package com.example.service1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Service1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service1);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				startService();
			}

			
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				stopService();
			}
		});
	}

	protected void stopService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Service1.class);
		stopService(intent);
	}
	
	protected void startService() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, Service1.class);
		startService(intent);
	}

}
