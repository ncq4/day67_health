package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validatecode")
public class ValidateCodeController {
    //使用到redis要导的类
    @Autowired
    private JedisPool jedisPool;
    //体检预约发送验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //使用验证码工具类获取一个长度为4的验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        try {
            //使用发送短信工具类向手机发送验证码
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code);
            //将验证码保存在redis中,其中telephone+ RedisMessageConstant.SENDTYPE_ORDER为key,
            // RedisMessageConstant.SENDTYPE_ORDER是为了标识这是登录用的验证码
            //300是指300秒,在redis里保存300秒后销毁
            //code是保存的数据
            jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,code);
            return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);
        } catch (ClientException e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
        }
    }
}
