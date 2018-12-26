package com.library.sboot.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RedissonApplication
 * @Description: Redisson分布式锁配置类
 * @author feifei.liu
 * @date 2018/12/26 14:56
 */
@Configuration
public class RedissonApplication {
    @Bean
    Redisson redissonSentinel() {
        //支持单机、主从、哨兵、集群等模式
        //此位哨兵模式
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .addSentinelAddress(new String[]{"redis://192.168.3.49:26379","redis://192.168.3.49:26479","redis://192.168.3.49:26579"})
                .setPassword("redis")
                .setDatabase(5);
        return (Redisson) Redisson.create(config);
    }
}
