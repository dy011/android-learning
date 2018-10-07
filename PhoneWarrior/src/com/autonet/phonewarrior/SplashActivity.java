package com.autonet.phonewarrior;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.autonet.phonewarrior.utils.StreamTools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

public class SplashActivity extends Activity {
	protected static final String TAG = "SplashActivity";
	protected static final int ENTER_HOME = 0;
	protected static final int SHOW_UPDATE_DIALOG = 1;
	protected static final int URL_ERROR = 2;
	protected static final int NETWORK_ERROR = 3;
	protected static final int JSON_ERROR = 4;
	private TextView tv_splash_version;
	private String description;
	private TextView tv_update_info;
    /**
     * 新版本的下载地址
     */
	private String apkurl;
	private SharedPreferences sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本号"+getVersionName());
		tv_update_info = (TextView) findViewById(R.id.tv_update_info);
		boolean update = sp.getBoolean("update", false);
		
		//拷贝数据库
		copyDB();
		
		if(update){
			//检查升级
			checkUpdate();
		}else{
			//自动升级已经关闭
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					//进入主页面
					enterHome();
					
				}
			}, 2000);
		}
		
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(1000);
		findViewById(R.id.rl_root_splash).startAnimation(aa);
	}
	
	/**
	 * 把address.db这个数据库拷贝到data/data/<包名>/files/address.db
	 */
	private void copyDB() {
		//只要你拷贝一次，我就不需要你再拷贝了
		try {
			File file = new File(getFilesDir(), "address.db");
			if(file.exists()&&file.length()>0){
				//正常了，就不需要拷贝了
				Log.i(TAG, "正常了，就不需要拷贝了");
			}else{
				InputStream is = getAssets().open("address.db");  
				
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = is.read(buffer)) != -1){
					fos.write(buffer, 0, len);
				}
				is.close();
				fos.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case SHOW_UPDATE_DIALOG://显示升级的对话框
				Log.i(TAG, "显示升级的对话框");
				showUpdateDialog();
				break;
				
			case ENTER_HOME://进入主页面
				enterHome();
				break;
				
			case URL_ERROR://URL错误
				Toast.makeText(getApplicationContext(), "URL错误", 0).show();
				enterHome();
				break;
				
			case NETWORK_ERROR://网络错误
				Toast.makeText(SplashActivity.this, "网络异常", 0).show();
				enterHome();
				break;
				
			case JSON_ERROR://JSON解析出错
				Toast.makeText(SplashActivity.this, "JSON解析错误", 0).show();
				enterHome();
				break;

			default:
				break;
			}
		}

		
	};
	
	
	
	protected void enterHome() {
	// TODO Auto-generated method stub
	Intent intent = new Intent(this, HomeActivity.class);
	startActivity(intent);
	finish();
		
	}
	
	/**
	 * 弹出升级对话框
	 */
	protected void showUpdateDialog() {
		//this = Activity.this
		
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("提示升级");
		//builder.setCancelable(false);//强制升级
		builder.setOnCancelListener(new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				// TODO Auto-generated method stub
				//进入主页面
				enterHome();
				dialog.dismiss();
			}
		});
		builder.setMessage(description);
		builder.setPositiveButton("立刻升级", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 下载APK，并且替换安装
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					//sdcard存在
					//面向组建编程afinal
					FinalHttp finalhttp = new FinalHttp();
					finalhttp.download(apkurl, 
							Environment.getExternalStorageDirectory().getAbsolutePath()+"/phonewarrior2.0.apk", 
							new AjaxCallBack<File>(){

								@Override
								public void onFailure(Throwable t, int errorNo, String strMsg) {
									t.printStackTrace();
									Toast.makeText(getApplicationContext(), "下载失败", 1).show();
									super.onFailure(t, errorNo, strMsg);
									
								}

								@Override
								public void onLoading(long count, long current) {
									// TODO Auto-generated method stub
									super.onLoading(count, current);
									tv_update_info.setVisibility(View.VISIBLE);
									//当前下载百分比
									int progress = (int)(current * 100 / count);
											
									tv_update_info.setText("下载进度："+progress+"%");
								}

								@Override
								public void onSuccess(File t) {
									// TODO Auto-generated method stub
									super.onSuccess(t);
									installAPK(t);
								}

								/**
								 * 安装APK
								 * @param t
								 */
								private void installAPK(File t) {
									Intent intent = new Intent();
									intent.setAction("android.intent.action.VIEW");
									intent.addCategory("android.intent.category.DEFAULT");
									intent.setDataAndType(Uri.fromFile(t), "application/vnd.android.package-archive");
									startActivity(intent);
								}
						
					});
					
				}else{
					Toast.makeText(getApplicationContext(), "没有sdcard，请安装上再试", 0).show();
					return;
				}
				
			}
		});
		builder.setNegativeButton("下次再说", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				enterHome();//进入主页面
			}
		});
		builder.show();
	}

	/*
	 * 检查是否有新版本，如果有就升级
	 * */
	private void checkUpdate() {
		 new Thread(){
		 	public void run(){
		 		Message mes = Message.obtain();
		 		long startTime = System.currentTimeMillis();
			 	try {
			 		//URL=http://127.0.0.1:8080/updateinfo.html
					URL url = new URL(getString(R.string.serverurl));
					
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setConnectTimeout(4000);
						int code = conn.getResponseCode();
						if(code == 200){
							//联网成功
							InputStream is = conn.getInputStream();
							//把流转成String
							String result = StreamTools.readFromStream(is);
							Log.i(TAG, "联网成功了"+result);
							
							//Json解析
							JSONObject obj = new JSONObject(result);
							//得到服务器的版本信息
							String version = (String) obj.get("version");
							description = (String) obj.get("description");
							apkurl = (String) obj.get("apkurl");
							
							if(getVersionName().equals(version)){
								//版本一致，没有新版本，进入主页面
								mes.what = ENTER_HOME;
							}else{
								//有新版本，弹出一升级对话框
								mes.what = SHOW_UPDATE_DIALOG;
							}
						}
					} catch (MalformedURLException e) {
						// TODO: handle exception
						mes.what = URL_ERROR;
						e.printStackTrace();
					}catch (IOException e) {
						// TODO: handle exception
						mes.what = NETWORK_ERROR;
						e.printStackTrace(); 
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						mes.what = JSON_ERROR;
						e.printStackTrace();
					}finally{
						long endTime = System.currentTimeMillis();
						//我们花了多少时间
						long dTime = endTime - startTime;
						//2000
						if(dTime < 2000){
							try {
								Thread.sleep(2000 - dTime);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						handler.sendMessage(mes);
					}
		 	};
		 }.start();
		
	}

	/**
	 * 得到应用程序的版本名称 
	 */
	private String getVersionName(){
		//用来管理手机的APK
		PackageManager pm = getPackageManager();
		
		try {
			//得到已知APK的功能清单文件
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
