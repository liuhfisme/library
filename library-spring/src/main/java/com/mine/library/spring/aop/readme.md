# AOP
>AOP定义为面向切面编程，相对于OOP面向对象编程。AOP可以让一组类共享相同的行为，在OOP中只能通过继承类和实现接口，来使代码的耦合度
增强，且类继承只能为继承，阻碍了更多行为添加到一组类上，AOP的存在目的是为了解耦，并且AOP弥补了OOP的不足。

Spring支持@Aspect的注解式切面编程
- 使用@Aspect声明一个切面。
- 使用@After、@Before、@Around定义建言（advice），可直接将拦截规则（切点）作为参数。
- @After、@Before、@Around参数的拦截规则为切点（PointCut），为了代码可复用，可以使用@PointCut定义一个拦截规则，然后在@After、
@Before、@Around的参数中调用。
- 符合条件的每一个被拦截处为拦截点（JointPoint）。

本示例将演示基于注解拦截和基于方法规则拦截两种方式，模拟记录操作的日志系统的实现。注解式拦截能够更好地控制要拦截的粒度和获得更丰
富的信息，Spring本身在事务处理（@Transcational）和数据缓存（@Cacheable等）上面都使用此种形式的拦截。

## 代码块
使用注解被拦截类
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
```
```java
@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void add(){}
}
```
方使用法规则被拦截类
```java
@Service
public class DemoMethodService {
    public void add(){}
}
```
编写切面
```java
@Aspect //声明一个切面
@Component //注册成Spring容器管理的Bean
public class LogAspect {

    /**
     * 通过@Pointcut声明切点
     */
    @Pointcut("@annotation(com.mine.library.spring.aop.Action)")
    public void annotationPointCut(){}

    /**
     *通过@After注解声明一个建言，并使用@Pointcut定义的切点
     * @param joinPoint
     */
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截，"+action.name());
    }

    /**
     * 通过@Before注解声明一个建言，使用拦截规则作为参数
     * @param joinPoint
     */
    @Before("execution(* com.mine.library.spring.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截，"+method.getName());
    }
}
```
配置类
```java
@Configuration
@ComponentScan("com.mine.library.spring.aop")
@EnableAspectJAutoProxy // 注解开启Spring对AspectJ的支持
public class AopConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        context.close();
    }
}
```