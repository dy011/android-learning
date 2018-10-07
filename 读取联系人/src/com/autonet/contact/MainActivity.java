package com.autonet.contact;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	private ListView select_contact;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		select_contact = (ListView) findViewById(R.id.select_contact);
		List<Map<String, String>> data = getContactInfo();
		select_contact.setAdapter(new SimpleAdapter(this, data, R.layout.contact_item_view,
				new String[]{"name", "phone"}, new int[]{R.id.tv_name, R.id.tv_phone}));
	}
	
	/**
	 * ��ȡ��ϵ��
	 * @return
	 */
	private List<Map<String, String>> getContactInfo() {
		
		//�����е���ϵ��
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		//�õ�һ�����ݽ�����
		ContentResolver resolver = getContentResolver();
		//raw_contacts 
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri uriData = Uri.parse("content://com.android.contacts/data");
		
		Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);
		
		while(cursor.moveToNext()){
			String contact_id = cursor.getString(0);
			
			if(contact_id != null){
				//�����ĳһ����ϵ��
				Map<String, String> map = new HashMap<String, String>();
				Cursor dataCursor = resolver.query(uriData, 
						new String[]{"data1", "mimetype"}, 
						"contact_id=?", new String[]{contact_id}, 
						null);
				
				while (dataCursor.moveToNext()) {
					String data1 = dataCursor.getString(0);
					String mimetype = dataCursor.getString(1);
					System.out.println("data1=="+data1+"==mimetype=="+mimetype);
					
					if("vnd.android.cursor.item/name".equals(mimetype)){
						//��ϵ�˵�����
						map.put("name", data1);
					}else if("vnd.android.cursor.item/phone_v2".equals((mimetype))){
						//��ϵ�˵ĵ绰����
						map.put("phone", data1);	
					}
				}
				
				list.add(map);
				dataCursor.close();
			}
		}
		
		cursor.close();
		return list;
	}

}





















