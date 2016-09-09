package com.xingtam.thirdparty.inputcheck;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杈撳叆妗嗙殑鍖归厤妯″紡
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.3
 */
abstract class PatternInvoker<Patt, Meta> {

    protected final List<Patt> patterns = new ArrayList();
    protected final Context context;

    public final TextView input;
    public final int viewId;

    protected PatternInvoker(Context context, int viewId, TextView input) {
        this.context = context;
        this.input = input;
        this.viewId = viewId;
    }

    /**
     * 娣诲姞鍖归厤妯″紡鍒楄〃
     * @param patterns 鍖归厤妯″紡鍒楄〃
     */
    public final void addPatterns(Meta[] patterns){
        for (Meta item : Arrays.asList(patterns)){
            final Patt pattern = convert(item);
            if (!onFilter(pattern, item)){
                this.patterns.add(pattern);
            }
        }
    }

    public final String dump(){
        StringBuilder buf = new StringBuilder(input.toString());
        buf.append('@').append(input.getHint()).append(':').append(input.getText());
        buf.append("\n -> patterns:\n");
        for (Patt pattern : patterns){
            buf.append("\t").append(pattern.toString()).append(" ,\n");
        }
        return buf.toString();
    }

    /**
     * 鏍￠獙杈撳叆
     * @return 鏍￠獙缁撴灉
     */
    public abstract Result performTest();

    /**
     * 閰嶇疆椤硅繃婊�
     * @param pattern 杞崲鍚庣殑鍌ㄥ瓨椤�
     * @param item 閰嶇疆椤圭洰
     * @return 濡傛灉閰嶇疆椤规墜鍔ㄦ帓搴忥紝鍒欒繑鍥濼rue銆傚惁鍒欒繑鍥濬alse銆�
     */
    protected abstract boolean onFilter(Patt pattern, Meta item);

    /**
     * 灏嗛厤缃」 Meta 杞崲鎴愬偍瀛橀」 P
     */
    protected abstract Patt convert(Meta item);
}
