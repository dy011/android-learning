package com.HUAT.smssender;

import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private EditText etNumber;
	private EditText etContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	 etNumber = (EditText)	findViewById(R.id.et_number);
	 etContent = (EditText) findViewById(R.id.et_content);
	
	Button button = (Button) findViewById(R.id.btn_send);
	button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//ºÅÂë
		String number = etNumber.getText().toString();
		//ÄÚÈÝ
		String content = etContent.getText().toString();
	
		SmsManager smsManager = SmsManager.getDefault();

		smsManager.sendTextMessage(
				number, 
				null, 
				content, 
				null, 
				null);
	}
}
