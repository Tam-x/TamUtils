package com.xingtam.thirdparty.inputcheck;

/**
 * Lazy loader
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public interface LazyLoader {

    /**
     * @return 杩斿洖鏁村瀷鏁板�笺�傚鏋滄病鏈夋暟鍊硷紝杩斿洖null銆�
     */
    Long loadInt();

    /**
     * @return 杩斿洖娴偣鍨嬫暟鍊笺�傚鏋滄病鏈夋暟鍊硷紝杩斿洖null銆�
     */
    Double loadFloat();

    /**
     * @return 杩斿洖瀛楃涓叉暟鍊笺�傚鏋滄病鏈夋暟鍊硷紝杩斿洖null銆�
     */
    String loadString();
}
