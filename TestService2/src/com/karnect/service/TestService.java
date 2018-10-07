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

	//����Ҫʵ�ֵķ���
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind���������ã�");
		return null;
	}
	
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate���������ã�");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand���������ã�");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy���������ã�");
		super.onDestroy();
	}

}
