package com.test.sunrongxin.vectordemo;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
	ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mImageView = (ImageView) findViewById(R.id.image);
	}

	public void anim(View view) {
		ImageView imageView = (ImageView) view;
		Drawable drawable = imageView.getDrawable();
		if(drawable instanceof Animatable) {
			((Animatable) drawable).start();
			Toast.makeText(view.getContext(), "下载开始！",Toast.LENGTH_SHORT).show();
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sleep(5000);
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							((Animatable) mImageView.getDrawable()).stop();
							Toast.makeText(getApplicationContext(),"下载完毕",Toast.LENGTH_SHORT).show();
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
