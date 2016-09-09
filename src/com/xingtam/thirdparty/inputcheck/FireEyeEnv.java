package com.xingtam.thirdparty.inputcheck;

import android.util.Log;

/**
 * FireEye Env
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class FireEyeEnv {

    /**
     * 鏄惁寮�鍚皟璇曚俊鎭緭鍑�
     */
    private static boolean isDebug = false;

    /**
     * 鏄惁杈撳嚭鏇村璇︾粏鐨勮皟璇曚俊鎭�
     */
    private static boolean isVerbose = false;

    /**
     * 璁剧疆鏄惁寮�鍚皟璇曞姛鑳�
     */
    public static void setDebug(boolean isDebug){
        FireEyeEnv.isDebug = isDebug;
    }

    /**
     * 璁剧疆鏄惁寮�鍚鎯呰緭鍑哄姛鑳�
     */
    public static void setVerbose(boolean isVerbose){
        FireEyeEnv.isVerbose = isVerbose;
    }

    /**
     * 鍙帶鐨勮皟璇曚俊鎭緭鍑烘帴鍙�
     * @param tag Tag
     * @param message 娑堟伅鍐呭
     */
    public static void log(String tag, String message){
        if (isDebug) Log.d(tag, message);
    }

    public static void verbose(String tag, String message){
        if (isDebug && isVerbose) Log.d(tag, message);
    }
}
