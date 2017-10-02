# SCOPE
>Scope描述的是Spring容器如何新建Bean的实例的。Spring的Scope有以下几种，通过@Scope注解来实现。

- Singleton：一个Spring容器中只有一个Bean的实例，Spring的默认配置（单例模式）。
- Prototype：每次调用新建一个Bean的实例（多例模式）。
- Request：Web项目中，给每一个http request新建一个Bean实例。
- Session：Web项目中，给米一个http session新建一个Bean实例。
- GlobalSession：这个只在portal应用中有用，给每一个global http session 新建一个Bean实例。

另外，在Spring Batch中还有一个Scope是使用@StepScope，本示例演示默认的singleton和Prototype，分别从Spring 容器中获得2次Bean，
判断Bean的实例是否相等。
## 代码块
Singleton
```java
@Service //默认为Singleton，相当于@Scope("singleton")
public class DemoSingletonService {
}
```
Prototype
```java
@Service
@Scope("prototype") //声明Scope为Prototype
public class DemoPrototypeService {
}
```
配置类
```java
@Configuration
@ComponentScan(lcom.librarypublic class ScopeConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);
        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);

        System.out.println("s1 与 s2 是否相等："+s1.equals(s2));
        System.out.println("p1 与 p2 是否相等："+p1.equals(p2));
        context.close();
    }
}
```