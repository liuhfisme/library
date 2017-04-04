# 条件注解@Conditional
>通过profile我们可以获得不同的Bean。Spring4提供了一个更通用的基于条件的Bean的创建，即使用@Conditional注解。

@Conditional根据满足某一个特定条件创建一个特定的Bean。比方说，当某一个jar包在一个类路径下的时候，自动配置一个或多个Bean；或者
只有某个Bean被创建才会创建另外一个Bean。总的来说，就是根据特定条件来控制Bean的创建行为，这样我们可以利用这个特性进行一些自动的
配置。

本示例将以不同的操作系统作为条件，我们将通过实现Condition接口，并重写其matches方法来构造判断条件。若在Windows系统下运行程序，
则输出列表命令为dir；若在Linux操作系统下运行程序，则输出列表命令为ls.

## 代码块
判断Windows的条件
```java
public class WindowsCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }
}
```
判断Linxu的条件
```java
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }
}
```
接口
```java
public interface ListService {
    public String showListCmd();
}
```
Windows下所要创建的Bean的类
```java
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
```
Linux下所要创建的Bean的类
```java
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
```
配置类
```java
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListService windowsListService() {
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListService linuxListService() {
        return new LinuxListService();
    }
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name")
            +"系统下的列表命令为："+listService.showListCmd());
        context.close();
    }
}
```