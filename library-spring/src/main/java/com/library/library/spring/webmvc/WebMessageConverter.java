package com.library.library.spring.webmvc;

import com.library.library.spring.webmvc.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by liuff on 2017/4/2.
 * 自定义 HttpMessageConverter
 * 继承AbstractHttpMessageConverter接口来实现HttpMessageConverter
 */
public class WebMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    /**
     * 新建一个我们自定义的媒体类型
     */
    public WebMessageConverter() {
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 表明HttpMessageConverter只处理DemoObj这个类
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }

    /**
     * 重写readIntenal方法，处理请求的数据。代码标明我们处理由“-”隔开的数据，并转成DemoObj的对象
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]), tempArr[1]);
    }

    /**
     * 重写writeInternal，处理如何输出数据到response。
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:"+obj.getId()+"-"+obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }

}
