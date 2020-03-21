package com.demo.zero.cloud.aliyun.sms.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/1 04:35
 */
public interface AdminLoginLogSink {
    @Input("sms")
    SubscribableChannel sms();
}
