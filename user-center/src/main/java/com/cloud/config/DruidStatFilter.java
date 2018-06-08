/**
 * @Project user-center
 * @Package com.cloud.config
 * @FileName DruidStatFilter.java
 */

package com.cloud.config;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @Author zivin
 * @Date 2017年10月9日
 */
@WebFilter(filterName = "druidStatFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter {

}
