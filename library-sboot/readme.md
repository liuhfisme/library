# 实战 Spring Boot
>

## Spring Boot 基础
### Spring Boot概述
#### 什么是Spring Boot
>随着动态语言的流行（Ruby、Groovy、Scala、Node.js），Java的开发显得格外的笨重：繁多的配置、低下的开发效率、复杂的部署流程以及
 第三方技术集成难度大。

## Spring Boot 核心
### 基本配置
#### 入口类和@SpringBootApplication
#### 关闭特定的自动配置
#### 定制 Banner
修改 Banner
- 在Spring Boot启动的时候会有一个默认启动图标。
- 我们在src/main/resources 下新建一个banner.txt。
- 通过http://patorjk.com/software/taag网站生成字符，如敲入的为“WISELY”，将网站生成的字符复制到banner.txt中。
- 这时再启动程序，图案就会变为网站生成的图案。

关闭 Banner
- main里的内容修改为
```java
@RestController
@SpringBootApplication //Spring Boot项目的核心注解，主要目的是开启自动配置
public class LibraryApplication {
    @RequestMapping("/")
    String index() {
        return "Hello Spring Boot !";
    }

    public static void main(String[] args) {
//        SpringApplication.run(LibraryApplication.class, args);
        SpringApplication application = new SpringApplication(LibraryApplication.class);
        application.setBannerMode(Banner.Mode.OFF); // 关闭 banner
        application.run(args);
    }
}
```
#### Spring Boot的配置文件
>Spring Boot使用一个全局的配置文件application.properties或application.yml，放置在src/main/resources目录或者类路径的/config
下。

Spring Boot不仅支持常规的properties配置文件，还支持yaml语言的配置文件。yaml是以数据为中心的语言，在配置数据的时候具有面向对象
的特性。

Spring Boot的全局配置文件的作用是对一些默认配置的配置值进行修改。

简单示例

将Tomcat的默认端口8080修改为8800，并将默认的访问路径“/”改为“/sboot”。可以在application.properties中添加：
```properties
server.port=8800
server.context-path=/sboot
```
#### starter pom
>Srping Boot为我们提供了简化企业级开发绝大多数场景的starter pom，只要使用了应用场景所需要的starter pom，相关的技术配置将会消除
，就可以得到Spring Boot为我们提供的自动配置的Bean。

官方 starter pom
- spring-boot-starter：Spring Boot核心starter，包含自动配置、日志、yaml配置文件的支持。
- spring-boot-starter-actuator：准生产特性，用来监控和管理应用。
- spring-boot-starter-remote-shell：提供基于ssh协议的监控和管理。
- spring-boot-starter-amqp：使用spring-rabbit来支持AMQP。
- spring-boot-starter-aop：使用spring-aop和AspectJ支持面向切面编程。
- spring-boot-starter-batch：对Spring Batch的支持。
- spring-boot-starter-cache：对Spring Cache抽象的支持。
- spring-boot-starter-cloud-connectors：对云平台（Cloud Foundry、Heroku）提供的服务提供简化的链接方式。
- spring-boot-starter-data-elasticsearch：通过spring-data-elastiesearch对Elasticsearch支持。
- spring-boot-starter-data-gemfire：通过spring-data-gemfire对分布式存储GenFire的支持。
- spring-boot-starter-data-jpa：对JPA的支持，包含spring-data-jpa、spring-orm和Hibernate。
- spring-boot-starter-data-mongodb：通过spring-data-mongodb，对MongoDB进行支持。
- spring-boot-starter-data-rest：通过spring-data-rest-webmvc将Spring Data repository暴露为REST形式的服务。
- spring-boot-starter-data-solr：通过spring-data-solr对Apache Solr数据检索平台的支持。
- spring-boot-starter-freemarker：对FreeMarker模板引擎的支持。
- spring-boot-starter-groovy-templates：对Groovy模板引擎的支持。
- spring-boot-starter-hateoas：通过spring-hateoas对基于HATEOAS的REST形式的网络服务的支持。
- spring-boot-starter-hornetq：通过HornetQ对JMS的支持。
- spring-boot-starter-integration：对系统集成框架spring-integration的支持。
- spring-boot-starter-jdbc：对JDBC数据库的支持。
- spring-boot-starter-jersey：对Jersery REST形式的网络服务的支持。
- spring-boot-starter-jta-atomikos：通过Atomikos对分布式事务的支持。
- spring-boot-starter-jta-bitronix：通过Bitronix对分布式事务的支持。
- spring-boot-starter-mail：对javax.mail的支持。
- spring-boot-starter-mobile：对spring-mobile的支持。
- spring-boot-starter-mustache：对Mustache模板引擎的支持。
- spring-boot-starter-redis：对键值对内存数据库Redis的支持，包含spring-redis。
- spring-boot-starter-security：对spring-security的支持。
- spring-boot-starter-social-facebook：通过spring-social-facebook对Facebook的支持。
- spring-boot-starter-social-linkedin：通过spring-social-linkedin对Linkedin的支持。
- spring-boot-starter-social-twitter：通过spring-social-twitter对Twitter的支持。
- spring-boot-starter-test：对常用的测试框架JUnit、Hamcrest和Mockito的支持，包含spring-test模块。
- spring-boot-starter-thymeleaf：对Thymeleaf模板引擎的支持，包含于Spring整合的配置。
- spring-boot-starter-velocity：对Velocity模板引擎的支持。
- spring-boot-starter-web：对Web项目开发的支持，包含Tomcat和spring-webmvc。
- spring-boot-starter-Tomcat：Spring Boot默认的Servlet容器Tomcat。
- spring-boot-starter-jetty：使用Jetty作为Servlet容器替换Tomcat。
- spring-boot-starter-undertow：使用Undertow作为Servlet容器替换Tomcat。
- spring-boot-starter-logging：Spring Boot默认的日志框架Logback
- spring-boot-starter-log4j：支持使用Log4J日志框架。
- spring-boot-starter-websocket：对WebSocket开发的支持。
- spring-boot-starter-ws：对Spring Web Services的支持。

第三方 starter pom
- Handlebars：https://github.com/allegro/handlebars-spring-boot-starter
- Vaadin：https://github.com/vaadin/spring/tree/master/vaadin-spring-boot-starter
- Apache Camel：https://github.com/apache/camel/tree/master/components/camel-spring-boot
- WRO4J：https://github.com/sbuettner/spring-boot-autoconfigure-wro4j
- Spring Batch（高级用法）：https://github.com/codecentric/spring-boot-starter-batch-web
- HDIV：https://github.com/hdiv/spring-boot-starter-hdiv
- Jade Templates(Jade4J)：https://github.com/domix/spring-boot-starter-jade4j
- Activiti：https://github.com/Activiti/Activiti/tree/master/modules/activiti-spring-boot/spring-boot-starters

#### 使用xml配置
Spring Boot提倡零配置，即无xml配置，但是在实际项目中，可能有一些特殊要求你必须使用xml配置，这时我们可以通过Spring提供的
@ImportResource来加在xml配置，例如：
```java
@ImportResource({"classpath:some-context.xml","classpath:another-context.xml"})
```
### 外部配置
>Spring Boot允许使用properties文件、yaml文件或者命令行参数作为外部配置。

