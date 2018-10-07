package com.karnect.edittextwithdel;

import com.example.edittextwithdel.R;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

public class EditTextWithDel extends EditText {
	
	private final static String TAG="EditTextWithDel";
	private Drawable imgInable;
	private Drawable imgAble;
	private Context mContext;

	public EditTextWithDel(Context context) {
		super(context);
		mContext = context;
		init();
	}


	public EditTextWithDel(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		imgInable = mContext.getResources().getDrawable(R.drawable.delete_gray);
		
		addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				setDrawable();
			}
		});
		setDrawable();
	}

	//…Ë÷√…æ≥˝Õº∆¨
	private void setDrawable() {
		// TODO Auto-generated method stub
		if(length() < 1){
			setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
		}
		else{
			setCompoundDrawablesWithIntrinsicBounds(null, null, imgInable, null);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		
		if(imgAble != null && event.getAction() == MotionEvent.ACTION_UP){
			int eventX = (int) event.getRawX();
			int eventY = (int) event.getRawY();
			
			Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
			Rect rect = new Rect();
			getGlobalVisibleRect(rect);
			rect.left = rect.right - 100;
			if(rect.contains(eventX, eventY))
				setText("");
		}
		
		return super.onTouchEvent(event);
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
}
