package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 鏈�澶т紵
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class MaxValueTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final double maxValue = floatValue;
        return Double.valueOf(content) <= maxValue;
    }

}
