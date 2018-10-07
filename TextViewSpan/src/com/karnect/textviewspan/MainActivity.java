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
		
		Spannable span = new SpannableString("��ɫ��绰б��ɾ��б����ɫ�»���ͼƬ:");
		
		//1.���ñ���ɫ,setSpanʱ��Ҫָ����flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(ǰ�󶼲�����)
		span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	
		 //2.�ó����ӱ���ı�
		span.setSpan(new URLSpan("tel:4155551212"), 2, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//3.����ʽ����ı���б�壩
		span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//4.��ɾ���߱���ı�
		span.setSpan(new StrikethroughSpan(), 7, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//5.���»��߱���ı�
		span.setSpan(new UnderlineSpan(), 10, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//6.����ɫ���
		span.setSpan(new ForegroundColorSpan(Color.GREEN), 10, 13, span.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		span.setSpan(new AbsoluteSizeSpan(20,true), 5, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		//7.��ȡDrawable��Դ
		Drawable dr = getResources().getDrawable(R.drawable.icon);
		dr.setBounds(50,50,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
		
		//8.����ImageSpan,Ȼ����ImageSpan���滻�ı�
		ImageSpan imagspan = new ImageSpan(dr, ImageSpan.ALIGN_BASELINE);
		span.setSpan(imagspan, 18, 19, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		
		tv_one.setText(span);
	}
}
