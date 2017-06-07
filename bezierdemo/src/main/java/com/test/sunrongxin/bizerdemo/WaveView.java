package com.test.sunrongxin.bizerdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Sun Rongxin on 2017/6/7.
 * show how to use 2 Bezier curves to draw water-wave line
 */

public class WaveView extends View implements View.OnClickListener{

	private int mWaveLength; //波长
	private int mScreenHeight; //屏幕高
	private int mScreenWidth; //屏幕宽
	private int mCenterY; //Y轴上的重点
	private int mWaveCount; //屏幕上能显示完整波形的个数
	private int mOffset; //波形绘制的偏移量

	private ValueAnimator mValueAnimator; //改变 mOffSet的插值器
	private Paint mPaintBezier; //绘制波纹的画笔1
	private Path mPath; //绘制波纹的路径1

	public WaveView(Context context) {
		super(context);
	}

	public WaveView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaintBezier.setColor(Color.LTGRAY);
		mPaintBezier.setStrokeWidth(8);
		mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);

		mWaveLength = 800;
	}

	public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mPath = new Path();
		setOnClickListener(this);

		mScreenHeight = h;
		mScreenWidth = w;
		mCenterY = h / 2;

		//计算需要绘制几个完整的波形，注意需要多绘制一个完整的波形用来位移，多出来0.5是防止被四舍五入
		mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mPath.reset();
		//位移到屏幕外左侧一个波长的地方，开始绘制水波
		mPath.moveTo(-mWaveLength + mOffset, mCenterY);
		//利用两个Bezier曲线绘制出水波
		for (int i = 0; i < mWaveCount; i++) {
			int totalOffSet = i * mWaveLength + mOffset;
			//控制点在波峰处上方
			mPath.quadTo(-mWaveLength * 3 / 4 + totalOffSet, mCenterY + 60, -mWaveLength / 2 + totalOffSet, mCenterY);
			//控制点在波谷处下方
			mPath.quadTo(-mWaveLength / 4 + totalOffSet, mCenterY - 60, totalOffSet, mCenterY);
		}
		//闭合图象，并填充
		mPath.lineTo(mScreenWidth, mScreenHeight);
		mPath.lineTo(0, mScreenHeight);
		mPath.close();
		canvas.drawPath(mPath, mPaintBezier);
	}

	@Override
	public void onClick(View v) {
		//点击View,开始动画
		mValueAnimator = ValueAnimator.ofInt(0, mWaveLength);
		mValueAnimator.setDuration(1000);
		mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
		mValueAnimator.setInterpolator(new LinearInterpolator());
		//更新位移量
		mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				mOffset = (int) valueAnimator.getAnimatedValue();
				invalidate();
			}
		});
		mValueAnimator.start();
	}
}
