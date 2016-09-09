package com.xingtam.thirdparty.inputcheck;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.xingtam.thirdparty.inputcheck.testers.AbstractTester;
import com.xingtam.thirdparty.inputcheck.testers.BankCardTester;
import com.xingtam.thirdparty.inputcheck.testers.DigitsTester;
import com.xingtam.thirdparty.inputcheck.testers.EmailTester;
import com.xingtam.thirdparty.inputcheck.testers.HostTester;
import com.xingtam.thirdparty.inputcheck.testers.IDCardTester;
import com.xingtam.thirdparty.inputcheck.testers.IPv4Tester;
import com.xingtam.thirdparty.inputcheck.testers.MobileTester;
import com.xingtam.thirdparty.inputcheck.testers.NotBlankTester;
import com.xingtam.thirdparty.inputcheck.testers.NumericTester;
import com.xingtam.thirdparty.inputcheck.testers.RequiredTester;
import com.xingtam.thirdparty.inputcheck.testers.URLTester;
import com.xingtam.thirdparty.inputcheck.testers.VINTester;
import com.xingtam.thirdparty.inputcheck.testers.VehicleNumberTester;

/**
 * 杈撳叆妗嗙殑鍖归厤妯″紡
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.3
 */
final class StaticPatternInvoker extends PatternInvoker<StaticPatternMeta, StaticPattern> {

    private static final String TAG = "StaticInvoker";

    StaticPatternInvoker(Context context, int viewId, TextView field, StaticPattern[] patterns) {
        super(context, viewId, field);
        addPatterns(patterns);
    }

    @Override
    public Result performTest(){
        final String value = input.getText().toString();
        // 褰撹緭鍏ュ唴瀹逛负绌烘椂锛屽鏋滅涓�涓牎楠屾ā寮忎笉鏄�淩equired鈥濓紝鍒欒烦杩囧悗闈㈢殑閰嶇疆銆�
        // 鍦‵ireEye鍏ュ彛鏃讹紝宸茬粡纭繚patterns鑷冲皯鍖呭惈涓�涓狿attern閰嶇疆銆�
        if (TextUtils.isEmpty(value) && !StaticPattern.Required.equals(patterns.get(0).pattern)){
            return Result.passed(null);
        }
        final String inputKey = input.getClass().getSimpleName() + "@{" + input.getHint() + "}";
        for (StaticPatternMeta meta : patterns){
            final AbstractTester tester = findTester(meta);
            FireEyeEnv.verbose(TAG, "[v] Testing.meta: " + inputKey + "value: " + value + ", tester: " + tester.getName());
            final boolean passed = tester.performTest(value);
            if (!passed){
                return Result.reject(meta.message, value);
            }
        }
        FireEyeEnv.log(TAG, "[D] " + inputKey + " -> passed: YES, value: " + value);
        return Result.passed(value);
    }

    @Override
    protected boolean onFilter(StaticPatternMeta meta, StaticPattern item) {
        if (StaticPattern.Required.equals(item)){
            this.patterns.add(0, meta);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected StaticPatternMeta convert(StaticPattern item) {
        final StaticPatternMeta meta = StaticPatternMeta.parse(item);
        meta.convertMessage(context);
        FireEyeEnv.verbose(TAG, "[v] Static pattern meta -> " + meta.toString());
        return meta;
    }

    private AbstractTester findTester(StaticPatternMeta meta){
        switch (meta.pattern){
            case BankCard: return new BankCardTester();
            case Digits: return new DigitsTester();
            case Email: return new EmailTester();
            case Host: return new HostTester();
            case IDCard: return new IDCardTester();
            case IPv4: return new IPv4Tester();
            case Mobile: return new MobileTester();
            case NotBlank: return new NotBlankTester();
            case Numeric: return new NumericTester();
            case Required: return new RequiredTester();
            case URL: return new URLTester();
            case VehicleNumber: return new VehicleNumberTester();
            case VIN: return new VINTester();
            default: return new AbstractTester() {
                @Override
                public boolean test(String content) {
                    return false;
                }
            };
        }
    }
}
