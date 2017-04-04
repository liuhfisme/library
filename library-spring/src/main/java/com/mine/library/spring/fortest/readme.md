# 测试
>测试是开发工作中不可缺少的部分。单元测试只针对当前开发的类和方法进行测试，可以简单通过模拟依赖来实现，对运行环境没有依赖。但是
仅仅进行单元测试是不够的，它只能验证当前类或方法能否正常工作，而我们想要知道系统的各个部分组合在一起是否能正常工作，这就是集成
测试存在的意义。

集成测试一般需要来自不同层的不同对象的交互，如数据库、网络连接、IOC容器等。其实我们也经常通过运行程序，然后通过自己操作来完成类
似于集成测试的流程。集成测试为我们提供了一种无须部署或运行程序来完成验证系统各部分是否能正常协同工作的能力。

Spring通过Spring TestContext Framework对集成测试提供顶级支持。它不依赖于特定的测试框架，既可使用Junit，也可使用TestNG。

Spring提供了一个SpringJUnit4ClassRunner类，它提供了Spring TestContext Framework的功能。通过@ContextConfiguration来配置

Application Context，通过@ActiveProfiles确定活动的profile。

## 代码块
准备
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>4.3.6.RELEASE</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```
业务代码
```java
public class TestBean {
    private String content;
    public  TestBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
```
配置类
```java
@Configuration
public class TestConfig {
    @Bean
    @Profile("dev")
    public TestBean devTestBean() {
        return new TestBean("from development profile");
    }

    @Bean
    @Profile("prod")
    public TestBean prodTestBean() {
        return new TestBean("from production profile");
    }
}
```
运行测试
```java
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
```