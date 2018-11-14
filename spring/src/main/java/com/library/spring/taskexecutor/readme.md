# 多线程
>Spring通过任务执行器（TaskExecutor）来实现多线程和并发编程。使用ThreadPoolTaskExecutor可实现一个基于线程池的TaskExecutor。
而实际开发任务一般是非阻碍的，即异步的，所以我们要在配置类中通过@EnableAsync开启对异步任务的支持，并通过在实际执行的Bean的方法
中使用@Async注解来声明其是一个异步任务。

## 代码块
任务执行类
```java
@Service
public class AsyncTaskService {
    @Async //表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务："+i);
    }
    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1："+i);
    }
}
```
配置类
```java
@Configuration
@ComponentScan(libraryor")
@EnableAsync //开启异步任务支持
public class TaskExecutorConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i=0; i<10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}
```