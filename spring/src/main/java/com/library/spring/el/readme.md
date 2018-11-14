# EL
>Spring EL-Spring表达式语言，支持在xml和注解中使用表达式，类似于JSP的EL表达式语言。

Spring主要在注解@Value的参数中使用表达式。本例演示实现以下几种情况：
- 注入普通字符。
- 注入操作系统属性。
- 注入表达式运算结果。
- 注入其它Bean的属性。
- 注入文件内容。
- 注入网址内容。
- 注入属性文件。

## 代码块
普通字符串值注入
```java
@Service
public class DemoService {
    @Value("其他类的属性") //注入普通字符
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
```
配置类
```java
@Configuration
@ComponentScan(libraryel")
@PropertySourlibraryes") //使用@PropertySource注入配置文件
public class ElConfig {
    @Value("I Love You！") //注入普通字符
    private String normal;

    @Value("#{systemProperties['os.name']}") //注入操作系统属性
    private String osName;

    @Value("#{ T(java.lang.Math).random()*100.0}") //注入表达式结果
    private String randomNumber;

    @Value("#{demoService.another}") //输入其它Bean属性
    private String fromAnother;

    @Vallibraryxt") //输入文件资源
    private Resource demoFile;

    @Value("http://www.baidu.com") //注入网址资源
    private Resource demoUrl;

    @Value("${book.name}") //注入配置文件属性
    private String bookName;

    @Autowired
    private Environment environment; //注入Spring环境容器environment

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(demoFile.getInputStream()));
            System.out.println(IOUtils.toString(demoUrl.getInputStream()));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```