package com.example.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
public class FirstActivity extends BaseActivity{
	
	//static int count = 0;
	//static String str = null;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		///Log.d("FirstActivity", this.toString());
		Log.d("FirstActivity", "Task id is " + getTaskId());
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		
		Button button1 = (Button) findViewById(R.id.button_1);
		button1.setOnClickListener(new OnClickListener(){
			
			@Override
			/*public void onClick(View v){
				count++;
				str = "You clicked Button " +String.valueOf(count)+" times";
				Toast.makeText(FirstActivity.this, str, Toast.LENGTH_SHORT).show();
				
				if(count == 1)
				{
					String data = "Hello SecondActivity";
					Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
					intent.putExtra("extra_data", data);
					startActivity(intent);
				}
				
				if(count == 2)
				{
					Intent intent1 = new Intent("com.example.activitytest.ACTION_START");
					intent1.addCategory("com.example.activitytest.MY_CATEGORY");
					startActivity(intent1);
				}
				
				
				if(count == 3)
				{
					Intent intent2 = new Intent(Intent.ACTION_VIEW);
					intent2.setData(Uri.parse("http://www.baidu.com"));
					startActivity(intent2);
				}
				
				if(count == 4)
				{
					Intent intent3 = new Intent(Intent.ACTION_DIAL);
					intent3.setData(Uri.parse("tel:10086"));
					//startActivity(intent1); 为什么其他的intent就有问题
					startActivity(intent3);
				}
				
				if(count == 6)
				{
					count = 0;
					finish();
				}
			}*/
			
			/*public void onClick(View v){
				String data = "Hello SecondActivity";
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				intent.putExtra("extra_data", data);
				startActivity(intent);
			}*/
			
			/*public void onClick(View v){
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				startActivityForResult(intent, 1);
			}*/
			
			public void onClick(View v){
				Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
				startActivity(intent);
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
		case 1:
			if(resultCode == RESULT_OK){
				String returnedData = data.getStringExtra("data_return");
				Log.d("FirstActivity", returnedData);
			}
			break;
		default:
		}
	}
	
	
	
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main, menu);
		return true;	
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.add_item:
			Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
			break;
		case R.id.remove_item:
			Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
			break;
		default:
		}
		
		return true;
	}

	protected void onRestart(){
		super.onRestart();
		Log.d("FirstActivity", "onRestart");
	}
}





















