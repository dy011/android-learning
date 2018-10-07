package com.autonet.gpsdemo;



import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	//�õ�λ�÷���
	private LocationManager lm;
	private MyLocationListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);

		
		/*List<String> provider = lm.getAllProviders();
		for(String l:provider){
			System.out.println(l);
		}*/
		
		listener = new MyLocationListener();
		//ע�����λ�÷���
		//��λ���ṩ����������
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		
		//���ò���ϸ����
//		criteria.setAltitudeRequired(false);//��Ҫ�󺣰���Ϣ
//		criteria.setBearingRequired(false);//��ҪǮ��λ��Ϣ
//		criteria.setCostAllowed(true);//�Ƿ�������
//		criteria.setPowerRequirement(Criteria.POWER_LOW);//�Ե�����Ҫ��
		String provider = lm.getBestProvider(criteria, true);
		lm.requestLocationUpdates(provider, 0, 0, listener);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//ȡ������λ�÷���
		lm.removeUpdates(listener);
		listener = null;
	}

	class MyLocationListener implements LocationListener{

		/**
		 * ��λ�øı��ʱ��ص�
		 */
		@Override
		public void onLocationChanged(Location location) {
			String longitude = "���ȣ�" + location.getLongitude();
			String latitude = "γ�ȣ�" + location.getLatitude();
			String accuracy = "��ȷ�ȣ�" + location.getAccuracy();
			TextView textview = new TextView(MainActivity.this);
			textview.setText(longitude+"\n" + latitude+ "\n" + accuracy);
			setContentView(textview);
		}

		/**
		 * ��״̬�����ı��ʱ��ص��� ����--�ر�; �ر�--����
		 */
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * ĳһ��λ���ṩ�߿���ʹ����
		 */
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * ĳһ��λ���ṩ�߲�����ʹ����
		 */
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
