package com.karnect.materialbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageButton;

public class MyButton extends ImageButton {

	private static final int INVALIDATE_DURATION = 15;
	private static int DIFFUSE_GAP = 10;
	private static int TAP_TIMEOUT;
	
	private int viewWidth, viewHeight;
	private int pointX, pointY;
	private int maxRadio;
	private int shaderRadio;
	
	private Paint bottomPaint, colorPaint;
	private boolean isPushButton;
	
	private int eventX, eventY;
	private long downTime = 0;
	
	public MyButton(Context context) {
		super(context);
		initPaint();
		TAP_TIMEOUT = ViewConfiguration.getLongPressTimeout();
	}

	

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
		TAP_TIMEOUT = ViewConfiguration.getLongPressTimeout();
	}

	public MyButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
		TAP_TIMEOUT = ViewConfiguration.getLongPressTimeout();
	}
	
	private void initPaint() {
		colorPaint = new Paint();
		bottomPaint = new Paint();
		
		colorPaint.setColor(getResources().getColor(R.color.reveal_color));
		bottomPaint.setColor(getResources().getColor(R.color.bottom_color));
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		
			case MotionEvent.ACTION_DOWN:
				performClick();
				if(downTime == 0)
					downTime = SystemClock.elapsedRealtime();
				
				eventX = (int) event.getX();
				eventY = (int) event.getY();
				
				countMaxRadio();
				isPushButton = true;
				postInvalidateDelayed(INVALIDATE_DURATION);
				break;
				
			case MotionEvent.ACTION_UP:
				
			case MotionEvent.ACTION_CANCEL:
				if(SystemClock.elapsedRealtime() - downTime < TAP_TIMEOUT){
					DIFFUSE_GAP = 30;
					postInvalidate();
				}else{
					clearData();
				}
				break;

			default:
				break;
		}
		return super.onTouchEvent(event);
	}



	private void clearData() {
		downTime = 0;
		DIFFUSE_GAP = 10;
		isPushButton = false;
		shaderRadio = 0;
		postInvalidate();
	}



	private void countMaxRadio() {
		if(viewWidth > viewHeight){
			if(eventX < viewWidth/2){
				maxRadio = viewWidth - eventX;
			}else{
				maxRadio = viewWidth/2 + eventX;
			}
		}else{
			if(eventY < viewHeight/2){
				maxRadio = viewHeight - eventY;
			}else{
				maxRadio = viewHeight/2+eventY;
			}
		}
	}

	@Override
	public boolean performClick() {
		// TODO Auto-generated method stub
		return super.performClick();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		this.viewWidth = w;
		this.viewHeight= h;
	}



	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.dispatchDraw(canvas);
		if(!isPushButton)
			return;
		
		canvas.drawRect(pointX, pointY, pointX + viewWidth, pointY + viewHeight, bottomPaint);
		canvas.save();
		
		canvas.clipRect(pointX, pointY, pointX + viewWidth, pointY + viewHeight);
		canvas.drawCircle(eventX, eventY, shaderRadio, colorPaint);
		canvas.restore();
		
		if(shaderRadio < maxRadio){
			postInvalidateDelayed(INVALIDATE_DURATION, pointX, pointY, pointX + viewWidth, pointY + viewHeight);
			
			shaderRadio += DIFFUSE_GAP;
		}else{
			clearData();
		}
	}

	
}
