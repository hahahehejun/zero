package com.demo.zero.service.auction.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/2/1 03:52
 */
public interface MessageSource {
    @Output("sms")
    MessageChannel sms();

}
