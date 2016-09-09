package com.xingtam.testmain;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.xingtam.androidclass.Animations;
import com.xingtam.tamutils.R;
import com.xingtam.tamutils.ToastUtil;

/**
 * ∫Ë—Ûblog-http://blog.csdn.net/lmj623565791/article/details/38092093
 * */
public class AnimationActivity extends Activity implements OnCheckedChangeListener, OnClickListener{
	private ViewGroup viewGroup;
	private GridLayout mGridLayout;
	private int mVal;
	private LayoutTransition mTransition;
	private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;
	private ImageView mImage;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animation);
		viewGroup = (ViewGroup) findViewById(R.id.id_container);
		
		mImage = (ImageView) findViewById(R.id.animation_image);

		mAppear = (CheckBox) findViewById(R.id.id_appear);
		mChangeAppear = (CheckBox) findViewById(R.id.id_change_appear);
		mDisAppear = (CheckBox) findViewById(R.id.id_disappear);
		mChangeDisAppear = (CheckBox) findViewById(R.id.id_change_disappear);
		mAppear.setOnCheckedChangeListener(this);
		mChangeAppear.setOnCheckedChangeListener(this);
		mDisAppear.setOnCheckedChangeListener(this);
		mChangeDisAppear.setOnCheckedChangeListener(this);

		mGridLayout = new GridLayout(this);
		mGridLayout.setColumnCount(5);
		viewGroup.addView(mGridLayout);
		mTransition = new LayoutTransition();
		mGridLayout.setLayoutTransition(mTransition);
		
		findViewById(R.id.anim_frame).setOnClickListener(this);
		findViewById(R.id.anim_object).setOnClickListener(this);
		findViewById(R.id.anim_value).setOnClickListener(this);
		findViewById(R.id.anim_view).setOnClickListener(this);
		findViewById(R.id.anim_xml).setOnClickListener(this);

	}

	/**
	 * add btns
	 * 
	 * @param view
	 */
	@SuppressLint("NewApi")
	public void addBtn(View view) {
		final Button button = new Button(this);
		button.setText((++mVal) + "");
		if(mGridLayout.getChildCount() < 15){
			mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));
		}else{
			ToastUtil.showShortToast(AnimationActivity.this, "Too many buttons!");
		}
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mGridLayout.removeView(button);
			}
		});
	}

	@SuppressLint("NewApi")
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		mTransition = new LayoutTransition();
		mTransition.setAnimator(LayoutTransition.APPEARING,
				(mAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.APPEARING) : null));
		mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
						(mChangeAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_APPEARING): null));
		mTransition.setAnimator(LayoutTransition.DISAPPEARING,
				(mDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.DISAPPEARING) : null));
		mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
				(mChangeDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING): null));
		mGridLayout.setLayoutTransition(mTransition);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.anim_frame:			
			Animations.getDrawableAnimation(this, mImage);		
			break;
		case R.id.anim_object:
			Animations.getObjectAnimatorAnimation(mImage);	
			break;
		case R.id.anim_value:
			Animations.getValueAnimatorAnimation(mImage);
			break;
		case R.id.anim_view:
			Animations.getViewAnimation(this,mImage);
			break;
		case R.id.anim_xml:
			Animations.getXmlPropertyAnimation(this, mImage);
			break;
		default:
			break;
		}
	}
}
