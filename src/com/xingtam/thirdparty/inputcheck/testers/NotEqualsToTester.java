package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 鍐呭鎴栬�呮暟鍊间笉鐩稿悓
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-06-05
 * @since   2.1
 */
public class NotEqualsToTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        if (intValue != null){
            return intValue.longValue() != Long.valueOf(content);
        }else if (floatValue != null){
            return floatValue.doubleValue() != Double.valueOf(content);
        }else{
            return !stringValue.equals(content);
        }
    }
}
