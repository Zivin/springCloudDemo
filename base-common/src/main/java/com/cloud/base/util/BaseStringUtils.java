/**
 * @Project base-common
 * @Package com.cloud.base.util
 * @FileName StringUtils.java
 */

package com.cloud.base.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 * @Author zivin
 * @Date 2017年9月20日
 */
public class BaseStringUtils extends StringUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseStringUtils.class);

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取固定位数的编码字符串
     * @param num
     * @param digit
     * @return
     */
    public static String getCode(int num, int digit) {
        StringBuilder code = new StringBuilder(String.valueOf(num));
        if (code.length() > digit) {
            return code.substring(code.length() - digit);
        } else {
            for (int i = code.length(); i < digit; i++) {
                code.insert(0, 0);
            }
            return code.toString();
        }
    }

    /**
     * 对编码加一处理
     * @param code 编码
     * @param digit 位数
     * @return
     */
    public static String plusOne(String code, int digit) {
        int num = Integer.parseInt(code);
        num++;
        return getCode(num, digit);
    }

    /**
     * 判断字符串是否包含大写字母
     * @param string
     * @return
     */
    public static boolean containsUpperCase(String string) {
        if (StringUtils.isBlank(string)) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否包含小写字母
     * @param string
     * @return
     */
    public static boolean containsLowerCase(String string) {
        if (StringUtils.isBlank(string)) {
            return false;
        }
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isLowerCase(c)) {
                return true;
            }
        }
        return false;
    }
}
