package com.autonet.phonewarrior.ui;

import com.autonet.phonewarrior.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * �����Զ������Ͽؼ���������������TextView������һ��CheckBox
 * ����һ��View
 * @author think
 *
 */
public class SettingItemView extends RelativeLayout {

	private CheckBox cb_status;
	private TextView tv_description;
	private TextView tv_title;
	
	private String title;
	private String description_on;
	private String description_off;
	
	
	/**
	 * ��ʼ�������ļ�
	 * @param context
	 */
	private void iniView(Context context) {
		//��һ�������ļ�---��View ���Ҽ�����SettingItemView
		View.inflate(context, R.layout.setting_item_view, this);
		cb_status = (CheckBox) this.findViewById(R.id.cb_status);
		tv_description = (TextView) this.findViewById(R.id.tv_description);
		tv_title = (TextView) this.findViewById(R.id.tv_title);
	}
	
	public SettingItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		iniView(context);
	}
 
	/**
	 * �������������Ĺ��췽���������ļ�ʹ�õ�ʱ�����
	 * @param context
	 * @param attrs
	 */
	public SettingItemView(Context context, AttributeSet attrs) {
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

	public SettingItemView(Context context) {
		super(context);
		iniView(context);
	}
	
	/**
	 * У����Ͽؼ��Ƿ�ѡ��
	 * @return
	 */
	public boolean isChecked(){
		return cb_status.isChecked();
	}
	
	/**
	 * ������Ͽؼ���״̬
	 */
	public void setChecked(boolean checked){
		if(checked){
			setDescription(description_on);
		}else{
			setDescription(description_off);
		}
		cb_status.setChecked(checked);
	}
	
	/**
	 * ������Ͽؼ���������Ϣ
	 */
	public void setDescription(String text){
		tv_description.setText(text);
	}
	

}
