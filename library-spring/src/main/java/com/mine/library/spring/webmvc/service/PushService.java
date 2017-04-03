package com.mine.library.spring.webmvc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by liuff on 2017/4/2.
 */
@Service
public class PushService {

    private DeferredResult<String> deferredResult;

    public  DeferredResult<String> getAsynvUpdate() {
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh() {
        if(deferredResult!=null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }
}
