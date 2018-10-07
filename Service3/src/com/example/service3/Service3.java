package com.example.service3;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Service3 extends Service {

	private int counter = 0;
	private boolean bRunning = true;
	private mBinder binder = new mBinder();
	public class mBinder extends Binder{
		public int getCounter(){
			return counter;
		}
	}
	
	public IBinder onBind(Intent arg0){
		return binder;
	}
	
	public void onCreate(){
		super.onCreate();
		new Thread(new Runnable(){
			public void run(){
				while(bRunning == true){
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					counter++;
				}
			}
		}).start();
	}
	
	public boolean onUnbind(Intent intent){
		System.out.println("onUnbind");
		return true;
	}
	
	public void onDestroy(){
		super.onDestroy();
		bRunning = false;
		System.out.println("onDestroy");
	}
}
