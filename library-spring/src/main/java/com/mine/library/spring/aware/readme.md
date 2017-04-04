# Aware
>Spring 的依赖注入的最大亮点就是你所有的Bean对Spring容器的存在是没有意识的。即你可以将你的容器替换成别的容器，如Google Guice，
这时Bean之间的耦合度很低。

在实际项目中，你不可避免的要用到Spring容器本身的功能资源，这时你的Bean必须要意识到Spring容器的存在，才能调用Spring所提供的资源
，这就是所谓的Spring Aware。其实Spring Aware本身就是Spring设计用来框架内部使用的，若使用了Spring Aware，你的Bean将会和Spring
框架耦合。

Spring 提供的Aware接口：
- BeanNameAware：获得到容器中Bean的名称。
- BeanFactoryAware：获得当前bean factory，这样可以调用容器的服务。
- ApplicationContextAware：当前的application context，这样可以调用容器的服务。
- MessageSourceAware：获得message source，这样可以获得文本信息。
- ApplicationEventPublisherAware：应用事件发布器，可以发布事件。
- ResourceLoaderAware：获得资源加载器，可以获得外部资源文件。

Spring Aware的目的是为了让Bean获得Spring容器的服务。因为ApplicationContext接口集成了MessageSource接口、ApplicationEventPublisher
接口和ResourceLoader接口，所以Bean继承ApplicationContextAware可以获得Spring容器的所有服务，但原则上我们还是用到什么接口，就
实现什么接口。

## 代码块
Spring Aware演示Bean
```java
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware{
    private String beanName;
    private ResourceLoader loader;
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    public void outputResult() {
        System.out.println("Bean的名称为："+beanName);
        Resource resource = loader.getResource("classpath:com/mine/library/spring/aware/demo.txt");
        try{
            System.out.println("ResourceLoader加载的文件内容为："+ IOUtils.toString(resource.getInputStream()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
配置类
```java
@Configuration
@ComponentScan("com.mine.library.spring.aware")
public class AwareConfig {
}
```
运行
```java
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();
    }
}
```