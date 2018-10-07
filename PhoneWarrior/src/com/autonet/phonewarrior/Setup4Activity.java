package com.autonet.phonewarrior;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class Setup4Activity extends BaseSetupActivity {
	
	private SharedPreferences sp;
	private CheckBox cb_protected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup4);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		cb_protected = (CheckBox) findViewById(R.id.cb_protected);
		
		boolean isProtected = sp.getBoolean("protected", false);
		if(isProtected){
			//手机防盗已经开启了
			cb_protected.setText("手机防盗已经开启了");
			cb_protected.setChecked(true);
		}else{
			//手机防盗没有开启
			cb_protected.setText("手机防盗没有开启");
			cb_protected.setChecked(false);
		}
		
		cb_protected.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					cb_protected.setText("手机防盗已经开启了");
				}else{
					cb_protected.setText("手机防盗没有开启了");
				}
				
				//保存选择的状态
				Editor editor = sp.edit();
				editor.putBoolean("protected", isChecked);
				editor.commit();		
			}
		});
	}

	@Override
	public void showNext() {
		Editor editor = sp.edit();
		editor.putBoolean("configed", true);
		editor.commit();
		
		Intent intent = new Intent(this, LostFindActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
		
	}

	@Override
	public void showPre() {
		Intent intent = new Intent(this, Setup3Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
	}

}
