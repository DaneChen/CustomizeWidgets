package com.example.gitwaveview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ErrorView extends View {

	private int mWidth = 0;
	private int mHeight = 0;

	private int mProgress;

	private int mLineOneX = 0;
	private int mLineOneY = 0;

	private int mLineTwoX;
	private int mLineTwoY;

	private boolean isLineDrawDone = false;
	private boolean isDrawDone = false;

	private int mScroll;
	private OnStopListener mOnStopListener;

	Paint p = new Paint();
	RectF rectF = new RectF();
	
	public ErrorView(Context context) {
		this(context, null);
	}

	public ErrorView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode == MeasureSpec.EXACTLY) {
			mWidth = widthSize;
		} else {
			mWidth = 200;
		}

		if (heightMode == MeasureSpec.EXACTLY) {
			mHeight = heightSize;
		} else {
			mHeight = 200;
		}
		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
	
		//画圆
		p.setStrokeWidth(10);
		p.setAntiAlias(true);
		p.setColor(Color.RED);
		p.setStyle(Paint.Style.STROKE);
		rectF.set(0 + 10, 0 + 10, mWidth - 10, mHeight - 10);
		canvas.drawArc(rectF, 180, 360 * mProgress / 100, false, p);
		mProgress += 10;

		if (mProgress >= 100) {
			// 画左边的线
			if (mLineOneX < mWidth * 0.5) {
				mLineOneX += 20;
				mLineOneY += 20;
			}
			canvas.drawLine(mWidth / 4, mHeight / 4, mWidth / 4 + mLineOneX,
					mHeight / 4 + mLineOneY, p);

			if (mLineOneX == mWidth * 0.5) {
				if (mLineTwoX < mWidth * 0.5) {
					mLineTwoX += 20;
					mLineTwoY += 20;
				} else {
					// 判断全部绘制完成
					isLineDrawDone = true;
				}
				canvas.drawLine(3*mWidth / 4, (float) (mHeight * 0.75), mWidth
						/ 4 + mLineTwoX, (float) (mHeight * 0.75) - mLineTwoY, p);
			}
		}

		if (isLineDrawDone) {
			Log.e("wing", "draw done");
			if (!isDrawDone) {
				if (mOnStopListener != null) {
					mOnStopListener.onStop(this);
				}
				isDrawDone = true;
			}

		} else {

			postInvalidateDelayed(10);
		}

		super.onDraw(canvas);
	}

	public void reset() {
		mProgress = 0;
		mLineOneX = 0;
		mLineOneY = 0;
		mLineTwoX = 0;
		mLineTwoY = 0;
		isLineDrawDone = false;
		isDrawDone = false;
		invalidate();
	}

	public interface OnStopListener {
		void onStop(View v);
	}

	public void setOnStopListener(OnStopListener onStopListener) {
		mOnStopListener = onStopListener;
	}

}
