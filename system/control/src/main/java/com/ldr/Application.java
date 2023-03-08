package com.ldr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ldr.common.tools.SerialPortTool;
import com.ldr.manage.base.face.FaceInit;
import com.ldr.manage.base.netty.client.NettyClient;
import com.ldr.manage.base.netty.server.NettyServer;

import jakarta.annotation.PostConstruct;

/**
 * @author 高振中
 * @date 2021-02-24 10:50:00
 */
@EnableAsync // 开启异步调用
@EnableScheduling
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private NettyServer server;// 注入netty服务器
	@Autowired
	private NettyClient client;// 注入netty客户端
	@Autowired
	private SerialPortTool serialPortTool; // 注入Serial 工具类
	@Autowired
	private FaceInit faceInit; // 人脸信息初始化组件
	@Value("${serial.isOpen}") // 注入Serial开启的标志
	private Boolean isOpenSerial;
	@Value("${baidu.face.isOpen}") // 注入人脸识别开启的标志
	private Boolean isOpenFace;
	@Value("${netty.isOpen}") // 注入人脸识别开启的标志
	private Boolean isOpenNetty;

	/**
	 * @方法说明 初始操作
	 */
	@PostConstruct
	public void init() {
		if (isOpenNetty) {
			server.start();// 启动netty服务器
			client.start();// 启动netty模拟客户端
		}
		if (isOpenSerial)
			serialPortTool.open();// 打开串口
		if (isOpenFace)
			faceInit.init();// 初始化百度人脸服务
	}
}
