# 组合注解与元注解
>从Spring2开始，为了响应JDK1.5推出的注解功能，Spring开始大量加入注解来替代xml配置。Spring的注解主要用来配置和注入Bean，以及AO
P相关配置（@Transactional）。随着注解的大量使用，尤其相同的多个注解用到各个类或方法中，会相当繁琐。这就是样板代码（boilerplate
code），是Spring设计原则中要消除的代码。

所谓元注解其实就是可以注解到别的注解上的注解，被注解的注解称之为组合注解（比较拗口），组合注解具备注解其上的元注解的功能。
Spring的很多注解都可以作为元注解，而且Spring本身已经有很很多组合注解，如@Configuration就是一个组合@Component注解，表明这个类
其实也是一个Bean。

## 代码块
示例组合注解
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration //组合@Configuration元注解
@ComponentScan //组合@ComponentScan元注解
public @interface WiselyConfiguration {
    String[] value() default {}; //覆盖value参数
}
```
演示服务Bean
```java
@Service
public class DemoService {
    public void outputResult() {
        System.out.println("从组合注解配置照样获得的bean");
    }
}
```
使用自定义注解配置类
```java
@WiselyConfiguration(lcom.librarypublic class DemoConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        DemoService demoService = context.getBean(DemoService.class);
        demoService.outputResult();
        context.close();
    }
}
```