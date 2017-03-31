# Overview
*Spring MVC 4.x*
## 三层架构&MVC
*   说到Spring MVC，不得不先来谈谈什么是MVC，它和三层架构是什么关系。
可能很多人会说：
    
*   MVC：Model + View + Controller (数据模型+视图+控制器)。
    三层架构：Presentation tier + Application tier + Data tier (展现层+应用层+数据访问层)。
    
那MVC和三层架构有什么关系呢？

*   实际上MVC只存在三层架构的展现层， M实际上是数据模型，是包含数据的对象。
    在Spring MVC里，有一个专门的类叫Model，用来和V之间的数据交互、传值；
    V指的是视图页面，包含JSP、Freemarker、Velocity、Thymeleaf、Title等；
    C就是控制器（Spring MVC的注解@Controller的类）。
    
*   而三层架构师整个应用的架构，是由Spring架构负责管理的。一般项目结构都有Service层、DAO层，
    这两个反馈在应用层和数据访问层。
弄清MVC和三层架构的关系对我们理解Spring MVC和进行Web开发至关重要。
## Spring MVC项目快速搭建

## Spring MVC基本配置
*   Spring MVC的定制配置需要我们的配置类继承一个WebMvcConfigurerAdapter类，并在此类使用@EnableWebMvc注解，来开启对
Spring MVC的配置支持，这样我们就可以重写这个类的方法，完成我们的常用配置。
我们将示例WebMvcConfig配置类继承WebMvcConfigurerAdapter。
### 静态资源映射
*   程序的静态文件（js、css、图片）等需要直接访问，这时我们可以在配置里重写addResourceHandlers方法来实现。
### 拦截器配置
*   拦截器（Interceptor）实现对每一个请求处理前后进行相关的业务处理，类似于Servlet的Filter。
*   可让普通的Bean实现HandlerInterceptor接口或者继承HandlerInterceptorAdapter类来实现自定义拦截器。
*   通过重写WebMvcConfigurerAdapter的addInterceptors方法来注册自定义的拦截器。
### @controllerAdvice
*   通过@ControllerAdvice，我们可以将对于**控制器的全局配置**放置在同一个位置，注解了@Controller的类的方法可使用@ExecptionHandler、
@InitBinder、@ModelAttribute注解到方法上，这对所有注解了@RequestMapping的控制器内的方法有效
    *   @ExceptionHandler：用于全局处理控制器里的异常。
    *   @InitBinder：用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中。
    *   @ModelAttribute：@ModelAttribute本来的作用是绑定键值对到Model中，此处是让全局的@RequestMapping都能获得在此处设置的键值对。
### 其它配置
#### 快捷的ViewController
    @RequestMapping("/index")
    public String demo() {
        return "index";
    }
    
    此处只是简单的页面转向，在实际开发中会涉及到大量这样的页面转向，若都这样写会很麻烦，我们可以通过在配置中重写addViewControllers
    来简化配置：
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
    }
    
    这样实现的代码更简洁，管理更集中。
#### 路径匹配参数配置
    再Spring MVC中，路径参数如果带"."的话，"."会面的值将被忽略，例如"http://localhost:8080/anno/pathvar/xx.yy"，此时"."后面
    的yy被忽略。
    通过重写configurePathMatch方法可不忽略"."后面的参数，代码如下：
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
    
    这时，再访问"http://localhost:8080/anno/pathvar/xx.yy"，就可以接受"."后面的yy了。
#### 更多配置
    更多配置请查看WebMvcConfigurerAdapter类的API。因其是WebMvcConfigurer接口的实现，所以WebMvcConfigurer的API内的方法也可以
    用来配置MVC，可参考WebMvcConfiturerAdapter和WebMvcConfigurer的源码。
## Spring MVC的高级配置
### 文件上传配置
*   通过配置一个MultipartResolver来上传文件
*   在Spring的控制器中，通过MultipartFile file来接收文件，通过MultipartFile[] files接收多个文件上传。