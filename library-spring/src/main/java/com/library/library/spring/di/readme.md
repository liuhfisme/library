# DI (dependency injection)
>我们经常说的控制反转IOC（Inversion of Control）和依赖注入DI（dependency injection）在Spring环境下是两个不同的概念，控制反转
是通过依赖注入实现的。依赖注入指的是容器负责创建对象和维护对象间的依赖关系，而不是通过对象本身负责自己的创建和解决自己的依赖。
依赖注入的主要目的就是为了解耦。

Spring IOC容器负责创建Bean，并通过容器将功能类Bean注入到你需要的Bean中。Spring提供使用xml、注解、Java配置、groovy配置实现Bean
的创建和注入

无论是xml配置、注解配置还是java配置，都被称为配置元数据，所谓元数据即描述数据的数据。元数据本身不具备任何可执行的能力，只能通过
外界代码来对这些元数据进行解析后进行一些有意义操作。Spring容器解析这些配置元数据进行Bean初始化、配置和管理依赖。

声明Bean的注解：
- @Component组件，没有明确的角色。
- @Servcie 在业务逻辑层（service层）使用。
- @Repository 再数据访问层（dao层）使用。
- @Controller 在展现层（Spring MVC）使用。

注入Bean的注解，一般情况下通用。
- @Autowired：Spring提供的注解。
- @Inject： JSR-330 提供的注解。
- @Resource：JSR-250提供的注解。

@Autowired、@Inject、@Resource可注解在set方法或者属性上。

## 代码块
注解类
```java
@Service //使用@Service注解声明当前FunctionService类是Spring容器管理的一个Bean
public class FunctionService {
    public String sayHello(String word) {
        return "Hello "+word+" !";
    }
}

```
```java
//DI实现依赖注入使用功能类的Bean
@Service
public class UseFunctionService {
    //使用@Autowired将FunctionService的实体Bean注入到UseFunctionService中
    @Autowired
    FunctionService functionService;

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
```
配置类
```java
@Configuration //@Configuration声明当前类是一个配置类
@ComponentScan(librarydi")
//使用@ComponentScan自动扫描包下所有使用@Service、@Component、@Repository和@Controller的类，并注册为Bean
public class DiConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        //使用AnnotationConfigApplicationContext作为Spring容器，接受一个配置类作为参数
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        //获得声明配置的UseFunctionService的Bean
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("DI"));
        context.close();
    }
}
```