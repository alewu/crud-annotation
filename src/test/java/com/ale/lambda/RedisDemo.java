package com.ale.lambda;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

public class RedisDemo {

    @Test
    public void testRedisShiWu() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-data-redis.xml");
        RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        SessionCallback sessionCallback = (SessionCallback) (RedisOperations ops) -> {
            ops.multi();
            ops.boundValueOps("key1").set("value");
            String value = (String)ops.boundValueOps("key1").get();
            System.out.println("事务执行过程中，而没有被执行，所以此处采用get命令，所以value为空：" + value);
            List list = ops.exec();
            value = (String)redisTemplate.opsForValue().get("key1");
            return value;
        };
        String value = (String)redisTemplate.execute(sessionCallback);
        System.out.println(value);
    }
}
