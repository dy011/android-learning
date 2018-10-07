package com.autonet.gpsdemo;



import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	//用到位置服务
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
		//注册监听位置服务
		//给位置提供者设置条件
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		
		//设置参数细化：
//		criteria.setAltitudeRequired(false);//不要求海拔信息
//		criteria.setBearingRequired(false);//不要钱方位信息
//		criteria.setCostAllowed(true);//是否允许付费
//		criteria.setPowerRequirement(Criteria.POWER_LOW);//对电量的要求
		String provider = lm.getBestProvider(criteria, true);
		lm.requestLocationUpdates(provider, 0, 0, listener);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//取消监听位置服务
		lm.removeUpdates(listener);
		listener = null;
	}

	class MyLocationListener implements LocationListener{

		/**
		 * 当位置改变的时候回调
		 */
		@Override
		public void onLocationChanged(Location location) {
			String longitude = "经度：" + location.getLongitude();
			String latitude = "纬度：" + location.getLatitude();
			String accuracy = "精确度：" + location.getAccuracy();
			TextView textview = new TextView(MainActivity.this);
			textview.setText(longitude+"\n" + latitude+ "\n" + accuracy);
			setContentView(textview);
		}

		/**
		 * 当状态发生改变的时候回调， 开启--关闭; 关闭--开启
		 */
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 某一个位置提供者可以使用了
		 */
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * 某一个位置提供者不可以使用了
		 */
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
	}
	
}
