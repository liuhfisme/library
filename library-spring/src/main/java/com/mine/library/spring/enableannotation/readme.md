# @Enable*注解的工作原理
>通过简单的@Enable*来开启一项功能的支持，例如前面讲的@EnableAspectJAutoProxy开启对AspectJ自动代理的支持，从而避免自己配置大量
的代码，大大降低使用难度。那么下面我们一起来研究下这个神奇的功能的实现原理吧。

通过观察这些@Enable*注解的源码，我们发现所有的注解都有一个@Import注解，@Import是用来导入配置类的，这也就意味着这些自动开启的实
现其实是导入了一些自动配置的Bean。这些导入的配置方式主要分为以下三种类型。
- 第一类：直接导入配置类（@EnableScheduling为例）
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({SchedulingConfiguration.class})
@Documented
public @interface EnableScheduling {
}
```
直接导入配置类SchedulingConfiguration，这个类注解了@Configuration，而且注册了一个ScheduledAnnotationBeanPostProcessor的Bean，源码如下：
```java
@Configuration
@Role(2)
public class SchedulingConfiguration {
    public SchedulingConfiguration() {
    }

    @Bean(
        name = {"org.springframework.context.annotation.internalScheduledAnnotationProcessor"}
    )
    @Role(2)
    public ScheduledAnnotationBeanPostProcessor scheduledAnnotationProcessor() {
        return new ScheduledAnnotationBeanPostProcessor();
    }
}
```
- 第二类：依据条件选择配置类（@EnableAsync为例）
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AsyncConfigurationSelector.class})
public @interface EnableAsync {
    Class<? extends Annotation> annotation() default Annotation.class;

    boolean proxyTargetClass() default false;

    AdviceMode mode() default AdviceMode.PROXY;

    int order() default 2147483647;
}
```
AsyncConfigurationSelector通过条件来选择需要导入的配置类，AsyncConfigurationSelector的根接口为ImportSelector，这个接口需
重写selectImports方法，在此方法内进行事先条件判断。此例中，若adviceMode为PROXY，则返回ProxyAsyncConfiguration这个配置类；若
activeMode为ASPECTJ，则返回AspectJAsyncConfiguration配置类，源码如下：
```java
public class AsyncConfigurationSelector extends AdviceModeImportSelector<EnableAsync> {
    private static final String ASYNC_EXECUTION_ASPECT_CONFIGURATION_CLASS_NAME = "org.springframework.scheduling.aspectj.AspectJAsyncConfiguration";

    public AsyncConfigurationSelector() {
    }

    public String[] selectImports(AdviceMode adviceMode) {
        switch(null.$SwitchMap$org$springframework$context$annotation$AdviceMode[adviceMode.ordinal()]) {
        case 1:
            return new String[]{ProxyAsyncConfiguration.class.getName()};
        case 2:
            return new String[]{"org.springframework.scheduling.aspectj.AspectJAsyncConfiguration"};
        default:
            return null;
        }
    }
}
```
- 第三类：动态注册Bean（@EnableAspectJAutoProxy为例）
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AspectJAutoProxyRegistrar.class})
public @interface EnableAspectJAutoProxy {
    boolean proxyTargetClass() default false;

    boolean exposeProxy() default false;
}
```
AspectJAutoProxyRegistrar实现了ImportBeanDefinitionRegistrar接口，ImportBeanDefinitionRegistrar的作用是在运行时自动添加
Bean到已有的配置类，通过重写方法registerBeanDefinitions,其中，AnnotationMetadata参数用来获得当前配置类上的注解；
BeanDefinitionRegistry参数用来注解Bean。源码如下：
```java
class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar {
    AspectJAutoProxyRegistrar() {
    }

    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
        AnnotationAttributes enableAspectJAutoProxy = AnnotationConfigUtils.attributesFor(importingClassMetadata, EnableAspectJAutoProxy.class);
        if(enableAspectJAutoProxy.getBoolean("proxyTargetClass")) {
            AopConfigUtils.forceAutoProxyCreatorToUseClassProxying(registry);
        }

        if(enableAspectJAutoProxy.getBoolean("exposeProxy")) {
            AopConfigUtils.forceAutoProxyCreatorToExposeProxy(registry);
        }

    }
}
```