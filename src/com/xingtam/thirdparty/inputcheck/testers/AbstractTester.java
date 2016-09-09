package com.xingtam.thirdparty.inputcheck.testers;

import java.util.regex.Pattern;

/**
 * 鏍￠獙鍣�
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public abstract class AbstractTester {

    private static final String TAG = "AbstractInvoker";

    /**
     * 鍙戠敓寮傚父鏃朵骇鐢熺殑娑堟伅
     */
    private String mExceptionMessage;

    public String getExceptionMessage() {
        return mExceptionMessage;
    }

    /**
     * 鏍￠獙杈撳叆鍐呭銆傚鏋滄牎楠岄�氳繃杩斿洖True锛屽惁鍒欒繑鍥濬alse銆�
     * @param content 鏍￠獙鍐呭
     * @return 濡傛灉鏍￠獙閫氳繃杩斿洖True锛屽惁鍒欒繑鍥濬alse銆�
     */
    public final boolean performTest(String content){
        try{
            mExceptionMessage = null;
            return test(content);
        }catch (Throwable e){
            e.printStackTrace();
            mExceptionMessage = e.getMessage();
            return false;
        }
    }

    /**
     * 鑾峰彇Tester鐨勫悕绉�
     * @return Tester鍚嶇О
     */
    public final String getName(){
        return this.getClass().getSimpleName();
    }

    /**
     * 鏍￠獙鍣ㄥ疄鐜扮被瀹炵幇鐪熸鐨勬牎楠屾柟娉�
     * @param content 鍐呭
     * @return 鏄惁鏍￠獙閫氳繃
     */
    protected abstract boolean test(String content);

    /**
     * 鏍￠獙鍐呭鏄惁鍖归厤鎸囧畾姝ｅ垯琛ㄨ揪寮�
     * @param regex 姝ｅ垯琛ㄨ揪寮�
     * @param inputValue 鍐呭
     * @return 鏄惁鍖归厤
     */
    protected static boolean testRegex(String regex, CharSequence inputValue){
        return Pattern.compile(regex).matcher(inputValue).matches();
    }
}
