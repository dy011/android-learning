package com.example.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyAIDLService extends Service {

	private String[] values = {
			"java",
			"c/c++",
			"Android"
	};
	
	private int index = 0;
	private boolean bRunning = true;
	
    class mBinder extends MyAIDL.Stub{
		public String getValue() throws RemoteException {
			return values[index];
		}
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new mBinder();
	}
	
	public void onCreate(){
		super.onCreate();
		new Thread(new Runnable(){
			public void run(){
				while(bRunning == true){
					index = (int)(Math.random()*2);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();;
	}
	
	public boolean onUnbind(Intent intent){
		return super.onUnbind(intent);
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
}
