package com.liuliume.common.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类 User: mayan Date: 13-3-27 Time: 上午11:19 To change this template use File | Settings | File Templates.
 */
public class RedisUtils {

    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    private static final Logger redisMissLogger = LoggerFactory.getLogger("redisMissLogger");

    private RedisTemplate redisTemplate;

    private static final String ALL_REQUEST_TIMER = "REDIES_ALL_REQUEST";

    /*
    * 设置缓存内容
    */
    public void set(String key, String value) throws Exception {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value);
        } catch (Exception e) {
            logger.error("[Cache] set cache fail, key:" + key + " value:" + value, e);
            try {
                delete(key);
            } catch (Exception ex) {
                logger.error("[Cache] set and delete cache fail, key:" + key + " value:" + value, e);
                throw e;
            }
        }
    }


    /*
     * 设置缓存内容及有效期，单位为秒
     * TODO:是否抛出异常及如何处理
     */
    public void setWithinSeconds(String key, String value, long timeout) throws Exception {
        set(key, value, timeout, TimeUnit.SECONDS);
    }

    public String get(String key) {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            String res = valueOperations.get(key);
            if (Strings.isNullOrEmpty(res)) {
                redisMissLogger.info("get cache miss, key:" + key);
            }
            return res;
        } catch (Exception e) {
            logger.error("[Cache] get cache fail, key:" + key, e);
        }
        return null;
    }


    public boolean checkKeyIsExist(String key) {
        try {
            boolean res = redisTemplate.hasKey(key);
            if (!res) {
                redisMissLogger.info("checkKeyIsExist cache miss, key:" + key);
            }
            return res;
        } catch (Exception e) {
            logger.error("[Cache] check key is exist in cache fail, key:" + key, e);
            return false;
        }
    }


    public void expire(String cacheKey, long timeout) {
        try {
            redisTemplate.expire(cacheKey, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("[Cache] set cache expire fail, key:" + cacheKey + "timeout:" + timeout, e);
        }
    }

    public void delete(String cacheKey) {
        redisTemplate.delete(cacheKey);
    }



    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /*
    * 设置缓存内容
    */
    public void set(String key, String value, long timeout, TimeUnit timeUnit) throws Exception {
        try {
            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(key, value, timeout, timeUnit);
        } catch (Exception e) {
            logger.error("[Cache] set cache fail, key:" + key + " value:" + value, e);

        }
    }


    /**
     * 获取key 的剩余时间
     *
     * @param key
     * @return
     * @throws Exception
     */
    public long getExpireTime(String key) throws Exception {
        long expireSeconds = -1;
        try {
            expireSeconds = redisTemplate.getExpire(key);
        } catch (Exception e) {
            logger.error("[Cache] getExpireTime cache fail, key:" + key, e);
        }
        return expireSeconds;
    }
}
