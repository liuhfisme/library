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