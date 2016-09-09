package com.xingtam.testmain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.xingtam.tamutils.R;

public class MainActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);
		initViews();		
	}
	private void initViews(){
		findViewById(R.id.btn01_layoutanim).setOnClickListener(this);
		findViewById(R.id.btn02_layoutslide).setOnClickListener(this);
		findViewById(R.id.btn03_calendar).setOnClickListener(this);
		findViewById(R.id.btn04_inputcheck).setOnClickListener(this);
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn01_layoutanim:
			intentAnimationActivity();
			break;
		case R.id.btn02_layoutslide:
			intentListviewSlideActivity();
			break;
		case R.id.btn03_calendar:
			intentListviewCalendarActivity();
			break;				
		case R.id.btn04_inputcheck:
			intentInputCheckActivity();
			break;	
		default:
			break;
		}
	}
	/*
	 * btn_01
	 * */
	private void intentAnimationActivity(){
		Intent intent = new Intent(MainActivity.this,AnimationActivity.class);
		startActivity(intent);
	}
	/*
	 * btn_02
	 * */
	private void intentListviewSlideActivity(){
		Intent intent = new Intent(MainActivity.this,ListviewSlideActivity.class);
		startActivity(intent);
	}
	/*
	 * btn_03
	 * */
	private void intentListviewCalendarActivity(){
		Intent intent = new Intent(MainActivity.this,ListviewCalendarActivity.class);
		startActivity(intent);
	}	
	/*
	 * btn_04
	 * */
	private void intentInputCheckActivity(){
		Intent intent = new Intent(MainActivity.this,DialogSweetActivity.class);
		startActivity(intent);
	}
	/*
	 * btn_04
	 * */
	private void intentDialogSweetActivity(){
		Intent intent = new Intent(MainActivity.this,InputCheckActivity.class);
		startActivity(intent);
	}
	public  void testMenthod(){
	}
}
