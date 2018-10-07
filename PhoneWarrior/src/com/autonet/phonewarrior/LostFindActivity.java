/**
 * 
 */
package com.autonet.phonewarrior;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Listenanr
 *
 */
public class LostFindActivity extends Activity {
	
	private SharedPreferences sp;
	private TextView tv_safenumber;
	private ImageView iv_protected;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		boolean configed = sp.getBoolean("configed", false);
		//�ж�һ�£��Ƿ����������򵼣����û��������������������ҳ��ȥ���ã�
		//��������ڵ�ǰ��ҳ��
		if(configed){
			//�����ֻ�����ҳ��
			setContentView(R.layout.activity_lost_find);
			tv_safenumber = (TextView) findViewById(R.id.tv_safenumber);
			iv_protected = (ImageView) findViewById(R.id.iv_protected);
			//�õ��������õİ�ȫ����
			String safenumber = sp.getString("safenumber", "");
			tv_safenumber.setText(safenumber);
			//���÷���������״̬
			boolean isProtected = sp.getBoolean("protected", false);
			if(isProtected){
				//�Ѿ�������������
				iv_protected.setImageResource(R.drawable.lock);
			}else{
				//û�п�����������
				iv_protected.setImageResource(R.drawable.unlock);
			}
		}else{
			//��û������������
			Intent intent = new Intent(this, Setup1Activity.class);
			startActivity(intent);
			//�رյ�ǰҳ��
			finish();
		}
	}
	
	/**
	 * ���½����ֻ�����ҳ��
	 * @param view
	 */
	public void reEnterSetup(View view){
		Intent intent = new Intent(this, Setup1Activity.class);
		startActivity(intent);
		//�رյ�ǰҳ��
		finish();
	}
}
