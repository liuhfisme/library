package com.library.util;

import com.library.utils.http.HttpRequestUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 天气预报测试.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-04-15
 */
public class WeatherTests {
    @Test
    public void test1() {
        String url = "https://www.tianqiapi.com/api/?version=v1&cityid=101110101";
        String res = HttpRequestUtil.httpGet(url);
        System.out.println(res);
    }

    @Test
    public void test2() {
        String url = "https://api.heweather.net/s6/air?parameters";
        Map<String, String> params = new HashMap<>(16);
        params.put("location", "CN101010100");
        params.put("key", "af0a0d40db2e485a8059b69cfd1b3a70");
        System.out.println(HttpRequestUtil.httpGet(url, params));
    }
}