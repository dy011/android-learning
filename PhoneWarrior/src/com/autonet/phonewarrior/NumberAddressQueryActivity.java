package com.autonet.phonewarrior;

import com.autonet.phonewarrior.db.dao.NumberAddressQueryUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NumberAddressQueryActivity extends Activity {
	
	private static final String TAG = "NumberAddressQueryActivity";
	private EditText et_phone;
	private TextView result;
	
	/**
	 * 系统提供的振动服务
	 */
	private Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_address_query);
		 et_phone = (EditText) findViewById(R.id.et_phone);
		 result = (TextView) findViewById(R.id.result);
		 vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		 et_phone.addTextChangedListener(new TextWatcher() {
			
			 /**
			  * 当文本发生变化的时候回调
			  */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s != null && s.length() >= 3){
					String address  = NumberAddressQueryUtils.queryNumber(s.toString());
					result.setText(address);
				}
				
			}
			
			/**
			 * 当文本发生变化之前回调 
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * 当文本发生变化之前回调
			 */
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	} 
	
	/**
	 * 查询号码归属地
	 * @param view
	 */
	public void numberAddressQuery(View view){
		String phone = et_phone.getText().toString().trim();
		if(TextUtils.isEmpty(phone)){
			Toast.makeText(this, "号码为空", 0).show();
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			et_phone.startAnimation(shake);
			
		 	//当电话号码为空的时候，就去振动手机提醒用户
			//vibrator.vibrate(2000);
			long[] pattern = {200, 200, 300, 300, 1000, 3000};
			//-1不重复 0循环振动 1
			vibrator.vibrate(pattern, -1);
			
			return;
		}else{
			String address = NumberAddressQueryUtils.queryNumber(phone);
			result.setText(address);
			//去数据库查询号码归属地
			//1.网络查询; 2.本地数据库--数据库
			//写一个工具类，去查询数据库
			Log.i(TAG, "您要查询的电话号码=="+phone);
		}
	}
}
