package com.eureka.invoker;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/18.
 */
@RestController
public class InvokerControllerr {
    private static final Logger log = LoggerFactory.getLogger(InvokerControllerr.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    HelloFeignClient helloFeignClient;

    @RequestMapping(value = "/router", method = RequestMethod.GET)
    public String router() {
        log.info("invoker debug, getServiceInstance start.");
        List<ServiceInstance> ins = getServiceInstances();
        log.info("invoker debug, print service status.");
        for (ServiceInstance service: ins) {
            EurekaDiscoveryClient.EurekaServiceInstance esi = (EurekaDiscoveryClient.EurekaServiceInstance) service;
            InstanceInfo info = esi.getInstanceInfo();
            log.info("invoker debug, status. appName:[{}], instanceId:[{}], status:[{}]", info.getAppName(), info.getInstanceId(), info.getStatus());
        }
        //根据应用名称调用服务
        String json = restTemplate.getForObject( "http://eureka-service-provider/hello", String.class);
        return json;
    }

    @RequestMapping(value = "/feign/{name}", method = RequestMethod.GET)
    public Object feign(@PathVariable("name") String name) {
        return helloFeignClient.hello(name);
    }

    /**
     * @Title: getServiceInstances
     * @Description: 查询可用服务
     * @Param []
     * @return java.util.List<org.springframework.cloud.client.ServiceInstance>
     * @author feifei.liu
     * @date 2018/12/28 15:53
     */
    private List<ServiceInstance> getServiceInstances() {
        List<String> ids = discoveryClient.getServices();
        List<ServiceInstance> result = new ArrayList<>();
        for (String id : ids) {
            List<ServiceInstance> ins = discoveryClient.getInstances(id);
            result.addAll(ins);
        }
        return result;
    }
}
