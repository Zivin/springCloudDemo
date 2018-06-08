/**
 * @Project base-common
 * @Package com.cloud.base.util
 * @FileName RedisUtils.java
 */

package com.cloud.base.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cloud.base.constant.WebConstant;
import com.cloud.base.enumeration.AccountType;

/**
 * redis工具类
 * @Author zivin
 * @Date 2017年10月15日
 */
@Component
public class BaseRedisUtils {

    private static Logger logger = LoggerFactory.getLogger(BaseRedisUtils.class);

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置登录用户的缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setAdmin(final String key, Object value) {
        return this.set(generateKey(AccountType.ADMIN.getCode(), key), value, WebConstant.EXPIRATION_TIME);
    }

    /**
     * 获取登录用户的缓存
     * @param key
     * @return
     */
    public Object getAdmin(final String key) {
        return this.get(generateKey(AccountType.ADMIN.getCode(), key));
    }

    /**
     * 删除登录用户缓存
     * @param key
     */
    public void removeAdmin(final String key) {
        this.remove(generateKey(AccountType.ADMIN.getCode(), key));
    }

    /**
     * 设置登录用户的权限
     * @param key
     * @param value
     * @return
     */
    public boolean setMenu(final String key, Object value) {
        return this.set(generateKey(AccountType.ADMIN.getCode(), "menu", key), value, WebConstant.EXPIRATION_TIME);
    }

    /**
     * 获取登录用户的权限
     * @param key
     * @return
     */
    public Object getMenu(final String key) {
        return this.get(generateKey(AccountType.ADMIN.getCode(), "menu", key));
    }

    /**
     * 删除登录用户权限
     * @param key
     */
    public void removeMenu(final String key) {
        this.remove(generateKey(AccountType.ADMIN.getCode(), "menu", key));
    }

    /**
     * 设置登录用户的缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setUser(final String key, Object value) {
        return this.set(generateKey(AccountType.USER.getCode(), key), value, WebConstant.EXPIRATION_TIME);
    }

    /**
     * 获取登录用户的缓存
     * @param key
     * @return
     */
    public Object getUser(final String key) {
        return this.get(generateKey(AccountType.USER.getCode(), key));
    }

    /**
     * 删除登录用户缓存
     * @param key
     */
    public void removeUser(final String key) {
        this.remove(generateKey(AccountType.USER.getCode(), key));
    }

    /**
     * 构建缓存的key
     * @param keys
     * @return
     */
    public static String generateKey(String... keys) {
        StringBuilder stringBuilder = new StringBuilder();
        if (keys.length > 0) {
            stringBuilder.append(keys[0]);
            for (int i = 1; i < keys.length; i++) {
                String key = keys[i];
                stringBuilder.append(":").append(key);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     * @param pattern
     */
    @SuppressWarnings("unchecked")
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys))
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error("Redis写入缓存错误：", e);
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, expireTime, TimeUnit.MILLISECONDS);
            result = true;
        } catch (Exception e) {
            logger.error("Redis写入缓存错误：", e);
        }
        return result;
    }
}
