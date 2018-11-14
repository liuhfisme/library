# Java配置
>Java配置是Spring 4.x推荐的配置方式，可以完全替代xml配置，Java配置也是Spring Boot推荐的配置方式。

Java配置是通过@Configuration和@Bean来实现的。
- @Configuration 声明当前类是一个配置类，相当于一个Spring配置的xml文件。
- @Bean 注解在方法上，声明当前方法的返回值为一个Bean。

如何使用Java配置和注解配置呢？主要原则是：全局配置使用Java配置（如数据库相关配置、MVC相关配置），业务Bean的配置实用注解配置（
@Service、@Component、@Repository、@Controller）。

## 代码块
配置类
```java
@Configuration //声明一个配置类，此处没有用到包扫描，是因为所有的Bean都在此类中定义了
public class JavaConfig {

    @Bean //使用@Bean注解说明当前方法functionService的返回值是一个Bean，Bean的名称为方法名
    public FunctionService functionService() {
        return new FunctionService();
    }

    @Bean //通过setFunctionService方法将上面声明好的Bean注入到UseFunctionService类中
    public UseFunctionService  useFunctionService() {
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }

    /*@Bean 另外一种注入方式，直接传参数
    public UseFunctionService  useFunctionService(FunctionService functionService) {
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService);
        return useFunctionService;
    }*/

}
```