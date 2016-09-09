package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 鏈�灏忓��
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class MinValueTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final double minValue = floatValue;
        return minValue <= Double.valueOf(content);
    }
}
