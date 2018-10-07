package com.karnect.textviewspan;

import java.net.URL;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv_one = (TextView) findViewById(R.id.txtOne);
		//TextView tv_two = (TextView) findViewById(R.id.txtTwo);
		
		Spannable span = new SpannableString("红色打电话斜体删除斜线绿色下划线图片:");
		
		//1.设置背景色,setSpan时需要指定的flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)
		span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
		 //2.用超链接标记文本
		span.setSpan(new URLSpan("tel:4155551212"), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//3.用样式标记文本（斜体）
		span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//4.用删除线标记文本
		span.setSpan(new StrikethroughSpan(), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//5.用下划线标记文本
		span.setSpan(new UnderlineSpan(), 10, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//6.用颜色标记
		span.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 13, span.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		span.setSpan(new AbsoluteSizeSpan(20,true), 5, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//7.获取Drawable资源
		Drawable dr = getResources().getDrawable(R.drawable.icon);
		dr.setBounds(50,50,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
		
		//8.创建ImageSpan,然后用ImageSpan来替换文本
		ImageSpan imagspan = new ImageSpan(dr, ImageSpan.ALIGN_BASELINE);
		span.setSpan(imagspan, 18, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		
		tv_one.setText(span);
	}
}
