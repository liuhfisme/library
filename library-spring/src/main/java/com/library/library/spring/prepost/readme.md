# Bean的初始化和销毁
>在我们实际开发的时候，经常会遇到在Bean使用之前或者之后做些必要的操作，Spring对Bean的生命周期操作提供了支持。

Spring在使用Java配置和注解配置下提供如下两种方式：
- Java配置方式：使用@Bean的initMethod和destroyMethod（相当于xml配置的init-method和destroy-method）。
- 注解方式：利用JSR-250的@PostConstruct和@PreDestroy。

## 代码块
增加JSR250支持
```xml
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>jsr250-api</artifactId>
    <version>1.0</version>
</dependency>
```
使用@Bean形式的Bean
```java
public class BeanWayService {

    public void init() { //相当于xml配置的init-method
        System.out.println("@Bean-init-method");
    }

    public BeanWayService(){
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destroy() { //相当于xml配置的init-destroy
        System.out.println("@Bean-destroy-method");
    }
}
```
使用JSR250形式的Bean
```java
public class JSR250WaryService {

    @PostConstruct //在构造函数执行完之后执行
    public void init() {
        System.out.println("jsr250-init-method");
    }

    public JSR250WaryService(){
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }

    @PreDestroy //在Bean销毁之前执行
    public void destroy() {
        System.out.println("jsr250-destroy-method");
    }
}
```
配置类
```java
@Configuration
@ComponentScan(libraryst")
public class PrePostConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy") //指定BeanWayService类的init和destroy方法在构造之后、Bean销毁之前执行
    BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    JSR250WaryService jsr250WaryService() {
        return new JSR250WaryService();
    }
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WaryService jsr250WaryService = context.getBean(JSR250WaryService.class);
        context.close();
    }
}
```