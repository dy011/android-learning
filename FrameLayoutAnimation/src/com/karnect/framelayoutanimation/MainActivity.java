package com.karnect.framelayoutanimation;

import java.util.Timer;

import com.example.framelayoutanimation.R;

import java.util.TimerTask;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.FrameLayout;


public class MainActivity extends Activity {

	//初始化变量,帧布局
	FrameLayout frame = null;
	
	//自定义一个用于定时更新UI界面的handler类对象
	Handler hander = new Handler(){
		int i = 0;
		
		public void handleMessage(Message msg){
			if(msg.what == 0x123){
				i++;
				move(i % 8);
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		frame = (FrameLayout) findViewById(R.id.myframe);
		
		new Timer().schedule(new TimerTask(){
			
			@Override
			public void run(){
				hander.sendEmptyMessage(0x123);
			}
			
		}, 0, 170);
	}

	//定义走路时切换图片的方法
	protected void move(int i) {
		Drawable a = getResources().getDrawable(R.drawable.s_1);
		Drawable b = getResources().getDrawable(R.drawable.s_2);
		Drawable c = getResources().getDrawable(R.drawable.s_3);
		Drawable d = getResources().getDrawable(R.drawable.s_4);
		Drawable e = getResources().getDrawable(R.drawable.s_5);
		Drawable f = getResources().getDrawable(R.drawable.s_6);
		Drawable g = getResources().getDrawable(R.drawable.s_7);
		Drawable h = getResources().getDrawable(R.drawable.s_8);
		
		switch (i) {
		
		case 0:
			frame.setForeground(a);
			break;

		case 1:
			frame.setForeground(b);
			break;
			
		case 2:
			frame.setForeground(c);
			break;

		case 3:
			frame.setForeground(d);
			break;
			
		case 4:
			frame.setForeground(e);
			break;

		case 5:
			frame.setForeground(f);
			break;
			
		case 6:
			frame.setForeground(g);
			break;

		case 7:
			frame.setForeground(h);
			break;
			
		default:
			break;
		}
	}
}
