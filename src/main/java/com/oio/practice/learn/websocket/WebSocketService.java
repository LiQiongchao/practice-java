package com.oio.practice.learn.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.lang.model.element.NestingKind;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author: LiQiongchao
 * @Date: 2019/6/23 10:13
 */
@Slf4j
@Component
@ServerEndpoint("/socket/{uid}")
public class WebSocketService {

    // 存放当前所有的连接数
    private static LongAdder onlineCount = new LongAdder();

    // 存入当前连接的socket对象
    private static CopyOnWriteArraySet<WebSocketService> webSocket = new CopyOnWriteArraySet();

    private Session session;

    private String uid = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        this.session = session;
        webSocket.add(this);
        onlineCount.increment();
        log.info("【web socket 消息】有新的连接，uid: {}, 在线总人数：{}", uid, onlineCount.intValue());
    }

    @OnClose
    public void onClose() {
        webSocket.remove(this);
        onlineCount.decrement();
        log.info("【web socket 消息】关闭连接，uid: {}，在线总人数：{}", uid, onlineCount.intValue());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("【web socket 消息】有新的消息, uid: {}，接收到消息：{}", uid, message);
        // 返回消息
        this.sendMessage("接收到消息：" + message);
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("【web socket 消息】，uid: {}, 发生错误", uid);
        error.printStackTrace();
    }

    public static void sendInfo(String message, @PathVariable String uid) {
        log.info("【web socket 消息】推送消息，uid: {}，消息：{}", uid, message);
        for (WebSocketService item : webSocket) {
            //这里可以设定只推送给这个sid的，为null则全部推送
            if(uid==null) {
                item.sendMessage(message);
            }else if(item.uid.equals(uid)){
                item.sendMessage(message);
            }
        }
    }

}
