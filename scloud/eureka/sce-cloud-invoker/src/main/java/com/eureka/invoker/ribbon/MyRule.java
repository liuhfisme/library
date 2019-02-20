package com.eureka.invoker.ribbon;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @ClassName: MyRule
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/12/28 17:01
 */
public class MyRule implements IRule {
    private ILoadBalancer lb;

    @Override
    public Server choose(Object key) {
        List<Server> servers = lb.getAllServers();
        System.out.println("这是自定义服务器规则类，输出服务器信息：");
        for (Server s: servers) {
            System.out.println("    "+ s.getHostPort());
        }
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {

    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }

    public ILoadBalancer getLb() {
        return lb;
    }

    public void setLb(ILoadBalancer lb) {
        this.lb = lb;
    }
}