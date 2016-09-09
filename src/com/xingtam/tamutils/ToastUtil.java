package com.xingtam.tamutils;

import android.content.Context;
import android.widget.Toast;

/**
 * For toast.
 * 
 * */
public class ToastUtil {
	private static Toast mToast;
    public static void showShortToast(Context context,String content) {
    	if( null != context && null != content){
	        if (mToast == null) {
	            mToast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
	        } else {
	            mToast.setText(content);
	        }
	        mToast.show();
    	}
    }
    public static void showLongToast(Context context,String content) {
    	if( null != context && null != content){
    		if (mToast == null) {	
                mToast = Toast.makeText(context,content,Toast.LENGTH_LONG);
            } else {
                mToast.setText(content);
            }
            mToast.show();  
        }
    }
    public static void showCustomToast(Context context,String content,int timeLength) {
    	if( null != context && null != content && ( timeLength > 0 ) ){
    		 if (mToast == null) {
    	            mToast = Toast.makeText(context,content,timeLength);
    	        } else {
    	            mToast.setText(content);
    	        }
    	        mToast.show();
        }
       
    }
    public static void cancelToast( ){
        if( null != mToast ){
            mToast.cancel( );
        }
    }

}
