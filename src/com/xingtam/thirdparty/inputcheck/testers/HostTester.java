package com.xingtam.thirdparty.inputcheck.testers;

/**
 * 主机地址
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class HostTester extends AbstractTester {

    static final String HOST_REGEX = "^([a-z0-9]([a-z0-9\\-]{0,65}[a-z0-9])?\\.)+[a-z]{2,6}$";

    @Override
    public boolean test(String content) {
        // 全部转换成小写来优化正则匹配性能
        final String host = content.toLowerCase();
        return testRegex(IPv4Tester.IPV4_REGEX, host) || testRegex(HOST_REGEX, host);
    }
}
