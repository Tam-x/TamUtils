package com.xingtam.thirdparty.inputcheck;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

/**
 * Fire Eye
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version 2015-05-20
 * @since   2.0
 */
public class FireEye {

    private static final String TAG = FireEye.class.getSimpleName();

    private final Context mContext;
    private final List<TypeWrapper> mOrderedFields = new ArrayList();
    private final SparseArray<StaticPatternInvoker> mStaticPatterns = new SparseArray();
    private final SparseArray<ValuePatternInvoker> mValuePatterns = new SparseArray();

    private MessageDisplay mMessageDisplay;

    public FireEye(Context context, MessageDisplay display){
        mContext = context;
        mMessageDisplay = display;
    }

    public FireEye(Context context) {
        this(context, new MessageDisplay() {
            @Override
            public void dismiss(TextView input) {
                input.setError(null);
            }

            @Override
            public void show(TextView input, String message) {
                input.setError(message);
            }
        });
    }

    /**
     * 娣诲姞涓�涓渶瑕佹牎楠岀殑View锛屽苟鎸囧畾瀹冪殑闈欐�佹牎楠屾ā寮忋��
     * @param inputView TextView鍙婂叾瀛愮被
     * @param patterns 鏁板�兼牎楠屾ā寮�
     * @return FireEye
     */
    public FireEye add(TextView inputView, StaticPattern...patterns){
        enforceViewNotNull(inputView);
        enforceHasPatterns(patterns);
        final int viewKey = inputView.hashCode();
        final StaticPatternInvoker invoker = mStaticPatterns.get(viewKey);
        // 鏍￠獙閰嶇疆涓嶅瓨鍦紝鍒欏垱寤哄苟娣诲姞锛涘鏋滃凡瀛樺湪锛屽垯娣诲姞鍒板凡鏈夌殑閰嶇疆涓�
        // 鍦ㄥ垱寤烘柊鐨勮緭鍏ユ鏍￠獙閰嶇疆鏃讹紝鏍规嵁浠ｇ爜娣诲姞鍏堝悗锛屽皢鏍￠獙椤哄簭淇濆瓨涓嬫潵锛�
        if (invoker == null){
            mOrderedFields.add(new TypeWrapper(viewKey, true));
            mStaticPatterns.put(viewKey, new StaticPatternInvoker(mContext, viewKey, inputView, patterns));
        }else{
            invoker.addPatterns(patterns);
        }
        return this;
    }

    /**
     * 娣诲姞涓�涓渶瑕佹牎楠岀殑View锛屽苟鎸囧畾瀹冪殑鏁板�兼牎楠屾ā寮忋��
     * @param inputView TextView鍙婂叾瀛愮被
     * @param patterns 鏁板�兼牎楠屾ā寮�
     * @return FireEye
     */
    public FireEye add(TextView inputView, ValuePattern...patterns){
        enforceViewNotNull(inputView);
        enforceHasPatterns(patterns);
        final int viewKey = inputView.hashCode();
        final ValuePatternInvoker invoker = mValuePatterns.get(viewKey);
        // 鏍￠獙閰嶇疆涓嶅瓨鍦紝鍒欏垱寤哄苟娣诲姞锛涘鏋滃凡瀛樺湪锛屽垯娣诲姞鍒板凡鏈夌殑閰嶇疆涓�
        // 鍦ㄥ垱寤烘柊鐨勮緭鍏ユ鏍￠獙閰嶇疆鏃讹紝鏍规嵁浠ｇ爜娣诲姞鍏堝悗锛屽皢鏍￠獙椤哄簭淇濆瓨涓嬫潵锛�
        if (invoker == null){
            mOrderedFields.add(new TypeWrapper(viewKey, false));
            mValuePatterns.put(viewKey, new ValuePatternInvoker(mContext, viewKey, inputView, patterns));
        }else{
            invoker.addPatterns(patterns);
        }
        return this;
    }

    /**
     * 璁剧疆鏍￠獙涓嶉�氳繃鏃剁殑娑堟伅鏄剧ず澶勭悊鎺ュ彛
     * @param messageDisplay 娑堟伅鏄剧ず澶勭悊鎺ュ彛
     */
    public void setMessageDisplay(MessageDisplay messageDisplay){
        mMessageDisplay = messageDisplay;
    }

    /**
     * 鏍￠獙杈撳叆锛屽綋妫�娴嬪埌鏍￠獙澶辫触鏃朵腑鏂牎楠屻��
     * @return 鏍￠獙缁撴灉
     */
    public Result test(){
        return test(false);
    }

