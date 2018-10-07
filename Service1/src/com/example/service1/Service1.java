package com.example.service1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Service1 extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
		System.out.println("onCreate()");
	}
	
	public int onStartCommand(Intent intent, int flags, int startId){
		System.out.println("onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy(){
		System.out.println("onDestroy");
		super.onDestroy();
	}
}
