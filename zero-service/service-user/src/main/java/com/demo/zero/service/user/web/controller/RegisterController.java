package com.demo.zero.service.user.web.controller;

import com.demo.zero.commons.dto.BaseResult;
import com.demo.zero.commons.utils.RandomCodeUtil;
import com.demo.zero.service.user.source.MessageSource;
import com.demo.zero.user.api.UserProfileService;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.UserProfile;
import com.demo.zero.user.domain.UserVerification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: 哈哈呵呵君
 * @date: 2020/1/20 21:24
 */
@RestController
@RequestMapping("register")
@Api(tags = "用户注册相关接口")
public class RegisterController {

    @Resource
    private UserVerificationService userVerificationService;
    @Resource
    private UserProfileService userProfileService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Resource
    public BCryptPasswordEncoder passwordEncoder;
    @Resource
    private MessageSource source;


    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/{code}")
    public BaseResult register(@RequestBody UserVerification userVerification, @PathVariable String code){
        //验证用户名是否存在
        if(userVerificationService.getByUsername(userVerification.getUsername())!=null){
            return BaseResult.fail("用户名已存在");
        }
        String redisCode = redisTemplate.opsForValue().get("smscode_"+userVerification.getPhone());
        //验证code，通过进行注册
        if(!Objects.equals(code,redisCode)){
            return BaseResult.fail("短信验证码错误");
        }
        //save UserVerification
        userVerification.setPassword(passwordEncoder.encode(userVerification.getPassword()));
        userVerificationService.save(userVerification);

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(userVerification.getUsername());
        userProfile.setPhone(userVerification.getPhone());

        userProfileService.save(userProfile);
        return BaseResult.success("注册成功",userVerification.getUsername());

    }


    @ApiOperation(value = "注册验证码", notes = "请求用户注册验证码短信")
    @GetMapping("/code/{phone}")
    public BaseResult sendRegisterSmsCode(@PathVariable String phone){
        //验证phone是否已存在，不存在则发送注册验证码
        if (userVerificationService.getByPhone(phone) != null){
            return BaseResult.fail("该号码已存在");
        }
        //生成code，并发送至rabbitmq，redis
        int code = RandomCodeUtil.getCode();
        HashMap<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("code",code+"");
        source.sms().send(MessageBuilder.withPayload(map).build());
        redisTemplate.opsForValue().set("smscode_"+phone,code+"",5, TimeUnit.MINUTES);
        rabbitTemplate.convertAndSend("sms",map);
        System.out.println(code);
        return BaseResult.success("短信已发送");
    }

}
