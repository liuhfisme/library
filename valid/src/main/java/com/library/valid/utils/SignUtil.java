package com.library.valid.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.valid.constant.SignConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 签名认证工具类.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-06
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUtil {
    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(createSecret()) );
    }

    /**
     * APP 生成唯一key&secret
     * @return
     */
    public static AppAuth createSecret() {
        UUID uuid = UUID.randomUUID();
        String tempKey = uuid.toString();
        String tempSecret = DigestUtils.md5Hex(SignConstant.SIGN_KEY + tempKey);
        return new AppAuth(tempKey, tempSecret);
    }

    /**
     * 生成签名
     * @param param 向VIP发送的参数
     * @param accessSecret 由VIP提供的私钥，该私钥应存储于properties方便更换
     * @return String
     */
    public static String createSign(Object param, String accessSecret) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map params;
        try {
            String jsonStr = objectMapper.writeValueAsString(param);
            params = objectMapper.readValue(jsonStr, Map.class);

        } catch (Exception e) {
            throw new RuntimeException("生成签名：转换json失败");
        }

        if (params.get(SignConstant.TIMESTAMP_KEY) == null) {
            params.put(SignConstant.TIMESTAMP_KEY, System.currentTimeMillis());
        }
        //对map参数进行排序生成参数
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        for (Object key : keys) {
            temp.append("&");
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        if (StringUtils.isNotEmpty(temp.toString())) {
            temp.deleteCharAt(0);
        }
        temp.append("&").append(SignConstant.SECRET_KEY).append("=").append(accessSecret);
        //根据参数生成签名
        String sign = DigestUtils.md5Hex(temp.toString()).toUpperCase();
        return sign;
    }

    /**
     * 校验签名有效性
     * @param request
     * @param accessSecret
     * @return
     * @throws UnsupportedEncodingException
     */
    public static boolean verificationSign(HttpServletRequest request, String accessSecret) {
        Enumeration<?> keys = request.getParameterNames();
        Map<String, Object> params = new HashMap<String, Object>();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (SignConstant.SIGN_KEY.equals(key)) {
                continue;
            }
            Object value = request.getParameter(key);
            params.put(key, value);
        }
        String timestamp = request.getParameter(SignConstant.TIMESTAMP_KEY);
        Long ts = Long.valueOf(timestamp);
        if (System.currentTimeMillis() - ts > SignConstant.EXPIRE_TIME) {
            log.info("Request EXPIRE_TIME out!");
            return false;
        }
        String originSign = request.getParameter(SignConstant.SIGN_KEY);
        String sign = createSign(params, accessSecret);
        return sign.equals(originSign);
    }

}