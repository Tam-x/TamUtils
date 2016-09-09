package com.xingtam.androidclass;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.xingtam.tamutils.R;
import com.xingtam.tamutils.ToastUtil;

public class Animations {
	/* 1.View Animation */
	public static void getViewAnimation(final Context context,View testImage){
		testImage.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				ToastUtil.showShortToast(context, "View Animation!");
			}
		});
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_view_set);
		testImage.startAnimation(hyperspaceJumpAnimation);
	}
	/* 2.Drawable/Frame Animation */
	public static void getDrawableAnimation(Context context,ImageView image){
		image.setImageResource(R.anim.anim_drawable_res);  
		AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();  
        animationDrawable.start();  
	}
	/* 3.Property Animation */
	@SuppressLint("NewApi")
	public static void getObjectAnimatorAnimation(final View view){
		ObjectAnimator anim = ObjectAnimator 
	            .ofFloat(view, "rotationX", 0.0F, 540F)
	            .setDuration(5000);
	    anim.start();  
	    anim.addUpdateListener(new AnimatorUpdateListener()  
	    {  
	        @Override  
	        public void onAnimationUpdate(ValueAnimator animation)  
	        {  
	            float cVal = (Float) animation.getAnimatedValue();  
	            view.setAlpha(cVal);  
	            view.setScaleX(cVal/100);  
	            view.setScaleY(cVal/100);  
	            view.setRotation(cVal);
	        }  
	    });  
	    /* Another way to achieve
	     * PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,  
                0f, 1f);  
           PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,  
                0, 1f);  
           PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,  
                0, 1f);  
           ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();  
	     * */
	}
	@SuppressLint("NewApi")
	public static void getValueAnimatorAnimation(final View view){
		ValueAnimator valueAnimator = new ValueAnimator();  
        valueAnimator.setDuration(3000);  
        valueAnimator.setObjectValues(new PointF(0, 0));  
        valueAnimator.setInterpolator(new LinearInterpolator());  
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()  
        {  
            // fraction = t / duration  
            @Override  
            public PointF evaluate(float fraction, PointF startValue,  
                    PointF endValue)  
            {  
                // x-200px/s £¬y-0.5 * 10 * t  
                PointF point = new PointF();  
                point.x = 200 * fraction * 3;  
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);  
                return point;  
            }  
        });  
  
        valueAnimator.start();  
        valueAnimator.addUpdateListener(new AnimatorUpdateListener()  
        {  
            @Override  
            public void onAnimationUpdate(ValueAnimator animation)  
            {  
                PointF point = (PointF) animation.getAnimatedValue();  
                view.setX(point.x);  
                view.setY(point.y);  
            }  
        }); 
      /*    --facilitate--runOnUiThread cannot run an static method
          view.animate()
                .alpha(0) 
                .y(300).setDuration(1000)  
                // need API 12  
                .withStartAction(new Runnable()  
                {  
                    @Override  
                    public void run()  
                    {     
                    	
                    }  
                    // need API 16  
                }).withEndAction(new Runnable()  
                {  
  
                    @Override  
                    public void run()  
                    {                       
                        runOnUiThread(new Runnable()  
                        {  
                            @Override  
                            public void run()  
                            {  
                                view.setY(0);  
                                view.setAlpha(1.0f);  
                            }  
                        });  
                    }  
                }).start();  
         * */
        
	}
	@SuppressLint("NewApi")
	public static void getXmlPropertyAnimation(Context context, View view){
		Animator anim = AnimatorInflater.loadAnimator(context, R.anim.anim_property_set);  
		view.setPivotX(0);  
		view.setPivotY(0);        
		view.invalidate();  
        anim.setTarget(view);  
        anim.start();  
	}
	public static void getLayoutAnimations(){
		
	}
}
