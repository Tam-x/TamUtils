package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 鍐呭鎴栬�呮暟鍊肩浉鍚�
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class EqualsToTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        if (intValue != null){
            return intValue.longValue() == Long.valueOf(content);
        }else if (floatValue != null){
            return floatValue.doubleValue() == Double.valueOf(content);
        }else{
            return stringValue.equals(content);
        }
    }
}
