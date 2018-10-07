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
	 * ϵͳ�ṩ���񶯷���
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
			  * ���ı������仯��ʱ��ص�
			  */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s != null && s.length() >= 3){
					String address  = NumberAddressQueryUtils.queryNumber(s.toString());
					result.setText(address);
				}
				
			}
			
			/**
			 * ���ı������仯֮ǰ�ص� 
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * ���ı������仯֮ǰ�ص�
			 */
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	} 
	
	/**
	 * ��ѯ���������
	 * @param view
	 */
	public void numberAddressQuery(View view){
		String phone = et_phone.getText().toString().trim();
		if(TextUtils.isEmpty(phone)){
			Toast.makeText(this, "����Ϊ��", 0).show();
			Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
			et_phone.startAnimation(shake);
			
		 	//���绰����Ϊ�յ�ʱ�򣬾�ȥ���ֻ������û�
			//vibrator.vibrate(2000);
			long[] pattern = {200, 200, 300, 300, 1000, 3000};
			//-1���ظ� 0ѭ���� 1
			vibrator.vibrate(pattern, -1);
			
			return;
		}else{
			String address = NumberAddressQueryUtils.queryNumber(phone);
			result.setText(address);
			//ȥ���ݿ��ѯ���������
			//1.�����ѯ; 2.�������ݿ�--���ݿ�
			//дһ�������࣬ȥ��ѯ���ݿ�
			Log.i(TAG, "��Ҫ��ѯ�ĵ绰����=="+phone);
		}
	}
}
