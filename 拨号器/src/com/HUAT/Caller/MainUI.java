package com.HUAT.Caller;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;

/*
 * @ author:listenanr
 * 程序刚运行就显示的界面
 */
public class MainUI extends ActionBarActivity {

	/*
	 * 当界面执行时回调该方法
	 * */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	//必须执行该代码，执行父类的初始化操作
		
		setContentView(R.layout.main);	//设置当前界面显示的布局
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
	 * 当拨打此号码的按钮被点击时触发此方法。
	 * @param v
	 */
	public void call(View v){
		System.out.println("拨打电话.");
		
		//1.取出输入框中的号码
		EditText etNumber = (EditText) findViewById(R.id.number);	//输入框对象
		String number = etNumber.getText().toString();
		
		//2.根据电话号码拨打电话
		Intent intent =  new Intent();	//创建一个意图
		intent.setAction(Intent.ACTION_CALL);	//指定其动作为打电话
		intent.setData(Uri.parse("tel:" + number));	//指定要拨出的号码	
		startActivity(intent);	//执行这个动作
		
	}
}
















