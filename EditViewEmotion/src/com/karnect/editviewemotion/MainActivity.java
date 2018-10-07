package com.karnect.editviewemotion;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button btn_add;
	private EditText et_first;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_add = (Button) findViewById(R.id.btn_add);
		et_first = (EditText) findViewById(R.id.et_first);
		
		btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SpannableString spanStr = new SpannableString("imge");
				
				Drawable dr = MainActivity.this.getResources().getDrawable(R.drawable.pai);
				dr.setBounds(0,0,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
				ImageSpan span = new ImageSpan(dr,ImageSpan.ALIGN_BASELINE);
				spanStr.setSpan(span, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				int cursor = et_first.getSelectionStart();
				et_first.getText().insert(cursor, spanStr);
			
			}
		});
	}
}
