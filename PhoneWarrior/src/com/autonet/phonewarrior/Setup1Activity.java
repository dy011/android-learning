package com.autonet.phonewarrior;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class Setup1Activity extends BaseSetupActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup1);
	}

	/**
	 * ��һ���ĵ���¼�
	 * @param view
	 */
	public void next(View view){
		showNext();
	}

	@Override
	public void showNext() {
		Intent intent = new Intent(this, Setup2Activity.class);
		startActivity(intent);
		finish();
		//Ҫ����finish()����startActivity(intent);����ִ�У�
		overridePendingTransition(R.anim.tran_in, R.anim.tran_out);
	}

	@Override
	public void showPre() {
		// TODO Auto-generated method stub
		
	}
	
	
}
