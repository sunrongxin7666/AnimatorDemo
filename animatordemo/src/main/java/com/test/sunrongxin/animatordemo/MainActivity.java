package com.test.sunrongxin.animatordemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view) {
		Toast.makeText(this,"onclick",Toast.LENGTH_SHORT).show();
	}

	public void move(View view) {
		ImageView image = (ImageView) findViewById(R.id.imageView);
//		TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 0);
//		translateAnimation.setFillAfter(true);
//		translateAnimation.setDuration(1000);
//		image.startAnimation(translateAnimation);

//		alpha 透明度
//		rotation z轴旋转
//		rotationX x轴旋转
//		rotationY y轴旋转
//		translationX x水平偏移
//		translationY y水平偏移
//		ScaleX x轴缩放
//		ScaleY y轴缩放
		//如果多个动画，会同时执行；
//		ObjectAnimator.ofFloat(image,"rotation",0f,360f).setDuration(1000).start();
//		ObjectAnimator.ofFloat(image,"TranslationX",0f,200f).setDuration(1000).start();
//		ObjectAnimator.ofFloat(image,"TranslationY",0f,200f).setDuration(1000).start();

		//多个动画还可以如此
//		PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("rotation",0f,360f);
//		PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("TranslationX",0f,300f);
//		PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("TranslationY",0f,300f);
//		ObjectAnimator.ofPropertyValuesHolder(image,p1,p2,p3).setDuration(1000).start();

		//动画集合
		ObjectAnimator rotation = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
		ObjectAnimator translationX = ObjectAnimator.ofFloat(image, "TranslationX", 0f, 200f);
		ObjectAnimator translationY = ObjectAnimator.ofFloat(image, "TranslationY", 0f, 200f);
		AnimatorSet animatorSet = new AnimatorSet();
		//animatorSet.playSequentially(rotation,translationX,translationY);
		animatorSet.playTogether(rotation,translationX,translationY);
//		animatorSet.play(rotation);
//		animatorSet.play(translationX).with(translationY).after(rotation);
		animatorSet.setDuration(1000).start();
	}

	public void doAlpha(final View view) {
		ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).setDuration(1000);
		alpha.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				Toast.makeText(view.getContext(),"get Animator",Toast.LENGTH_SHORT).show();
			}
		});
		startActivity(new Intent(this, SecondActivity.class));
		alpha.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {

			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		alpha.start();
	}
}
