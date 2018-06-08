/**
 * @Project turbine-server
 * @Package com.cloud
 * @FileName TurbineServerApplication.java
 */

package com.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @Author zivin
 * @Date 2017年9月20日
 */
@SpringBootApplication
@EnableTurbine
public class TurbineServerApplication {

    public static void main(String[] args) {

        new SpringApplicationBuilder(TurbineServerApplication.class).web(true).run(args);
    }
}
