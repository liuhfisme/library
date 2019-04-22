package com.mine.jenkins.ctl;

import com.mine.jenkins.model.JenkinsVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Jenkins自动构建请求类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-22
 */
@RestController
public class HelloJenkinsCtl {

    @GetMapping("/hello")
    public JenkinsVo hello() {
        JenkinsVo jenkinsVo = new JenkinsVo("feifei.liu", 28, "男");
        return jenkinsVo;
    }
}