package com.xingtam.thirdparty.inputcheck;

/**
 * 鏁板�煎尮閰嶆ā寮忋�備綔涓洪厤缃」浼犻�掞紝鍏舵暟鎹敱Config鏉ヤ繚瀛�
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public enum ValuePattern {

    Required("姝や负蹇呭～椤圭洰"),

    MaxLength("闀垮害涓嶈兘瓒呰繃{0}"),
    MinLength("闀垮害涓嶈兘灏忎簬{0}"),
    RangeLength("闀垮害蹇呴』鍦╗{0},{1}]涔嬮棿"),

    MaxValue("鏁板�间笉鑳借秴杩噞0}"),
    MinValue("鏁板�间笉鑳藉皬浜巤0}"),
    RangeValue("鏁板�煎繀椤诲湪[{0},{1}]涔嬮棿"),

    EqualsTo("蹇呴』杈撳叆鐩稿悓鍐呭"),
    NotEqualsTo("蹇呴』杈撳叆涓嶇浉鍚屽唴瀹�");

    private final String mDefMessage;
    private String mMessage;
    private int mMessageId = -1;

    private LazyLoader mLazyLoader;
    private ValueType mValueType;
    private String mMinValue;
    private String mMaxValue;
    private String mValue;

    private ValuePattern(String message){
        mDefMessage = message;
    }

    /**
     * 璁剧疆鎳掑姞杞芥帴鍙�
     * @param lazyLoader 鎳掑姞杞芥帴鍙�
     * @return ValuePattern瀹炰緥
     */
    public ValuePattern lazy(LazyLoader lazyLoader) {
        mLazyLoader = lazyLoader;
        return this;
    }

    /**
     * 璁剧疆绗竴涓弬鏁板��
     * @param first 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setFirstValue(double first){
        enforceFloatValueType();
        syncValue(first);
        return this;
    }

    /**
     * 璁剧疆绗竴涓弬鏁板��
     * @param first 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setFirstValue(long first){
        enforceIntValueType();
        syncValue(first);
        return this;
    }

    /**
     * 璁剧疆绗簩涓弬鏁板��
     * @param second 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setSecondValue(long second){
        enforceIntValueType();
        mMaxValue = String.valueOf(second);
        return this;
    }

    /**
     * 璁剧疆绗簩涓弬鏁板��
     * @param second 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setSecondValue(double second){
        enforceFloatValueType();
        mMaxValue = String.valueOf(second);
        return this;
    }

    /**
     * 璁剧疆绗竴涓弬鏁板��
     * @param value 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setValue(String value){
        syncValue(value);
        mValueType = ValueType.String;
        return this;
    }

    /**
     * 璁剧疆绗竴涓弬鏁板��
     * @param value 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setValue(long value){
        syncValue(value);
        mValueType = ValueType.Int;
        return this;
    }

    /**
     * 璁剧疆绗竴涓弬鏁板��
     * @param value 鏁板��
     * @return ValuePattern
     */
    public ValuePattern setValue(double value){
        syncValue(value);
        mValueType = ValueType.Float;
        return this;
    }

    /**
     * 璁剧疆鎻愮ず娑堟伅鍐呭
     * @param message 娑堟伅鍐呭
     */
    public ValuePattern setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 璁剧疆鎻愮ず娑堟伅鍐呭鐨勮祫婧怚D
     * @param msgId 璧勬簮ID
     */
    public ValuePattern setMessage(int msgId){
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

    LazyLoader getLazyLoader() {
        final LazyLoader loader = mLazyLoader;
        mLazyLoader = null;
        return loader;
    }

    ValueType getValueType() {
        ValueType type = mValueType;
        mValueType = null;
        return type;
    }

    String getMinValue() {
        final String value = mMinValue;
        mMinValue = null;
        return value;
    }

    String getMaxValue() {
        final String value = mMaxValue;
        mMaxValue = null;
        return value;
    }

    String getValue() {
        final String value = mValue;
        mValue = null;
        return value;
    }

    private void syncValue(Object value){
        mValue = String.valueOf(value);
        mMinValue = mValue;
    }

    private void enforceIntValueType(){
        if (mValueType == null){
            mValueType = ValueType.Int;
        }else{
            if (!ValueType.Int.equals(mValueType)) throw new IllegalArgumentException("璁剧疆鐨勬暟鍊肩被鍨嬪繀椤诲悓涓烘暣鏁�");
        }
    }

    private void enforceFloatValueType(){
        if (mValueType == null){
            mValueType = ValueType.Float;
        }else{
            if (!ValueType.Float.equals(mValueType)) throw new IllegalArgumentException("璁剧疆鐨勬暟鍊肩被鍨嬪繀椤诲悓涓烘诞鐐规暟");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name() + '\'' +
                ", messageId=" + mMessageId +
                ", message='" + mMessage + '\'' +
                ", lazyLoader=" + mLazyLoader +
                ", valueType=" + mValueType +
                ", minValue='" + mMinValue + '\'' +
                ", maxValue='" + mMaxValue + '\'' +
                ", value='" + mValue + '\'' +
                '}';
    }
}
