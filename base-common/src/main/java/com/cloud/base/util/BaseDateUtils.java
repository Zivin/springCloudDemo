/**
 * @Project base-common
 * @Package com.cloud.base.util
 * @FileName DateUtils.java
 */

package com.cloud.base.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @Author zivin
 * @Date 2017年9月20日
 */
public class BaseDateUtils extends DateUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseDateUtils.class);

    /**
     * PATTERN 完整日期格式
     */
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 转换日期
     * @param str 日期字符串
     * @param parsePatterns 转换格式
     * @return
     */
    public static Date parseDate(final String str, final String... parsePatterns) {
        if (parsePatterns == null) {
            return parseDate(str, PATTERN);
        }
        try {
            return parseDate(str, null, parsePatterns);
        } catch (ParseException e) {
            logger.error("转换日期错误：", e);
        }
        return null;
    }
}
