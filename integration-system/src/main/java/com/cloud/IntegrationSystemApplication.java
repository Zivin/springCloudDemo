/**
 * @Project integration-system
 * @Package com.cloud
 * @FileName IntegrationSystemApplication.java
 */

package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author zivin
 * @Date 2018年1月3日
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
// 禁止springboot自动加载持久化bean
@EnableAutoConfiguration(exclude = { JpaRepositoriesAutoConfiguration.class })
@ServletComponentScan
public class IntegrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationSystemApplication.class, args);
    }
}
