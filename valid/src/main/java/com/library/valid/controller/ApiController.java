package com.library.valid.controller;

import com.library.valid.constant.SignConstant;
import com.library.valid.model.ResponseData;
import com.library.valid.model.ValidVo;
import com.library.valid.utils.SignUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 开放API控制器.
 *
 * @author liufefei02@beyondsoft.com
 * @version 1.0
 * @date 2019-03-07
 */
@RestController
@RequestMapping("/o/payroll")
public class ApiController {

    @RequestMapping("/verify")
    public ResponseData verify() {
        return ResponseData.instance(new ValidVo("username", "admin@beyondsoft.com"));
    }

    /**
     * API 对接授权码（该授权码应由对接用户申请）
     */
    private static final String APP_SECRET = "244e7f76db84b915ff10dd9d239eeb4b";

    public static void main(String[] args) {
        test();
    }
    private static void test() {
        long currentMills = System.currentTimeMillis();
        Map<String,Object> param = new HashMap<>(16);
        param.put("userId","9527");
        param.put("amount","9.99");
        param.put("productId","9885544154");
        param.put(SignConstant.SECRET_KEY, APP_SECRET);
        param.put(SignConstant.TIMESTAMP_KEY, currentMills);
        String sign = SignUtil.createSign(param, APP_SECRET);
        param.put(SignConstant.SIGN_KEY, sign);
        System.out.println(currentMills);
        System.out.println(sign);
    }
}