package com.example.service3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Service3Activity extends Activity {

	private Intent intent = new Intent();
	private Service3.mBinder binder;
	public ServiceConnection sConnection = new ServiceConnection(){
		public void onServiceDisconnected(ComponentName name){
			System.out.println("--ServiceDisconnected--");
			binder = null;
		}
		
		public void onServiceConnected(ComponentName name, IBinder service){
			System.out.println("--ServiceConnected--");
			binder = (Service3.mBinder)service;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service3);
		intent.setAction("android.service");
		
		Button btn_start_service = (Button) findViewById(R.id.btn_start_service);
		btn_start_service.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				bindService(intent, sConnection, BIND_AUTO_CREATE);
			}
		});
		
		Button btn_stop_service = (Button) findViewById(R.id.btn_stop_service);
		btn_stop_service.setOnClickListener(new OnClickListener(){
		
			public void onClick(View v){
				unbindService(sConnection);
			}
		});
		
		Button btn_get_data = (Button) findViewById(R.id.btn_get_data);
		btn_get_data.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Toast.makeText(Service3Activity.this, 
						"ServiceµÄcounterÖµÎª" + binder.getCounter(), 
						Toast.LENGTH_LONG).show();
			}
		});
	}

	
}
