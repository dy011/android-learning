package com.example.service2;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Service2Activity extends Activity {

	private Intent intent = new Intent();
	private ServiceConnection sConnection = new ServiceConnection(){
		public void onServiceDisconnected(ComponentName name){
			
		}
		
		public void onServiceConnected(ComponentName name, IBinder service){
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service2);
		intent.setAction("android.intent.action.start");
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				bindService(intent, sConnection, BIND_AUTO_CREATE);
			}
		});
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				unbindService(sConnection);
			}
		});
	}
	

	
}
