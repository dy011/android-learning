package com.autonet.phonewarrior.receiver;

import com.autonet.phonewarrior.R;
import com.autonet.phonewarrior.service.GPSService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	private static final String TAG = "SMSReceiver";
	private SharedPreferences sp;
	@Override
	public void onReceive(Context context, Intent intent) {
		//д���ն��ŵĴ���
		Object[] objs = (Object[]) intent.getExtras().get("pdus");
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		
		for(Object b:objs){
			//�����ĳһ������
			SmsMessage sms = SmsMessage.createFromPdu((byte[])b);
			//������
			String sender = sms.getOriginatingAddress();//155999995556
			String safenumber = sp.getString("safenumber", "");//5556
			
			//5556
			//155999995556
			Toast.makeText(context, sender, 1).show();
			Log.i(TAG, "===sender=="+sender);
			
			String body = sms.getMessageBody();
			
			if(sender.contains(safenumber)){
				if("#*location*#".equals(body)){
					//�õ��ֻ���GPS
					Log.i(TAG, "�õ��ֻ���GPS");
					//��������
					Intent i = new Intent(context, GPSService.class);
					context.startService(i);
					SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
					String lastlocation = sp.getString("lastlocation", null);
					if(TextUtils.isEmpty(lastlocation)){
						//λ��û�еõ�
						SmsManager.getDefault().sendTextMessage(sender, null, "getting location....", 
								null, null);
					}else{
						SmsManager.getDefault().sendTextMessage(sender, null, lastlocation, 
								null, null);
					}
						
					
					//������㲥��ֹ��
					abortBroadcast();
				}else if("#*alarm*#".equals(body)){
					//���ű�������
					Log.i(TAG, "���ű������� ");
					MediaPlayer player = MediaPlayer.create(context, R.raw.ylzs);
					player.setLooping(false);//
					player.setVolume(1.0f, 1.0f);
					player.start();
					abortBroadcast();
				}else if("#*wipedata*#".equals(body)){
					//Զ���������
					Log.i(TAG, "Զ��������� ");
					abortBroadcast();
				}else if("#*lockscreen*#".equals(body)){
					//Զ������
					Log.i(TAG, "Զ������ ");
					abortBroadcast();
				}
			}
		}
	}

}
