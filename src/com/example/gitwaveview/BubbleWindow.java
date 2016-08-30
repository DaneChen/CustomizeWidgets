package com.example.gitwaveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BubbleWindow extends View {

	private int mWidth = 0;
	private int mHeight = 0;
	
	private int mRectWidth = 0;
	private int mRectHeight = 0;
	private float mRectPercent = 0.9f;
    Paint p = new Paint();  
    Path path = new Path();  
    RectF mRectF = new RectF(0,0,mRectWidth,mRectHeight);
    
	public BubbleWindow(Context context) {
		this(context, null);
	}

	 public BubbleWindow(Context context, AttributeSet attrs) {  
	     this(context, attrs,0);  
	 }  
	 
	 public BubbleWindow(Context context, AttributeSet attrs, int defStyleAttr) {  
	     super(context, attrs, defStyleAttr);  
	 } 
	 
	 @Override  
	  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	      int widthSize = MeasureSpec.getSize(widthMeasureSpec);  
	      int widthMode = MeasureSpec.getMode(widthMeasureSpec);  
	      int heightSize = MeasureSpec.getSize(heightMeasureSpec);  
	      int heightMode = MeasureSpec.getMode(heightMeasureSpec);  
	      if(widthMode== MeasureSpec.EXACTLY){  
	          mWidth = widthSize; //获取到当前view的宽度  
	          mRectWidth = (int) (mWidth * mRectPercent);//计算矩形的大小 这里是直接给的初值为0.8 也就是说矩形是view大小的0.8倍 下同  
	      }  
	      if(heightMode == MeasureSpec.EXACTLY){  
	          mHeight = heightSize;//获取当前view的高度  
	  
	          mRectHeight = (int) (mHeight * mRectPercent+0.1);  
	      }  
	  
	      setMeasuredDimension(mWidth,mHeight);  
	  }  
	 
	 @Override  
	   protected void onDraw(Canvas canvas) {  
	   
	       p.setColor(Color.parseColor("#2C97DE"));  
	       p.setStyle(Paint.Style.FILL);  
	       
	       mRectF.set(0,0,mRectWidth,mRectHeight);
	       canvas.drawRoundRect(mRectF,15,15,p);  
	  
	       path.moveTo(mRectWidth/2-30,mRectHeight);  
	       path.lineTo(mRectWidth/2,mRectHeight+20);  
	       path.lineTo(mRectWidth/2+30,mRectHeight);  
	       path.close();  
	       canvas.drawPath(path,p);  
	       super.onDraw(canvas);  
	   } 
}
