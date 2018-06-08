/**
 * @Project base-common
 * @Package com.cloud.base.util
 * @FileName BaseEncryptionUtils.java
 */

package com.cloud.base.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MD5加密工具类
 * @Author zivin
 * @Date 2017年10月14日
 */
public class BaseEncryptionUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseEncryptionUtils.class);

    // 全局数组
    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    private BaseEncryptionUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static String encryptByMd5(String str, String salt) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return byteToString(md5.digest((salt + str).getBytes("utf-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("MD5加密失败：{}", e);
        }
        return null;
    }

    /**
     * 转换字节数组为16进制字串
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 返回形式为数字跟字符串
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

}
