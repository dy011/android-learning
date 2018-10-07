package com.autonet.phonewarrior.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

	private SharedPreferences sp;
	private TelephonyManager tm;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		
		//读取之前保存的SIM信息；
		String saveSim = sp.getString("sim", "") + "bbb";
		
		//读取当前的SIM卡信息
		String realSim = tm.getSimSerialNumber();
		
		//比较是否一样
		if(saveSim.equals(realSim)){
			//sim没有变更，还是同一个哥们
		}else{
			//sim已经变更 发送一个短信给安全号码
			System.out.println("sim 已经变更");
			Toast.makeText(context, "sim 已经变更", 1).show();
		}
	}

}
