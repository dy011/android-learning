package com.karnect.businesschat;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class BusinessBluetooth {
	private static final String sTag = "BusinessBluetooth";
	private static final String sName = "BluetoothChat";
	private static final UUID sUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private BluetoothAdapter mBluetoothAdapter;
	private Context mContext;
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private PortListenTread mPortListenThread;
	private int mState;
	private int mStateConnected = 0;
	private int mStateDisconnect = 1;
	private boolean mIsNormalClosed = false;
	private Message mMessage = new Message();
	private OnPortListener mOnPortListener;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what){
			case 1:
				
			}
		}
	};
	
	public interface OnPortListener{
		public abstract void OnReceiveData(String pMessage);
	}
	
	public BusinessBluetooth(Context pContext){
		mContext = pContext;
		mMessage.what = 1;
		mOnPortListener = (OnPortListener) pContext;
	}
	
	public void CreatePortListen(){
		//获取一个本机蓝牙设备
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		//判断是否不存在蓝牙设备或没有打开
		if(mBluetoothAdapter == null || mBluetoothAdapter.isEnabled()){
			BluetoothServerSocket mBluetoothServerSocket = mBluetoothAdapter
					.listenUsingRfcommWithServiceRecord(name, uuid)
					
		}
		
	}
}
















