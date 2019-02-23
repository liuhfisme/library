package com.library.scloud.learn.invoker;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @ClassName: TestHttpClient
 * @Description: TODO
 * @author feifei.liu
 * @date 2018/10/23 10:24
 */
public class TestHttpClient {
    public static void main(String[] args) throws IOException {
        // 创建默认的HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 调用6次服务并输出结果
        for (int i=0; i<6; i++) {
            HttpGet httpGet = new HttpGet("http://localhost:8901/router");
            HttpResponse response = httpClient.execute(httpGet);
            System.out.println(EntityUtils.toString(response.getEntity()));
        }

        System.out.println(1 << 4);
    }
}
