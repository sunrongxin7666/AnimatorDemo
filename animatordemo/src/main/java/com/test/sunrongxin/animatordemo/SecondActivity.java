package com.test.sunrongxin.animatordemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
	// an array of imageView ids.
	int[] res = {R.id.imageView_a,R.id.imageView_b,R.id.imageView_c,R.id.imageView_d,R.id.imageView_e,
			R.id.imageView_f,R.id.imageView_g};

	// a list of ImageViews
	private List<ImageView> mImageViewList = new ArrayList<>();

	// a flag to control opening action or closing action
	private boolean opened = false;

	int r = 150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		initView();
	}

	private void initView() {
		for(int i=0;i<res.length;i++){
			ImageView imageView = (ImageView) findViewById(res[i]);
			mImageViewList.add(imageView);
			imageView.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.imageView_a:
				if(!opened) {
					openAnimator();
				} else {
					closeAnimator();
				}
				break;
			default:
				Toast.makeText(v.getContext(),"Id: "+v.getId(),Toast.LENGTH_SHORT).show();
				break;
		}
	}

	private void closeAnimator() {
		for(int i=0;i<res.length-1;i++){
			moveBack(mImageViewList.get(i+1),(float) (Math.PI/2/(res.length-2))*i);
		}
		opened = false;
	}

	private void openAnimator() {
		for(int i=0;i<res.length-1;i++){
			moveTo(mImageViewList.get(i+1),(float) (Math.PI/2/(res.length-2))*i);
		}
		opened = true;
	}

	public void doClick(final View view) {

//		ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator() {
//			@Override
//			public Object evaluate(float fraction, Object startValue, Object endValue) {
//				return null;
//			}
//		});
		final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
		valueAnimator.setDuration(5000);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer value = (Integer) valueAnimator.getAnimatedValue();
				((Button) view).setText(""+value);
			}
		});
		valueAnimator.start();
	}

	/**
	 * 扇形展开
	 * @param objView 目标控件
	 * @param angle 展开的角度
	 */
	void moveTo(View objView, float angle){
		ObjectAnimator translationX = ObjectAnimator.ofFloat(objView, "translationX", 0f, (float) Math.cos(angle) * r);
		ObjectAnimator translationY = ObjectAnimator.ofFloat(objView, "translationY", 0f, (float) Math.sin(angle) * r);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(translationX,translationY);
		animatorSet.setDuration(500).start();
	}

	void moveBack(View objView, float angle){
		ObjectAnimator translationX = ObjectAnimator.ofFloat(objView, "translationX", (float) Math.cos(angle) * r,0f);
		ObjectAnimator translationY = ObjectAnimator.ofFloat(objView, "translationY", (float) Math.sin(angle) * r,0f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(translationX,translationY);
		animatorSet.setDuration(500).start();
	}
}
