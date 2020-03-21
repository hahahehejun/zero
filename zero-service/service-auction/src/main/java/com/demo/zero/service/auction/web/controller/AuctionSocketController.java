package com.demo.zero.service.auction.web.controller;


import com.alibaba.fastjson.JSON;
import com.demo.zero.auction.api.AuctionService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 哈哈呵呵君
 */
@ServerEndpoint("/message")
@RestController
public class AuctionSocketController {


    private static AuctionService auctionService;

    @Resource
    private void setAuctionService(AuctionService auctionService){
        AuctionSocketController.auctionService=auctionService;
    }

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MessageController对象。
    private static CopyOnWriteArraySet<AuctionSocketController> webSocketSet = new CopyOnWriteArraySet<AuctionSocketController>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        List<String> messages = new ArrayList<>();
        messages.add(message);

        //群发消息
        for (AuctionSocketController item : webSocketSet) {
            try {
                item.sendMessage(messages);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     @OnError
     */
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(List<String> messages) throws IOException, EncodeException {
         String json = JSON.toJSONString(messages);
         this.session.getBasicRemote().sendText(json);
     }


     /**
      * 群发自定义消息
      */
    public static void sendInfo(List<String> messages) throws IOException {
        for (AuctionSocketController item : webSocketSet) {
            try {
                item.sendMessage(messages);
            } catch (IOException e) {
                continue;
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        AuctionSocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        AuctionSocketController.onlineCount--;
    }
}
