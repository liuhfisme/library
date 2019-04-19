package com.mine;

import com.library.utils.http.HttpRequestUtil;
import com.library.valid.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-27
 */
public class Demo {
    private static final String token = "77fe41016e4d47ddf45efb4ec110443d";
    private static Long timestamp;
    private static final String employeePhone = "15666666666";
    private static Map<String, String> params;
    private static String sign;
    public static void main(String[] args) {
        init();
        String res = HttpRequestUtil.httpGet("http://192.168.7.151:9092/o/payroll-external/check", params);
        System.out.println(res);
    }
    private static void init() {
        timestamp  = System.currentTimeMillis();
        System.out.println(timestamp);
        params = new HashMap(){{
            put("timestamp", timestamp+"");
            put("employeePhone", employeePhone);
        }};
        sign = SignUtil.createSign(params, token);
        System.out.println(sign);
        params.put("sign", sign);

    }
}