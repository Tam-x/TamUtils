package com.xingtam.thirdparty.inputcheck;

public class Result {

    public final boolean passed;
    public final String message;
    final String value;

    public Result(boolean passed, String message, String value){
        this.passed = passed;
        this.message = message;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ " +
                "passed: " + passed + ",\t" +
                "value: "+ String.valueOf(value) + ", " +
                "message: " + message +
                "}";
    }

    /**
     * 鏍￠獙閫氳繃
     * @param value 鏁板��
     * @return 鏍￠獙閫氳繃鐨勭粨鏋滃璞�
     */
    public static Result passed(String value){
        return new Result(true, null, value);
    }

    /**
     * 鏍￠獙澶辫触
     * @param message 澶辫触鎻愮ず娑堟伅
     * @param value 鏁板��
     * @return 鏍￠獙澶辫触缁撴灉瀵硅薄
     */
    public static Result reject(String message, String value){
        return new Result(false, message, value);
    }
}