# 事件（Application Event）
>Spring的事件（Application Event）为Bean与Bean之间的消息通信提供了支持。当一个Bean处理完一个任务之后，希望另外一个Bean知道并
能够做出相应的处理，这时我们就需要让另外一个Bean监听当前Bean所发送的事件。

Spring的事件需要遵循如下流程：
- 自定义事件，集成ApplicationEvent。
- 定义事件监听器，实现ApplicationListener。
- 使用容器发布事件。

## 代码块
自定义事件
```java
public class DemoEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;
    private String msg;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```
事件监听器
```java
@Component
//实现ApplicationListener接口，并指定监听的事件类型。
public class DemoListener implements ApplicationListener<DemoEvent> {
    /**
     * 使用onApplicationEvent方法对消息进行接受处理。
     * @param event
     */
    public void onApplicationEvent(DemoEvent event) {
        String msg = event.getMsg();
        System.out.println("我（bean-demoListener）接收到了bean-demoPublistener发布的消息："+msg);
    }
}
```
事件发布类
```java
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext; //注入ApplicationContext用来发布事件。

    /**
     * 使用ApplicationContext的publishEvent方法来发布
     * @param msg
     */
    public void publish(String msg) {
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("Hello Application Event!");
        context.close();
    }
}
```