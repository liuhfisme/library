# Profile
>Profile为在不同环境下使用不同的配置提供了支持（开发环境下的配置和生产环境下的配置，例如，数据库的位置）。

- 通过设定Environment和ActiveProfiles来设定当前context需要使用的配置环境。再开发中使用@Profile注解类或者方法，达到在不同环境
下选择实例化不同的Bean。
- 通过设定jvm的spring.profiles.active参数来设置配置环境。
- Web项目设置在Servlet的context parameter中。

Servlet2.5及以下：
```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>spring.profiles.active</param-name>
        <param-value>production</param-value>
</init-param>
</servlet>
```
Servlet3.0及以上
```java
public class WebInit implements WebApplicationInitializer {
    @Override
    public void onStartup(ervletContext container) throws ServletException {
        container.setInitParameter("spring.profiles.default", "dev");
    }
}
```
## 代码块
示例Bean
```java
public class DemoBean {
    private String content;

    public DemoBean(String content){
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
Profile配置
```java
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev") //使用@Profile实例化dev环境下的Bean
    public DemoBean devDemoBean() {
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod") //使用@Profile实例化prod环境下的Bean
    public DemoBean prodDemoBean() {
        return new DemoBean("from production profile");
    }
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod"); // 先将活动的Profile设置为prod
        context.register(ProfileConfig.class); //后置注册Bean配置类，不然会报Bean未定义的错误
        context.refresh(); //刷新容器

        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());

        /*context.getEnvironment().setActiveProfiles("dev"); // 先将活动的Profile设置为dev
        context.register(ProfileConfig.class); //后置注册Bean配置类，不然会报Bean未定义的错误
        context.refresh(); //刷新容器
        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());*/
        context.close();
    }
}
```