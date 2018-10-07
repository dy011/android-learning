package com.karnect.textviewagree;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv_one = (TextView) findViewById(R.id.tv_one);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 20; i++) {
			sb.append("好友"  + i + ",");
		}
		
		String likeUsers = sb.substring(0, sb.lastIndexOf(",")).toString();
		tv_one.setMovementMethod(LinkMovementMethod.getInstance());
		tv_one.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);
	}

	//定义一个点击每个部分文字的处理方法
	private SpannableStringBuilder addClickPart(String str) {
		//赞的图标，这里没有素材，就找个笑脸代替下~
		
		ImageSpan imgspan = new ImageSpan(MainActivity.this, R.drawable.ic_widget_face);
		
		SpannableString spanStr = new SpannableString("p.");
		spanStr.setSpan(imgspan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		
		//创建一个SpannableStringBuilder对象，连接多个字符串
		SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
		ssb.append(str);
		
		String[] likeUsers = str.split(",");
		
		if(likeUsers.length > 0){
			for (int i = 0; i < likeUsers.length; i++) {
				final String name = likeUsers[i];
				final int start = str.indexOf(name) + spanStr.length();
				ssb.setSpan(new ClickableSpan(){

					@Override
					public void onClick(View widget) {
						Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();	
					}
					
					@Override
					public void updateDrawState(TextPaint ds) {
						// TODO Auto-generated method stub
						super.updateDrawState(ds);
						
						//删除下划线，设置字体颜色为蓝色
						ds.setColor(Color.BLUE);
						ds.setUnderlineText(false);
					}
					
				}, start, start + name.length(), 0);
			}
		}	
		
		return ssb.append("等" + likeUsers.length + "个人觉得很赞");
	}
}
