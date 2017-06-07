package com.test.sunrongxin.bizerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	private View mWave;
	private View mB2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWave = findViewById(R.id.wave_view);
		mB2 = findViewById(R.id.b2_view);
	}

	public void showQuadBezier(View view) {
		mB2.setVisibility(View.VISIBLE);
		mWave.setVisibility(View.GONE);
	}

	public void showWaveBezier(View view) {
		mWave.setVisibility(View.VISIBLE);
		mB2.setVisibility(View.GONE);
	}
}
