package com.ldr.common.websocket;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

/**
 * @类说明 指纹模块登录接入点配置
 * @author keke
 * @date 2022-11-26 14:50:00
 */
@Slf4j
@Component
@ServerEndpoint("/login/point")
public class LoginPoint {
	private AtomicInteger clientCount = new AtomicInteger(0); // 客户端个数
	private static CopyOnWriteArraySet<LoginPoint> clients = new CopyOnWriteArraySet<>();// 客户端对像
	private Session session;// 客户端的连接会话

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		clients.add(this);// 加入set中
		clientCount.incrementAndGet();// 添加在线人数
		log.info("新连接接入sessionId={},当前在线人数为:{}.", session.getId(), clientCount);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		clients.remove(this);// 从set中删除
		clientCount.decrementAndGet(); // 在线数减1
		log.info("sessionId={}的连接关闭,当前在线人数为:{}.", this.session.getId(), clientCount);
	}

	/**
	 * 收到客户端消息后调用
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("客户端发送的消息：{}", message);
	}

	/**
	 * 暴露给外部的群发
	 */
	public void sendInfo(String message) {
		clients.forEach(item -> item.sendMessage(message));
	}

	/**
	 * 发生错误时调用
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("----websocket-------有异常啦", error);
	}

	/**
	 * 发送信息
	 */
	public void sendMessage(String vo) {
		// 获取session远程基本连接发送文本消息
		log.info("{}",vo);
		this.session.getAsyncRemote().sendText(vo);
	}
}
