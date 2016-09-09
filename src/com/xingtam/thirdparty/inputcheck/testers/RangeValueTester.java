package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 数值区间
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class RangeValueTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final double minValue = minFloatValue;
        final double maxValue = maxFloatValue;
        final double value = Double.valueOf(content);
        return minValue <= value && value <= maxValue;
    }
}
