package com.library.utils.redis.ps;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: PubSubDemo
 * @Description: 测试类
 * @author feifei.liu
 * @date 2018/11/26 18:38
 */
public class PubSubDemo {
    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.3.49", 6379, 2000, "redis", 1);
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "192.168.3.49", 6379));

        SubThread subThread = new SubThread(jedisPool); //订阅者
        subThread.start();

//        Publisher publisher = new Publisher(jedisPool); //发布者
//        publisher.start();
    }
}
