package com.autonet.phonewarrior;

import com.autonet.phonewarrior.service.AddressService;
import com.autonet.phonewarrior.ui.SettingClickView;
import com.autonet.phonewarrior.ui.SettingItemView;
import com.autonet.phonewarrior.utils.ServiceUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class SettingActivity extends Activity {
	
	//�����Ƿ����Զ�����
	private SettingItemView siv_update;
	private SharedPreferences sp;
	
	//�����Ƿ�����ʾ������
	private SettingItemView siv_show_address;
	private Intent showAddress;
	
	//���ù�������ʾ�򱳾�
	private SettingClickView scv_changebg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		//��ʼ�����Ƿ��Զ�����   
		siv_update = (SettingItemView) findViewById(R.id.siv_update);
		boolean update = sp.getBoolean("update", false);
		if(update){
			//�Զ������Ѿ�����
			siv_update.setChecked(true);
		}else{
			//�Զ������Ѿ��ر�
			siv_update.setChecked(false);
		}
		
		siv_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Editor editor = sp.edit();
				// �ж��Ƿ�ѡ��
				//�Ѿ����Զ�����
				if(siv_update.isChecked()){
					siv_update.setChecked(false);
					editor.putBoolean("update", false);
				}else{
					//û�д��Զ�����ad
					siv_update.setChecked(true);
					editor.putBoolean("update", true);
				}
				editor.commit();
				
			}
		});
		
		//���ú����������ʾ�ռ�
		siv_show_address = (SettingItemView) findViewById(R.id.siv_show_address);
		showAddress = new Intent(this, AddressService.class);
		boolean isServiceRunning = ServiceUtils.isServiceRunning(SettingActivity.this, 
				"com.autonet.phonewarrior.service.AddressService");
		
		if(isServiceRunning){
			//��������ķ����Ƿ���
			siv_show_address.setChecked(true);
		}else{
			siv_show_address.setChecked(false);
		}
		
		siv_show_address.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(siv_show_address.isChecked()){
					 //��Ϊ��ѡ��״̬
					siv_show_address.setChecked(false);
					stopService(showAddress);
				}else{
					//ѡ��״̬
					siv_show_address.setChecked(true);
					startService(showAddress);
				}
				
			}
		});
		
		//���ú�������صı���
		scv_changebg = (SettingClickView) findViewById(R.id.scv_changebg);
		scv_changebg.setTitle("��������ʾ����");
		final String[] items = {"��͸��", "������", "��ʿ��", "������", "ƻ����"};
		int which = sp.getInt("which", 0);
		scv_changebg.setDescription(items[which]);
		scv_changebg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int dd = sp.getInt("which", 0);
				// ����һ���Ի���
				AlertDialog.Builder builder = new Builder(SettingActivity.this);
				builder.setTitle("��������ʾ����");
				builder.setSingleChoiceItems(items, dd, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						// ����ѡ�����
						Editor editor = sp.edit();
						editor.putInt("which", which);
						editor.commit();
						scv_changebg.setDescription(items[which]);
						
						//ȡ���Ի���
						dialog.dismiss();
					}
				});
				builder.setNegativeButton("cancel", null);
				builder.show();
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//siv_show_address = (SettingItemView) findViewById(R.id.siv_show_address);
		showAddress = new Intent(this, AddressService.class);
		boolean isServiceRunning = ServiceUtils.isServiceRunning(SettingActivity.this, 
				"com.autonet.phonewarrior.service.AddressService");
		
		if(isServiceRunning){
			//��������ķ����Ƿ���
			siv_show_address.setChecked(true);
		}
		else{
			siv_show_address.setChecked(false);
		}
		
	}
}
