/**
 * @Project base-common
 * @Package com.cloud.base.util
 * @FileName BaseHttpUtils.java
 */

package com.cloud.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网络请求工具类
 * @Author zivin
 * @Date 2018年1月4日
 */
public class BaseHttpUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseHttpUtils.class);

    private BaseHttpUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 发送get请求
     * @param url 请求url
     * @param params 请求参数
     * @return
     */
    public static String get(String url, Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            StringBuilder urlNameString = new StringBuilder(url + "?");
            if (params != null && params.size() > 0) {
                for (Iterator<Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
                    Entry<String, String> type = iterator.next();
                    urlNameString.append(type.getKey() + "=" + type.getValue() + "&");
                }
            }
            URL realUrl = new URL(urlNameString.toString());
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("发送GET请求出现异常:", e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("关闭BufferedReader出现异常:", ex);
            }
        }
        return result.toString();
    }

    public static String post(String url, Map<String, String> param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            logger.error("发送POST请求出现异常:", e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("关闭PrintWriter/BufferedReader出现异常:", ex);
            }
        }
        return result.toString();
    }

}
