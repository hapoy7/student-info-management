package com.ldr.common.cmd;

import java.util.Map;

/**
 * @类说明 通讯常量类
 * @author 高振中
 * @date 2022-11-10 18:44:38
 **/
public interface CMDConst {
	String HEX2_NB = "%02X";// 2位16进制
	String HEX4_NB = "%04X";// 4位16进制
	String HEX2 = "%02X ";// 2位16进制
	String HEX4 = "%04X ";// 4位16进制

	String COMMOND_START_END = "F5"; // 指纹命令的开始和结束符
	String COMMOND_RESERVE = "00"; // 指纹命令的预留位置
	int DATA_LEN = 8;// 指令与返回数据长度
	int SINGER_LEN = 207; // 指纹特征值的数据包长度

	// 自研钥匙柜错误码表5位
	Map<Integer, String> BOX_MESSAGE = Map.of(0x1, "不支持的功能码", 0x2, "寄存器地址错误", 0x3, "数据值域错误", 0x4, "写入失败");
	// 指纹模块错误码表
	Map<Integer, String> FINGER_MESSAGE = Map.of(0x0, "操作成功!", 0x1, "操作失败!", 0x4, "指纹数据库已满!", 0x5, "无此用户!", 0x6, "用户已存在!", 0x7, "指纹已存在!", 0x8, "采集超时!");
	// 指纹1:N对比模块错误码表
	Map<Integer, String> COMPARE_MESSAGE = Map.of(0x1, "操作成功!", 0x5, "无此指纹!",0x8, "采集超时!");
}
