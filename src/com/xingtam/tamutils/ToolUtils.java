package com.xingtam.tamutils;

public class ToolUtils {
	
	private ToolUtils() {

	}	
	/*
	 * To prevent repeat click on the button
	 * */
	private static long mLastClickTime = 0;
	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - mLastClickTime;
		if (0 < timeD && timeD < 1000) {
			return true;
		}
		mLastClickTime = time;
		return false;
	}

}
