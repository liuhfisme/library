package com.library.utils.redis.ps;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: Publisher
 * @Description: 发布者
 * @author feifei.liu
 * @date 2018/11/26 18:24
 */
public class Publisher extends Thread {
    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("mychannel", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
