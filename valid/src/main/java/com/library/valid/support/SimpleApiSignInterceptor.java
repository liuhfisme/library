package com.library.valid.support;

import com.alibaba.fastjson.JSONObject;
import com.library.valid.constant.SignConstant;
import com.library.valid.model.ResponseData;
import com.library.valid.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API签名拦截实现.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-05
 */
public class SimpleApiSignInterceptor implements HandlerInterceptor {

    /**
     * API 对接授权码（该授权码应由对接用户申请）
     */
    private static final String APP_SECRET = "244e7f76db84b915ff10dd9d239eeb4b";
    private static Map<String, String> map = new ConcurrentHashMap<String, String>();
    static {
        map.put(SignConstant.SECRET_KEY, APP_SECRET);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String timestamp = request.getParameter(SignConstant.TIMESTAMP_KEY);
        String accessSecret = map.get(SignConstant.SECRET_KEY);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 请求时间戳校验
        if (timestamp == null || !StringUtils.isNumeric(timestamp)) {
            ResponseData responseData = ResponseData.instance(HttpStatus.OK.value(), "1000", "请求时间戳不合法");
            response.getWriter().write(JSONObject.toJSONString(responseData));
            return false;
        }

        // 检查KEY是否合理
        if (StringUtils.isEmpty(accessSecret)) {
            ResponseData responseData = ResponseData.instance(HttpStatus.OK.value(), "1001", "加密KEY不合法");
            response.getWriter().write(JSONObject.toJSONString(responseData));
            return false;
        }
        Long ts = Long.valueOf(timestamp);
        // 禁止超时签名
        if (System.currentTimeMillis() - ts > SignConstant.EXPIRE_TIME) {
            ResponseData responseData = ResponseData.instance(HttpStatus.OK.value(), "1002", "请求超时");
            response.getWriter().write(JSONObject.toJSONString(responseData));
            return false;
        }

        if (!SignUtil.verificationSign(request, accessSecret)) {
            ResponseData responseData = ResponseData.instance(HttpStatus.OK.value(), "1003", "签名错误");
            response.getWriter().write(JSONObject.toJSONString(responseData));
            return false;
        }
        return true;
    }
}