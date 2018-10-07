package com.karnect.framelayoutmove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GirlView extends View {

	public float bitmapX;
	public float bitmapY;
	
	public GirlView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), 
				R.drawable.s_jump);
		
		canvas.drawBitmap(bitmap, bitmapX, bitmapY,paint);
		
		if(bitmap.isRecycled()){
			bitmap.recycle();
		}
			
	}

}
