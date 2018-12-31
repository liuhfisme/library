package com.eureka.invoker.ribbon;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @ClassName: MyPing
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 17:04
 */
public class MyPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        System.out.println("自定义Ping类，服务器信息："+server.getHostPort());
        return true;
    }
}
