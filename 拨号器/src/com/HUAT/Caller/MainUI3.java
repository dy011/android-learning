package com.HUAT.Caller;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;

/*
 * @ author:listenanr
 * ��������о���ʾ�Ľ���
 */
public class MainUI3 extends ActionBarActivity {

	/*
	 * ������ִ��ʱ�ص��÷���
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	//����ִ�иô��룬ִ�и���ĳ�ʼ������
		
		setContentView(R.layout.main);	//���õ�ǰ������ʾ�Ĳ���
		
		Button btnCall = (Button) findViewById(R.id.btn_call);
		
		btnCall.setOnClickListener(new MyOnClickListener());
	}
	
	class MyOnClickListener implements OnClickListener{
		
		@Override
		public void onClick(View v){
			// TODO Auto-generated method stub
			System.out.println("MainUI3������롣��");
			call();
		}
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main_ui, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	/*
	 *����绰��ҵ�񷽷�
	 * @param v
	 */
	private void call(){
		System.out.println("����绰.");
		
		//1.ȡ��������еĺ���
		EditText etNumber = (EditText) findViewById(R.id.number);	//��������
		String number = etNumber.getText().toString();
		
		//2.���ݵ绰���벦��绰
		Intent intent =  new Intent();	//����һ����ͼ
		intent.setAction(Intent.ACTION_CALL);	//ָ���䶯��Ϊ��绰
		intent.setData(Uri.parse("tel:" + number));	//ָ��Ҫ�����ĺ���	
		startActivity(intent);	//ִ���������
	}
}
















