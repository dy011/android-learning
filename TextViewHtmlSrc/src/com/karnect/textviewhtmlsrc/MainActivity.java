package com.karnect.textviewhtmlsrc;

import java.lang.reflect.Field;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tv_src;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv_src = (TextView) findViewById(R.id.tv_src);
		
		String srcImg = "图片:<a href='https://www.zhihu.com'><img src='icon'/></a><br>";
		//srcImg += "<a href='https:zhihu.com'></a>"
		tv_src.setText(Html.fromHtml(srcImg, new ImageGetter() {
			
			@Override
			public Drawable getDrawable(String source) {
				
				Drawable draw = null;
				
				try {
					Field field = R.drawable.class.getField(source);
					int resId = Integer.parseInt(field.get(null).toString());
					draw = getResources().getDrawable(resId);
					draw.setBounds(0,0,draw.getIntrinsicWidth(),draw.getIntrinsicHeight());
					tv_src.setMovementMethod(LinkMovementMethod.getInstance());//点击跳转不了啊
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 				
				
				return draw;
			}
		}, null));
		
	}
}
