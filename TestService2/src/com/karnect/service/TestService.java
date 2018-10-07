package com.karnect.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

	private final String TAG = "TestService2";
	
	public TestService() {
		// TODO Auto-generated constructor stub
	}

	//必须要实现的方法
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind方法被调用！");
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate方法被调用！");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand方法被调用！");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy方法被调用！");
		super.onDestroy();
	}

}
