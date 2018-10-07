package com.karnect.bluetoothuds;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	//private int mState;
	//private final int mStateConnected = 0;
	//private final int mStateDisconnect = 0;
	//private boolean mIsNormalClosed = false;
	//private Message mMsg = new Message();
	private OnPortListener mOnPortListener;
	private ListView mLv;
	private BluetoothAdapter mBluetoothAdapter;
	private DeviceAdapter mAdapter;
	private OutputStream mOutputStream;
	private ArrayList<BluetoothDevice> mDevices = new ArrayList<BluetoothDevice>();
	private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(BluetoothDevice.ACTION_FOUND.equals(action)){
				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				mDevices.add(device);
				mAdapter.notifyDataSetChanged();
				System.out.println(device.getName()); 
			}else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
				Toast.makeText(getApplicationContext(), "开始扫描", Toast.LENGTH_SHORT).show();
			}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
				Toast.makeText(getApplicationContext(), "扫描结束", Toast.LENGTH_SHORT).show();
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLv = (ListView) findViewById(R.id.lv);
		mAdapter = new DeviceAdapter(getApplicationContext(), mDevices);
		mLv.setAdapter(mAdapter);
		mLv.setOnItemClickListener(this);
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		//注册广播接收者，当扫描到蓝牙设备时，系统会发送广播
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED );
		registerReceiver(mBluetoothReceiver, filter);
	}

	public void clickBtn(View v){
		switch (v.getId()) {
		case R.id.button1:
			//蓝牙是否可用
			if(!mBluetoothAdapter.isEnabled()){
				//打开蓝牙
				mBluetoothAdapter.enable();
			}
			break;

		case R.id.button2:
			//关闭蓝牙
			if(mBluetoothAdapter.isEnabled()){
				//关闭蓝牙
				mBluetoothAdapter.disable(); 
			}
			
			break;
			
		case R.id.button3:
			//开始扫描
			mDevices.clear();
			mAdapter.notifyDataSetChanged();
			mBluetoothAdapter.startDiscovery();		
					break;
					
		case R.id.button4:
			//停止扫描
			mBluetoothAdapter.cancelDiscovery();
			break;
			
		case R.id.button5:
			sendCtr(0);
			break;
			
		case R.id.button6:
			sendCtr(1);
			break;
			
		case R.id.button7:
			sendCtr(2);		
			break;
			
		default:
			break;
		}
	}
	
	private void sendCtr(int i)
	{
		try {
			byte[] bs = new byte[5];
			bs[0] = (byte)0x01;
			bs[1] = (byte)0x99;
			
			if(i==0){
				bs[2] = (byte)0x01;
				bs[3] = (byte)0x99;
 			}
			else if(i==1){
				bs[2] = (byte)0x10;
				bs[3] = (byte)0x10;
 			}else if(i==1){
				bs[2] = (byte)0x11;
				bs[3] = (byte)0x11;
 			}else if(i==3){
				bs[2] = (byte)0x17;
				bs[3] = (byte)0x17;
 			}
			
			bs[4] = (byte)0x99;
			mOutputStream.write(bs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mBluetoothReceiver);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		BluetoothDevice device = mDevices.get(position);
		conn(device);
		
	}

	private void conn(final BluetoothDevice device) {
		// 建立蓝牙连接是耗时操作，类似TCP Socket，需要放在子线程里面
		new Thread(){
			public void run(){
				try {
					// 获取BluetoothSocket，UUID需要和蓝牙服务端保持一致
					BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID
							.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					
					// 和蓝牙服务端建立连接
					bluetoothSocket.connect();
					
					// 获取输出流，往蓝牙服务端写指令信息
				    mOutputStream =	bluetoothSocket.getOutputStream();
				    
				    if(mOutputStream != null){
				    	System.out.println(mOutputStream.toString());
				    }
				    //提示用户
				    runOnUiThread(new Runnable(){
				    	@Override
				    	public void run() {
				    		System.out.println("连接成功-----");
				    		Toast.makeText(getApplicationContext(), 
				    				"连接成功", Toast.LENGTH_SHORT).show();
				    	}
				    });
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
	}
}
