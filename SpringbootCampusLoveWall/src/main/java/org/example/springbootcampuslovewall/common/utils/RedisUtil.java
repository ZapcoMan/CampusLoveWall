package org.example.springbootcampuslovewall.common.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * 提供对Redis的基本操作，包括设置键值对、检查键是否存在以及删除键
 */
@Component
public class RedisUtil {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;


    /**
     * 设置键值对到Redis中，并指定过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 检查指定的键在Redis中是否存在
     * @param key 要检查的键
     * @return 如果键存在返回true，否则返回false
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 从Redis中删除指定的键
     * @param key 要删除的键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
