package com.karnect.textviewhtml;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_html;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_html = (TextView) findViewById(R.id.tv_html);
		
		String str = "<font color= 'blue'><b>百度一下，您就知道:</b></font><br>";
		
		str += "<a href='https://www.baidu.com'>百度</a>";
		
		//tv_html.setText(str);
		tv_html.setText(Html.fromHtml(str));
		tv_html.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
