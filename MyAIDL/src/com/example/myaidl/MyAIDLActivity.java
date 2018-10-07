package com.example.myaidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.KeyEvent;


public class MyAIDLActivity extends Activity {

	private MyAIDL myAIDL;
	private Intent intent;
	private ServiceConnection sConnection = new ServiceConnection(){
		public void onServiceDisconnected(ComponentName name){
			
		}
		
		public void onServiceConnected(ComponentName name, IBinder service){
			myAIDL = MyAIDL.Stub.asInterface(service);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myaidl);
		intent = new Intent(getApplicationContext(), MyAIDLService.class);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			bindService(intent, sConnection, BIND_AUTO_CREATE);
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
			unbindService(sConnection);
		}else if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
			try {
				System.out.println("ƒ˙—°‘Ò¡À£∫" + myAIDL.getValue());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}

	
}
