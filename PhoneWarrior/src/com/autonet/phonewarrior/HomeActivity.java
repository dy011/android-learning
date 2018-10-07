package com.autonet.phonewarrior;

import com.autonet.phonewarrior.utils.MD5Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
	protected static final String TAG = "HomeActivity";
	private GridView list_home; 
	private MyAdapter adapter;
	
	private static SharedPreferences sp;
	private static String[] names = {
			"�ֻ�����","ͨѶ��ʿ","��������",
			"���̹���","����ͳ��","�ֻ�ɱ��",
			"��������","�߼�����","��������"
	};
	
	private static int[] ids = {
			R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
			R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
			R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		list_home = (GridView) findViewById(R.id.list_home);
		adapter = new MyAdapter();
		list_home.setAdapter(adapter);
		list_home.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 8://������������
					Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
					startActivity(intent);
					break;
				case 0://�����ֻ�����ҳ��
					showLostFindDialog();
					break;
				case 7://����߼�����
					Intent intents = new Intent(HomeActivity.this, AtoolsActiviy.class);
					startActivity(intents);
				default:
					break;
				}
			}
		});
	}
	
	protected void showLostFindDialog() {
		//�ж��Ƿ����ù�����
		if(isSetupPwd()){
			//�Ѿ����������ˣ� ������������Ի��� 
			showEnterDialog();
		}else{
			//û���������룬����������������ĶԻ���
			showSetupPwdDialog();
		}
		
	}
	
	/**
	 * ��������Ի���
	 */
	private void showEnterDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		//�Զ���һ�������ļ�
		View view =  View.inflate(HomeActivity.this, R.layout.dialog_enter_password, null);
		et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ������Ի���ȡ����
				dialog.dismiss();
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ȡ������
				String password = et_setup_pwd.getText().toString().trim();
				String savePassword = sp.getString("password", "");//ȡ�����ܺ��
				if(TextUtils.isEmpty(password)){
					Toast.makeText(HomeActivity.this, "����Ϊ��", 0).show();
					return;
				}
				
				if(MD5Utils.md5Password(password).equals(savePassword)){
					//�������������֮ǰ���õ�����
					//�ѶԻ���������������ҳ��
					dialog.dismiss();
					Log.i(TAG, "�ѶԻ��������������ֻ�����ҳ��");
					Intent intent = new Intent(HomeActivity.this, LostFindActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(HomeActivity.this, "�������", 1).show();
					et_setup_pwd.setText("");
					return;
				}
				
			}
		});
//		builder.setView(view);
 		dialog = builder.create();
		dialog.setView(view, 0, 0, 0, 0);
		dialog.show();
		
	}

	private EditText et_setup_pwd;
	private EditText et_setup_confirm;
	private Button btn_ok;
	private Button btn_cancel;
	private AlertDialog dialog;
	
	/**
	 * ��������Ի���
	 */
	private void showSetupPwdDialog() {
		AlertDialog.Builder builder = new Builder(HomeActivity.this);
		//�Զ���һ�������ļ�
		View view =  View.inflate(HomeActivity.this, R.layout.dialog_setup_password, null);
		et_setup_pwd = (EditText) view.findViewById(R.id.et_setup_pwd);
		et_setup_confirm = (EditText) view.findViewById(R.id.et_setup_confirm);
		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ������Ի���ȡ����
				dialog.dismiss();
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ȡ������
				String password = et_setup_pwd.getText().toString().trim();		
				String password_confirm = et_setup_confirm.getText().toString().trim();		
				if(TextUtils.isEmpty(password)||TextUtils.isEmpty(password_confirm)){
					Toast.makeText(HomeActivity.this, "����Ϊ��", 0).show();
					return;
				}
				//�ж��Ƿ�һ�£�һ���򱣴�
				if(password.equals(password_confirm)){
					//һ�µĻ����ͱ������룬�ѶԻ�����������Ҫ�����ֻ�����ҳ��
					Editor editor = sp.edit();
					editor.putString("password", MD5Utils.md5Password(password));//������ܺ��
					editor.commit();
					dialog.dismiss();
					Log.i(TAG, "һ�µĻ����ͱ������룬�ѶԻ�����������Ҫ�����ֻ�����ҳ��");
					Intent intent = new Intent(HomeActivity.this, LostFindActivity.class);
					startActivity(intent);
				}else{
					Toast.makeText(HomeActivity.this, "���벻һ��", 0).show();
					return;
				}
			}
		});
		builder.setView(view);
		dialog = builder.show();
		
	}

	/**
	 * �ж��Ƿ����ù�����
	 * @return  
	 */
	private boolean isSetupPwd(){
		String password = sp.getString("password", null);
//		if(TextUtils.isEmpty(password)){
//			return false;
//		}else{
//			return true;
//		} 
		
		return !TextUtils.isEmpty(password);
	}

	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return names.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = View.inflate(HomeActivity.this, R.layout.list_item_home, null);
			ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
			TextView tv_item = (TextView) view.findViewById(R.id.tv_item);
			
			tv_item.setText(names[position]);
			iv_item.setImageResource(ids[position]);
			return view;
		}
		
	}
}