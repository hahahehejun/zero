package com.demo.zero.cloud.aliyun.sms.listener;

import com.demo.zero.cloud.aliyun.sms.util.AliyunSmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @change 2020.02.01 将消息队列从rabbitmq改为rocketmq
 * @author 哈哈呵呵君
 * @date: 2020/2/1 04:35
 */
@Component
public class SmsListener {

    @Resource
    private AliyunSmsUtil aliSmsUtil;
    private static final Logger logger =  LoggerFactory.getLogger(SmsListener.class);



    @StreamListener("sms")
    public void sendSms(Map<String,String> message){
        logger.warn("111111111");
        String phone = message.get("phone");
        String code = message.get("code");
        if (Objects.nonNull(phone)&&Objects.nonNull(code)){
            //为了省钱先把对接阿里短信接口注释掉
            try {
                //aliSmsUtil.sendSms(phone,code);
                System.out.println("为了省钱先把对接阿里短信接口注释掉了");
            }
            catch (Exception e){
                logger.warn("连接至阿里云短信服务失败");
                logger.warn(e.getMessage());
            }
            System.out.println("手机号："+phone);
            System.out.println("验证码："+code);
            /**
             * 后期加上日志服务的时候  将发送的每条短信信息存储到数据库
             */
        }


    }
}
