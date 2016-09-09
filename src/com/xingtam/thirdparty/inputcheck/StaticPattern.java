package com.xingtam.thirdparty.inputcheck;

/**
 * 闈欐�佸尮閰嶆ā寮�
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-20
 * @since 2.0
 */
public enum StaticPattern {

    Required("姝や负蹇呭～椤圭洰"),
    NotBlank("涓嶈兘鏄换浣曠┖鍊�"),
    Digits("鍙兘杈撳叆鏁板瓧"),
    Email("閭鍦板潃閿欒"),
    Host("鍩熷悕鍦板潃閿欒"),
    URL("缃戠粶鍦板潃閿欒"),
    IPv4("IP鍦板潃閿欒"),
    Numeric("鍙兘杈撳叆鏁板��"),
    BankCard("閾惰鍗�/淇＄敤鍗″彿鐮侀敊璇�"),
    IDCard("韬唤璇佸彿閿欒"),
    Mobile("鎵嬫満鍙烽敊璇�"),
    VehicleNumber("杞︾墝鍙烽敊璇�"),
    VIN("杞︽灦鍙烽敊璇�");

    private final String mDefMessage;

    private String mMessage;
    private int mMessageId = -1;

    private StaticPattern(String message){
        mDefMessage = message;
    }

    /**
     * 璁剧疆鎻愮ず娑堟伅鍐呭
     * @param message 娑堟伅鍐呭
     */
    public StaticPattern setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 璁剧疆鎻愮ず娑堟伅鍐呭鐨勮祫婧怚D
     * @param msgId 璧勬簮ID
     */
    public StaticPattern setMessage(int msgId){
        mMessageId = msgId;
        return this;
    }

    String getMessage() {
        final String msg = mMessage == null ? mDefMessage : mMessage;
        mMessage = null;
        return msg;
    }

    int getMessageId() {
        final int msg = mMessageId <= 0 ? -1 : mMessageId;
        mMessageId = -1;
        return msg;
    }

    @Override
    public String toString() {
        return " {" +
                "name='" + name() + '\'' +
                ", message='" + mMessage + '\'' +
                ", messageId=" + mMessageId +
                '}';
    }
}
