# 计划任务
>从Spring3.1开始，计划任务在Spring中的实现变得非常的简单。首先通过在配置类注解@EnableScheduling来开启对计划任务的支持，然后在
要执行计划任务的方法上注解@Scheduled，声明这个是一个计划任务。

Spring通过@Scheduled支持多种类型的计划任务，包含crom、fixDelay、fixRate等。

## 代码块
计划任务执行类
```java
@Service
public class ScheduledTaskService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000) // 使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime() {
        System.out.println("每隔五秒执行一次 "+dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 39 23 ? * *") //使用cron属性可按照指定时间执行，本例指的是每天23点39分执行
    public void fixTimeExecution() {
        System.out.println("在指定时间 "+dateFormat.format(new Date())+"执行");
    }
}
```
配置类
```java
@Configuration
@ComponentScan(library的支持
public class TaskSchedulerConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }
}
```