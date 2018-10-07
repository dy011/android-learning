package com.autonet.phonewarrior.service;

import com.autonet.phonewarrior.R;
import com.autonet.phonewarrior.db.dao.NumberAddressQueryUtils;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.autonet.phonewarrior.R;
import com.autonet.phonewarrior.db.dao.NumberAddressQueryUtils;


public class AddressService extends Service {

	/**
	 * 窗体管理者
	 */
	private WindowManager wm;
	private View view;
	
	/**
	 * 监听来电
	 */
	private TelephonyManager tm;
	private MyListenerPhone listenerPhone;
	private OutCallReceiver receiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//服务里面的内部类
	//广播接收者的生命周期和服务一样
	class OutCallReceiver extends BroadcastReceiver{

		@Override 
		public void onReceive(Context context, Intent intent) {
			// 这就是我们拿到的拨出去的电话号码
			String phone = getResultData();
			//查询数据库
			String address = NumberAddressQueryUtils.queryNumber(phone);
			//Toast.makeText(context, address, 1).show();
			myToast(address);
		}
	}
	
	private class MyListenerPhone extends PhoneStateListener{

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// 第一个参数是状态，第二个参数是电话号码
			super.onCallStateChanged(state, incomingNumber);
			
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				//电话铃声响起的时候，其实也是来电的时候
				//根据得到的电话号码，查询它的归属地，并显示在吐司里面
				String address = NumberAddressQueryUtils.queryNumber(incomingNumber);
				//Toast.makeText(getApplicationContext(), address, 1).show();
				myToast(address);
				
				break;
				
			case TelephonyManager.CALL_STATE_IDLE://电话的空闲状态：挂电话、来电拒绝
				//把view移除
				if(view != null){
					wm.removeView(view);
				}
				break;

			default:
				break;
			}
		}
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		//监听来电
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(listenerPhone, PhoneStateListener.LISTEN_CALL_STATE);
		
		//用代码去注册广播接收者
		receiver = new OutCallReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
		registerReceiver(receiver, filter);
		
		//实例化窗体
		wm = (WindowManager) getSystemService(WINDOW_SERVICE);
	}

	/**
	 * 自定义吐司
	 * @param address
	 */
	public void myToast(String address) {
		view = View.inflate(this, R.layout.address_show, null);
		TextView textview = (TextView) view.findViewById(R.id.tv_address);
		
		//"半透明", "活力橙", "卫士蓝", "金属灰", "苹果绿"
		int[] ids = {R.drawable.call_locate_white, R.drawable.call_locate_orange,
				R.drawable.call_locate_blue, R.drawable.call_locate_gray,
				R.drawable.call_locate_green};
		SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
		view.setBackgroundResource(ids[sp.getInt("which", 0)]);
		textview.setText(address);
		
		
		//窗体的参数就设置好了
		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
				|WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				|WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.format = PixelFormat.TRANSLUCENT;
		params.type = WindowManager.LayoutParams.TYPE_TOAST;
		params.setTitle("Toast");
		
		wm.addView(view, params);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//取消监听来电
		tm.listen(listenerPhone, PhoneStateListener.LISTEN_NONE);
		listenerPhone = null;
		
		//用代码取消注册广播接收者 
		unregisterReceiver(receiver);
		receiver = null;
		
	}
}
