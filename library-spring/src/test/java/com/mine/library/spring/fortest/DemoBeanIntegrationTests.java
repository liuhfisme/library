package com.mine.library.spring.fortest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuff on 2017/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class) //在JUnit环境下提供Spring TestContext Framework的功能
@ContextConfiguration(classes = {TestConfig.class}) //用来加载配置ApplicationContext，其中classes属性用来加载配置类
@ActiveProfiles("prod") //用来声明活动的profile
public class DemoBeanIntegrationTests {
    @Autowired //可以使用普通@Autowired注入Bean
    private TestBean testBean;

    @Test //测试代码，通过JUnit的Assert来校验结果是否和预期一致
    public void prodBeanShouldInject() {
        String expected = "from production profile";
        String actual = testBean.getContent();
        Assert.assertEquals(expected, actual);
    }
}
