package com.example.buttondemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn_first,btn_second;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_first = (Button) findViewById(R.id.btn_first);
		btn_second = (Button) findViewById(R.id.btn_second);
		
		btn_first.setEnabled(false);
		
		btn_second.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(btn_second.getText().toString().equals("按钮不可用")){
					btn_second.setText("按钮可用");
					btn_first.setEnabled(true);
				}else{
					btn_second.setText("按钮不可用");
					btn_first.setEnabled(false);
				}	
			}
		});
		
		btn_first.setOnClickListener(new OnClickListener() {
			
			private int count = 0;
			
			@Override
			public void onClick(View v) {
				if(count == 0){
						btn_first.setText("哈哈*^-^*");
						count = 1;
					}else{
						btn_first.setText("嘻嘻(*^ww^*)");
						count  = 0;
					}
				}
		});
	}
	
}
