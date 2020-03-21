package com.demo.zero.cloud.aliyun.sms.listener.test;

import com.aliyuncs.exceptions.ClientException;
import com.demo.zero.cloud.aliyun.sms.listener.SmsListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/16 07:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsListenerTest {

    private SmsListener smsListener = new SmsListener();

    @Test
    public void sendSmsTest() throws UnsupportedEncodingException, ClientException {
        Map<String,String> message = new HashMap<>();
        message.put("phone","123456");
        message.put("code","12345");
        smsListener.sendSms(message);
    }
}
