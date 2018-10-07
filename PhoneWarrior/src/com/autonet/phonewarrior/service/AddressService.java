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
	 * ���������
	 */
	private WindowManager wm;
	private View view;
	
	/**
	 * ��������
	 */
	private TelephonyManager tm;
	private MyListenerPhone listenerPhone;
	private OutCallReceiver receiver;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//����������ڲ���
	//�㲥�����ߵ��������ںͷ���һ��
	class OutCallReceiver extends BroadcastReceiver{

		@Override 
		public void onReceive(Context context, Intent intent) {
			// ����������õ��Ĳ���ȥ�ĵ绰����
			String phone = getResultData();
			//��ѯ���ݿ�
			String address = NumberAddressQueryUtils.queryNumber(phone);
			//Toast.makeText(context, address, 1).show();
			myToast(address);
		}
	}
	
	private class MyListenerPhone extends PhoneStateListener{

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// ��һ��������״̬���ڶ��������ǵ绰����
			super.onCallStateChanged(state, incomingNumber);
			
			switch (state) {
			case TelephonyManager.CALL_STATE_RINGING:
				//�绰���������ʱ����ʵҲ�������ʱ��
				//���ݵõ��ĵ绰���룬��ѯ���Ĺ����أ�����ʾ����˾����
				String address = NumberAddressQueryUtils.queryNumber(incomingNumber);
				//Toast.makeText(getApplicationContext(), address, 1).show();
				myToast(address);
				
				break;
				
			case TelephonyManager.CALL_STATE_IDLE://�绰�Ŀ���״̬���ҵ绰������ܾ�
				//��view�Ƴ�
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
		//��������
		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		tm.listen(listenerPhone, PhoneStateListener.LISTEN_CALL_STATE);
		
		//�ô���ȥע��㲥������
		receiver = new OutCallReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.intent.action.NEW_OUTGOING_CALL");
		registerReceiver(receiver, filter);
		
		//ʵ��������
		wm = (WindowManager) getSystemService(WINDOW_SERVICE);
	}

	/**
	 * �Զ�����˾
	 * @param address
	 */
	public void myToast(String address) {
		view = View.inflate(this, R.layout.address_show, null);
		TextView textview = (TextView) view.findViewById(R.id.tv_address);
		
		//"��͸��", "������", "��ʿ��", "������", "ƻ����"
		int[] ids = {R.drawable.call_locate_white, R.drawable.call_locate_orange,
				R.drawable.call_locate_blue, R.drawable.call_locate_gray,
				R.drawable.call_locate_green};
		SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
		view.setBackgroundResource(ids[sp.getInt("which", 0)]);
		textview.setText(address);
		
		
		//����Ĳ��������ú���
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
		//ȡ����������
		tm.listen(listenerPhone, PhoneStateListener.LISTEN_NONE);
		listenerPhone = null;
		
		//�ô���ȡ��ע��㲥������ 
		unregisterReceiver(receiver);
		receiver = null;
		
	}
}
