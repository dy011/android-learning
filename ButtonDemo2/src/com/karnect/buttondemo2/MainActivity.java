package com.karnect.buttondemo2;

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
		
		btn_second.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 if(btn_second.getText().toString().equals("按钮不可用")){
					 btn_first.setEnabled(false);
	                  btn_second.setText("按钮可用");
                }else{
                	btn_first.setEnabled(true);
                    btn_second.setText("按钮不可用");
                }
			}
		});
	}
}