    /**
     * 鏍￠獙杈撳叆锛屽綋妫�娴嬪埌鏍￠獙澶辫触鏃朵腑鏂牎楠屻��
     * @return 鏍￠獙缁撴灉
     */
    public Result test(boolean testAll){
        // 鏍￠獙鏃讹紝鎸変繚瀛樼殑鐨勮緭鍏ユ椤哄簭鏉ユ牎楠�
        boolean testPassed = true;
        Result lastFail = null;
        for (TypeWrapper typeWrapper : mOrderedFields){
            final PatternInvoker invoker;
            if (typeWrapper.isStaticPattern){
                invoker = mStaticPatterns.get(typeWrapper.viewKey);
            }else{
                invoker = mValuePatterns.get(typeWrapper.viewKey);
            }
            final Result ret = testPattern(invoker);
            if(ret != null){
                lastFail = ret;
                testPassed = false;
            }
            if (!testPassed && !testAll){
                return ret;
            }
        }
        if (testPassed){
            return Result.passed(null);
        }else{
            return Result.reject(lastFail.message, lastFail.value);
        }
    }

    public final void dump(){
        for (TypeWrapper wrapper : mOrderedFields){
            final PatternInvoker invoker;
            if (wrapper.isStaticPattern){
                invoker = mStaticPatterns.get(wrapper.viewKey);
            }else{
                invoker = mValuePatterns.get(wrapper.viewKey);
            }
            Log.e(TAG, "[D] " + invoker.dump());
        }
    }

    /**
     * @return Null if passed, otherwise return the result
     */
    private Result testPattern(PatternInvoker invoker){
        mMessageDisplay.dismiss(invoker.input);
        final Result result = invoker.performTest();
        FireEyeEnv.verbose(TAG, "[v] Testing.item: " + invoker.dump());
        FireEyeEnv.verbose(TAG, "[v] Testing.result: " + result.toString());
        if (!result.passed){
            mMessageDisplay.show(invoker.input, result.message);
        }
        return result.passed ? null : result;
    }

    /**
     * 纭繚鏈変竴涓牎楠屾ā寮�
     * @param items 妯″紡鏉＄洰
     */
    private static void enforceHasPatterns(Object[] items){
        if (items == null || items.length == 0){
            throw new IllegalArgumentException("蹇呴』鎸囧畾鑷冲皯涓�涓牎楠屾ā寮�(Pattern required)");
        }
    }

    private static void enforceViewNotNull(TextView view){
        if (view == null) throw new IllegalArgumentException("鏍￠獙鐨刅iew涓嶈兘涓虹┖(Target view cannot be null)");
    }

    private final class TypeWrapper {

        final int viewKey;
        final boolean isStaticPattern;

        private TypeWrapper(int viewKey, boolean isStaticPattern) {
            this.viewKey = viewKey;
            this.isStaticPattern = isStaticPattern;
        }

    }

    /**
     * 鑾峰彇杈撳叆妗嗙殑瀛楃涓插唴瀹�
     * @param input 杈撳叆妗�
     * @return 瀛楃涓插唴瀹�
     */
    public static String getStringValue(TextView input){
        return String.valueOf(input.getText());
    }

    /**
     * 鑾峰彇杈撳叆妗嗙殑鏁村瀷鍊煎唴瀹�
     * @param input 杈撳叆妗�
     * @return 鏁村瀷鍊�
     */
    public static long getLongValue(TextView input){
        return Long.valueOf(getStringValue(input));
    }

    /**
     * 鑾峰彇杈撳叆妗嗙殑鏁村瀷鍊煎唴瀹�
     * @param input 杈撳叆妗�
     * @return 鏁村瀷鍊�
     */
    public static long getIntValue(TextView input){
        return Integer.valueOf(getStringValue(input));
    }

    /**
     * 鑾峰彇杈撳叆妗嗙殑娴偣鍊煎唴瀹�
     * @param input 杈撳叆妗�
     * @return 娴偣鍊�
     */
    public static double getDoubleValue(TextView input){
        return Double.valueOf(getStringValue(input));
    }

    /**
     * 鑾峰彇杈撳叆妗嗙殑娴偣鍊煎唴瀹�
     * @param input 杈撳叆妗�
     * @return 娴偣鍊�
     */
    public static float getFloatValue(TextView input){
        return Float.valueOf(getStringValue(input));
    }

}
