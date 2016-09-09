package com.xingtam.thirdparty.inputcheck;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.xingtam.thirdparty.inputcheck.testers.AbstractValuesTester;
import com.xingtam.thirdparty.inputcheck.testers.EqualsToTester;
import com.xingtam.thirdparty.inputcheck.testers.MaxLengthTester;
import com.xingtam.thirdparty.inputcheck.testers.MaxValueTester;
import com.xingtam.thirdparty.inputcheck.testers.MinLengthTester;
import com.xingtam.thirdparty.inputcheck.testers.MinValueTester;
import com.xingtam.thirdparty.inputcheck.testers.NotEqualsToTester;
import com.xingtam.thirdparty.inputcheck.testers.RangeLengthTester;
import com.xingtam.thirdparty.inputcheck.testers.RangeValueTester;
import com.xingtam.thirdparty.inputcheck.testers.RequiredValueTester;

/**
 * 杈撳叆妗嗙殑鍖归厤妯″紡
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.3
 */
final class ValuePatternInvoker extends PatternInvoker<ValuePatternMeta, ValuePattern> {

    private static final String TAG = "ValueInvoker";

    ValuePatternInvoker(Context context, int viewId, TextView field, ValuePattern... patterns) {
        super(context, viewId, field);
        addPatterns(patterns);
    }

    @Override
    public Result performTest(){
        final String value = input.getText().toString();
        // 褰撹緭鍏ュ唴瀹逛负绌烘椂锛屽鏋滅涓�涓牎楠屾ā寮忎笉鏄�淩equired鈥濓紝鍒欒烦杩囧悗闈㈢殑閰嶇疆銆�
        final ValuePatternMeta first = patterns.get(0);
        if (TextUtils.isEmpty(value) && !ValuePattern.Required.equals(first.pattern)){
            return Result.passed(null);
        }
        final String inputKey = input.getClass().getSimpleName() + "@{" + input.getHint() + "}";
        for (ValuePatternMeta meta : patterns){
            meta.performLazyLoader();
            final AbstractValuesTester tester = findTester(meta);
            final boolean passed = tester.performTest(value);
            if (!passed){
                FireEyeEnv.verbose(TAG,
                        "[v] Performing: passed: NO, " + inputKey + "value: " + value + ", message: " + meta.message + ", tester: " + tester.getName());
                // 濡傛灉鏍￠獙鍣ㄥ彂鐢熷紓甯革紝鍙栧紓甯告秷鎭繑鍥�
                String message = tester.getExceptionMessage();
                if (message == null) message = meta.getMessage();
                return Result.reject(message, value);
            } else{
                FireEyeEnv.verbose(TAG,
                        "[v] Performing: passed: YES, " + inputKey + "value: " + value + ", message: " + meta.message + ", tester: " + tester.getName());
            }
        }
        FireEyeEnv.log(TAG, "[D] " + inputKey + " -> passed: YES, value: " + value);
        return Result.passed(value);
    }

    @Override
    protected boolean onFilter(ValuePatternMeta meta, ValuePattern item) {
        if (ValuePattern.Required.equals(item)){
            this.patterns.add(0, meta);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected ValuePatternMeta convert(ValuePattern item) {
        final ValuePatternMeta meta = ValuePatternMeta.parse(item);
        meta.convertMessage(context);
        FireEyeEnv.log(TAG, "[D] Value pattern meta -> " + meta.toString());
        return meta;
    }

    private AbstractValuesTester findTester(ValuePatternMeta meta){
        switch (meta.pattern){
            case EqualsTo:
                EqualsToTester equalsToTester = new EqualsToTester();
                switch (meta.valueType){
                    case Float:
                        equalsToTester.setFloatValue(Double.valueOf(meta.value));
                        break;
                    case Int:
                        equalsToTester.setIntValue(Long.valueOf(meta.value));
                        break;
                    case String:
                        equalsToTester.setStringValue(meta.value);
                        break;
                }
                return equalsToTester;
            case NotEqualsTo:
                NotEqualsToTester notEqualsToTester = new NotEqualsToTester();
                switch (meta.valueType){
                    case Float:
                        notEqualsToTester.setFloatValue(Double.valueOf(meta.value));
                        break;
                    case Int:
                        notEqualsToTester.setIntValue(Long.valueOf(meta.value));
                        break;
                    case String:
                        notEqualsToTester.setStringValue(meta.value);
                        break;
                }
                return notEqualsToTester;
            case MinLength:
                MinLengthTester minLengthTester = new MinLengthTester();
                minLengthTester.setIntValue(Long.parseLong(meta.value));
                return minLengthTester;
            case MaxLength:
                MaxLengthTester maxLengthTester = new MaxLengthTester();
                maxLengthTester.setIntValue(Long.parseLong(meta.value));
                return maxLengthTester;
            case MinValue:
                MinValueTester minValueTester = new MinValueTester();
                minValueTester.setFloatValue(Double.valueOf(meta.value));
                return minValueTester;
            case MaxValue:
                MaxValueTester maxValueTester = new MaxValueTester();
                maxValueTester.setFloatValue(Double.valueOf(meta.value));
                return maxValueTester;
            case RangeLength:
                RangeLengthTester rangeLengthTester = new RangeLengthTester();
                rangeLengthTester.setMinIntValue(Long.parseLong(meta.minValue));
                rangeLengthTester.setMaxIntValue(Long.parseLong(meta.maxValue));
                return rangeLengthTester;
            case RangeValue:
                RangeValueTester rangeValueTester = new RangeValueTester();
                rangeValueTester.setMinFloatValue(Double.valueOf(meta.minValue));
                rangeValueTester.setMaxFloatValue(Double.valueOf(meta.maxValue));
                return rangeValueTester;
            case Required:
                return new RequiredValueTester();
            default:
                return new RequiredValueTester(){
                    @Override
                    public boolean test(String content) {
                        return false;
                    }
                };
        }
    }

}
