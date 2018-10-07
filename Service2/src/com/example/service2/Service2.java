package com.example.service2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Service2 extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("---onBind---");
		return null;
	}
	
	public void onCreate(){
		System.out.println("---onCreate---");
		super.onCreate();
	}
	
	public boolean onUnbind(Intent intent){
		System.out.println("---onUnBind---");
		return super.onUnbind(intent);
	}
	
	public void onDestroy(){
		System.out.println("---onDestroy---");
		super.onDestroy();
	}

	
}
