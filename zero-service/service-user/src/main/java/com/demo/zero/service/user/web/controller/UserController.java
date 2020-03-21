package com.demo.zero.service.user.web.controller;

import com.demo.zero.commons.dto.BaseResult;
import com.demo.zero.commons.utils.MapperUtils;
import com.demo.zero.service.user.dto.LoginInfo;
import com.demo.zero.service.user.dto.LoginParam;
import com.demo.zero.user.api.UserProfileService;
import com.demo.zero.user.api.UserVerificationService;
import com.demo.zero.user.domain.UserProfile;
import com.demo.zero.user.domain.UserVerification;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 哈哈呵呵君
 * @date: 2020/1/28 00:39
 *  用户信息接口api
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户信息及登录相关接口")
public class UserController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9001/oauth/token";

    @Value("${base.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${base.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${base.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource
    public BCryptPasswordEncoder passwordEncoder;
    @Resource
    public TokenStore tokenStore;

    @Resource
    private UserVerificationService userVerificationService;
    @Resource
    private UserProfileService userProfileService;


    /**
     * 登录
     *
     * @param loginParam 登录参数
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", notes = "传递用户名及密码获取token")
    public BaseResult login(@RequestBody LoginParam loginParam) throws Exception {
        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();

        // 验证密码是否正确
        UserVerification userVerification =  userVerificationService.getByUsername(loginParam.getUsername());
        if (userVerification == null || !passwordEncoder.matches(loginParam.getPassword(), userVerification.getPassword())) {
            return BaseResult.fail("用户名或密码错误");
        }

        //使用Httpclient请求/oauth/token获取token
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost(URL_OAUTH_TOKEN);
        // 创建请求参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair username = new BasicNameValuePair("username", loginParam.getUsername());
        BasicNameValuePair password = new BasicNameValuePair("password", loginParam.getPassword());
        BasicNameValuePair grantType = new BasicNameValuePair("grant_type",oauth2GrantType);
        BasicNameValuePair clientId = new BasicNameValuePair("client_id", oauth2ClientId);
        BasicNameValuePair clientSecret = new BasicNameValuePair("client_secret", oauth2ClientSecret);
        list.add(username);
        list.add(password);
        list.add(grantType);
        list.add(clientId);
        list.add(clientSecret);
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        httpPost.setEntity(entityParam);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            String json= EntityUtils.toString(httpEntity);
            Map<String, Object> jsonMap = MapperUtils.json2map(json);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResult.fail("请求失败，请查看后台日志");
        }
        // 无论如何必须关闭连接
        finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return BaseResult.success("登录成功",result);
    }

    /**
     * 获取用户信息
     *
     * @return BaseResult
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/info")
    @ApiOperation(value = "获取用户相关信息", notes = "获取用户进入主页所需信息（用户名，头像等）")
    public BaseResult info() {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userProfileService.getByUsername(authentication.getName());
        if (userProfile==null){
            return BaseResult.fail("不存在该用户");
        }
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(userProfile.getUsername());
        loginInfo.setAvatar(userProfile.getAvatar());
        loginInfo.setNickName(userProfile.getNickName());

        return BaseResult.success("获取成功",loginInfo);
    }

    /**
     * 注销
     *
     * @return BaseResult
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/logout")
    @ApiOperation(value = "退出登录", notes = "请求删除token进行退出登录")
    public BaseResult logout(HttpServletRequest request) {
        // 获取 token
        String token = request.getParameter("access_token");
        // 删除 token 以注销
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return BaseResult.success("注销成功");
    }
}
