package com.autonet.phonewarrior.ui;

import com.autonet.phonewarrior.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 我们自定义的组合控件，它里面有两个TextView，还有一个ImageView
 * 还有一个View
 * @author think
 *
 */
public class SettingClickView extends RelativeLayout {

	private TextView tv_description;
	private TextView tv_title;
	
	private String title;
	private String description_on;
	private String description_off;
	
	
	/**
	 * 初始化布局文件
	 * @param context
	 */
	private void iniView(Context context) {
		//把一个布局文件---》View 并且加载在SettingItemView
		View.inflate(context, R.layout.setting_click_view, this);
		tv_description = (TextView) this.findViewById(R.id.tv_description);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
	}
	
	public SettingClickView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		iniView(context);
	}
 
	/**
	 * 带有两个参数的构造方法，布局文件使用的时候调用
	 * @param context
	 * @param attrs
	 */
	public SettingClickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		iniView(context);
//		System.out.println(attrs.getAttributeValue(0));
//		System.out.println(attrs.getAttributeValue(1));
//		System.out.println(attrs.getAttributeValue(2));
//		System.out.println(attrs.getAttributeValue(3));
//		System.out.println(attrs.getAttributeValue(4));
//		System.out.println(attrs.getAttributeValue(5));
		title = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.autonet.phonewarrior","title1");
		description_on = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.autonet.phonewarrior","description_on");
		description_off = attrs.getAttributeValue("http://schemas.android.com/apk/res/com.autonet.phonewarrior","description_off");
		tv_title.setText(title);
		setDescription(description_off);
		
	} 

	public SettingClickView(Context context) {
		super(context);
		iniView(context);
	}
	
	
	/**
	 * 设置组合控件的标题
	 */
	public void setTitle(String title){
		tv_title.setText(title);
	}
	
	/**
	 * 设置组合控件的描述信息
	 */
	public void setDescription(String text){
		tv_description.setText(text);
	}
	

}
