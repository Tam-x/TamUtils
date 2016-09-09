package com.xingtam.thirdparty.inputcheck;

import android.view.View;
import android.widget.TextView;

/**
 * 琛ㄦ牸鏌ユ壘鍣�
 *
 * @author Yoojia.Chen (yoojia.chen@
 * @version version 2015-05-21
 * @since 2.0
 */
public class Form {

    private final View form;

    public Form(View form) {
        this.form = form;
    }

    /**
     * 鏌ユ壘琛ㄦ牸From鐨勫瓙View
     * @param viewId View ID
     * @param <T> TextView鐨勫瓙绫诲瀷
     * @return TextView鐨勫瓙绫诲瀷
     */
    public <T extends TextView> T byId(int viewId){
        return (T) form.findViewById(viewId);
    }
}
