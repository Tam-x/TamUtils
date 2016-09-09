package com.xingtam.thirdparty.inputcheck.testers;

import android.text.TextUtils;

/**
 * 涓嶆槸浠讳綍绌哄��
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class NotBlankTester extends AbstractTester {
    @Override
    public boolean test(String content) {
        final boolean empty = TextUtils.isEmpty(content);
        return ! empty && ! testRegex("^\\s*$", content);
    }
}
