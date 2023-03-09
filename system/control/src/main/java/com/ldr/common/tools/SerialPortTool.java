package com.ldr.common.tools;

import com.ldr.common.cmd.CMDConst;
import com.ldr.common.cmd.CMDTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import purejavacomm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * @summary 串口通信工具
 * @author 高振中
 * @date 2022-11-18 20:20:20
 **/
@Slf4j
//@Component
public class SerialPortTool {

	@Value("${serial.portName}") // 注入串口的端口号
	private String name;
	@Value("${serial.baudrate}") // 注入串口的波特率
	private Integer baudrate;
	private SerialPort serialPort = null;

	private static final int dataBits = 8;
	private static final int stopBits = 1;
	private static final int parity = 0;
	private static final int timeOut = 2000;

	/**
	 * 打开串口
	 */
	public void open() {
		try {
			// 通过端口名识别端口
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(name);
			// 打开端口，并给端口名字和一个timeout（打开操作的超时时间）
			CommPort commPort = portIdentifier.open(name, timeOut);
			// 判断是不是串口
			if (commPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) commPort;
				// 设置一下串口的波特率等参数
				serialPort.setSerialPortParams(baudrate, dataBits, stopBits, parity);
				log.info("串口" + name + "打开成功");
				this.serialPort = serialPort;
			} else {
				log.error("不是串口" + name);
			}
		} catch (NoSuchPortException e1) {
			log.error("无此串口" + name);
		} catch (PortInUseException e2) {
			log.error("串口使用中" + name);
		} catch (UnsupportedCommOperationException e) {
			log.error("设置串口" + name + "参数失败：" + e.getMessage());
		}
	}
	/**
	 * @方法说明 向指纹模块发送指令前等待响应结果
	 */
	public byte[] request(String command) {
		byte[] byteCmd = CMDTool.toByteArray(command);
		byte[] response =  send(byteCmd).read(CMDConst.DATA_LEN);
		int key = response[4];
		String hexString = CMDTool.toHexString(response);// 16进制对应的文本信息
		// 0X0C为1:N对比指令
		String msg = (byteCmd[1] == 0X0C ? CMDConst.COMPARE_MESSAGE.get(key) : CMDConst.FINGER_MESSAGE.get(key));
		log.info("指令:{},响应数据:{},描述信息:{}。", command, hexString, msg);
		return response;
	}
	/**
	 * 往串口发送数据
	 */
	private SerialPortTool send(byte[] command) {
		try {
			OutputStream out = serialPort.getOutputStream();
			out.write(command);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("发送数据失败{}", serialPort.getName());
		} catch (NullPointerException e) {
			log.error("串口没有打开,请核对");
			throw new RuntimeException("串口没有打开,请核对......");
		}
		return this;
	}

	/**
	 * 从串口读取数据
	 */
	public byte[] read(int length) {
		try {
			InputStream in = serialPort.getInputStream();
			for (int bufflenth = 0; bufflenth != length; bufflenth = in.available()) {
				TimeUnit.MILLISECONDS.sleep(20);
			}
			byte[] bytes = new byte[length];
			in.read(bytes, 0, length);
			return bytes;
		} catch (IOException e) {
			log.info("读取数据时发生IOException异常", e);
		} catch (InterruptedException e) {
			log.info("读取数据时发生IOException异常", e);
		}
		return null;
	}

	/**
	 * 生成指纹模块验证码
	 */
	public int createCHK(String str) {
		byte[] chkByte = CMDTool.toByteArray(str);
		byte start = chkByte[1];
		for (int i = 2; i < 6; i++) {
			start ^= chkByte[i];
		}
		return start;
	}
}
