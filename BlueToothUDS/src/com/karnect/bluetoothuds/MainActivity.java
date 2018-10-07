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
				Toast.makeText(getApplicationContext(), "��ʼɨ��", Toast.LENGTH_SHORT).show();
			}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
				Toast.makeText(getApplicationContext(), "ɨ�����", Toast.LENGTH_SHORT).show();
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
		//ע��㲥�����ߣ���ɨ�赽�����豸ʱ��ϵͳ�ᷢ�͹㲥
		IntentFilter filter = new IntentFilter();
		filter.addAction(BluetoothDevice.ACTION_FOUND);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED );
		registerReceiver(mBluetoothReceiver, filter);
	}

	public void clickBtn(View v){
		switch (v.getId()) {
		case R.id.button1:
			//�����Ƿ����
			if(!mBluetoothAdapter.isEnabled()){
				//������
				mBluetoothAdapter.enable();
			}
			break;

		case R.id.button2:
			//�ر�����
			if(mBluetoothAdapter.isEnabled()){
				//�ر�����
				mBluetoothAdapter.disable(); 
			}
			
			break;
			
		case R.id.button3:
			//��ʼɨ��
			mDevices.clear();
			mAdapter.notifyDataSetChanged();
			mBluetoothAdapter.startDiscovery();		
					break;
					
		case R.id.button4:
			//ֹͣɨ��
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
		// �������������Ǻ�ʱ����������TCP Socket����Ҫ�������߳�����
		new Thread(){
			public void run(){
				try {
					// ��ȡBluetoothSocket��UUID��Ҫ����������˱���һ��
					BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID
							.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					
					// ����������˽�������
					bluetoothSocket.connect();
					
					// ��ȡ������������������дָ����Ϣ
				    mOutputStream =	bluetoothSocket.getOutputStream();
				    
				    if(mOutputStream != null){
				    	System.out.println(mOutputStream.toString());
				    }
				    //��ʾ�û�
				    runOnUiThread(new Runnable(){
				    	@Override
				    	public void run() {
				    		System.out.println("���ӳɹ�-----");
				    		Toast.makeText(getApplicationContext(), 
				    				"���ӳɹ�", Toast.LENGTH_SHORT).show();
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
